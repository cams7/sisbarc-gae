/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.gae.controller.AbstractAppController;
import br.com.cams7.gae.security.AuthenticationHelper;
import br.com.cams7.gae.service.AppService;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.service.AALService;
import br.com.cams7.sisbarc.aal.validator.PinoValidator;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppHelper;

/**
 * @author cams7
 *
 */
public abstract class AALController<S extends AppService<E>, E extends AbstractEntity>
		extends AbstractAppController<S, E> {

	private PinoValidator validator;

	public AALController(PinoValidator validator) {
		super();
		this.validator = validator;
	}

	@Override
	public String criarForm(Model uiModel) {
		String page = super.criarForm(uiModel);

		if (page.equals(getPageInclude())
				&& uiModel.containsAttribute(getAttributeEntity())) {
			try {
				Pino pino = (Pino) AppHelper.getNewEntity(getEntityType());

				pino.setPino(new PinoKey());
				pino.setAlteraEvento(true);
				pino.setAlteraIntervalo(true);

				pino.setUser(AuthenticationHelper.getKeyUser());

				uiModel.addAttribute(getAttributeEntity(), pino);

				return page;
			} catch (AppException e) {
				getLog().log(Level.SEVERE, e.getMessage(), e);
			}
		}

		return PAGE_ERROR;
	}

	public String criar(Long userId, E entity, BindingResult result,
			Model uiModel, Locale locale) {
		validator.validate(entity, result);

		Pino pino = (Pino) entity;
		pino.setUser(AuthenticationHelper.getKeyUser(userId));

		return super.criar(entity, result, uiModel, locale);
	}

	public String atualizaPino(Long userId, E entity, BindingResult result,
			Model uiModel, Locale locale) {

		validator.validate(entity, result);

		Pino pino = (Pino) entity;
		pino.setUser(AuthenticationHelper.getKeyUser(userId));

		String page = getPageEdit();

		if (result.hasErrors()) {
			addAttributes(uiModel, entity, page);
			return page;
		}

		PinoKey key = pino.getPino();

		Object[] args = { key.getTipo(), key.getCodigo() };

		final String MSG_ERROR_UPDATE = getMessageSource().getMessage(
				"error.msg.pin.update", args, locale);

		try {
			Future<Boolean> call = ((AALService<E>) getService())
					.atualizaPino(entity);

			boolean arduinoRun = call.get();

			if (arduinoRun)
				page = listar(uiModel);
			else
				addMessageArduinoNotRun(uiModel, locale, MSG_ERROR_UPDATE);
		} catch (InterruptedException | ExecutionException e) {
			addERRORMessage(uiModel, MSG_ERROR_UPDATE, e.getMessage());
		} catch (NullPointerException | ArduinoException e) {
			addMessageMonitorNotRun(uiModel, locale, MSG_ERROR_UPDATE);
		}

		return page;
	}

	public String atualizaPinos(Model uiModel, Locale locale) {
		final String MSG_ERROR_UPDATE = getMessageSource().getMessage(
				"error.msg.pins.update", new Object[] {}, locale);

		List<E> pinos = getService().findAll();
		try {
			Future<Boolean> call = ((AALService<E>) getService())
					.alteraEventos(pinos);

			boolean arduinoRun = call.get();

			if (arduinoRun) {
				String summary = getMessageSource().getMessage(
						"info.msg.pins.update.ok", new Object[] {}, locale);// Resumo
				String detail = getMessageSource().getMessage(
						"info.msg.pins.update", new Object[] {}, locale);// Detalhes

				addINFOMessage(uiModel, summary, detail);
			} else
				addMessageArduinoNotRun(uiModel, locale, MSG_ERROR_UPDATE);

		} catch (InterruptedException | ExecutionException e) {
			addERRORMessage(uiModel, MSG_ERROR_UPDATE, e.getMessage());
		} catch (NullPointerException | ArduinoException e) {
			addMessageMonitorNotRun(uiModel, locale, MSG_ERROR_UPDATE);
		}

		String page = listar(uiModel);
		return page;
	}

	public String sincronizaPinos(Model uiModel, Locale locale) {
		final String MSG_ERROR_SYNCHRONIZE = getMessageSource().getMessage(
				"error.msg.pins.synchronize", new Object[] {}, locale);

		List<E> pinos = getService().findAll();
		try {
			Future<Boolean> call = ((AALService<E>) getService())
					.sincronizaEventos(pinos);

			boolean arduinoRun = call.get();

			if (arduinoRun) {
				String summary = getMessageSource()
						.getMessage("info.msg.pins.synchronize.ok",
								new Object[] {}, locale);// Resumo
				String detail = getMessageSource().getMessage(
						"info.msg.pins.synchronize", new Object[] {}, locale);// Detalhes

				addINFOMessage(uiModel, summary, detail);
			} else
				addMessageArduinoNotRun(uiModel, locale, MSG_ERROR_SYNCHRONIZE);

		} catch (InterruptedException | ExecutionException e) {
			addERRORMessage(uiModel, MSG_ERROR_SYNCHRONIZE, e.getMessage());
		} catch (NullPointerException | ArduinoException e) {
			addMessageMonitorNotRun(uiModel, locale, MSG_ERROR_SYNCHRONIZE);
		}

		String page = listar(uiModel);
		return page;
	}

	protected void addMessageMonitorNotRun(Model uiModel, Locale locale,
			String detail) {
		String summary = getMessageSource().getMessage(
				"error.msg.monitor.not.run", new Object[] {}, locale);// Resumo
		addWARNMessage(uiModel, summary, detail);
	}

	protected void addMessageArduinoNotRun(Model uiModel, Locale locale,
			String detail) {
		String summary = getMessageSource().getMessage(
				"error.msg.arduino.not.run", new Object[] {}, locale);// Resumo
		addWARNMessage(uiModel, summary, detail);
	}

	@ModelAttribute("tipos")
	public ArduinoPinType[] populateTipos() {
		return ArduinoPinType.values();
	}

	@ModelAttribute("intervalos")
	public Intervalo[] populateIntervalos() {
		return Intervalo.values();
	}

}
