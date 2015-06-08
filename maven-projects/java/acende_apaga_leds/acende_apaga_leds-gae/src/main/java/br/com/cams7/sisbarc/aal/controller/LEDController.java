/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.CorLED;
import br.com.cams7.sisbarc.aal.service.LEDService;
import br.com.cams7.sisbarc.aal.validator.PinoValidator;

/**
 * Principal componente do framework <code>Spring MVC</code>, esse e o
 * controller do cadastro de LEDs.
 * 
 * <p>
 * Tem como responsabilidade: definir o mapeamento de navegacao, acionar
 * validadores e conversores de dados, fornecer e receber os dados da camada de
 * visao (<code>JSP</code>).
 * </p>
 * 
 * <p>
 * Os metodos de navegacao, retornam a url definida no Tiles. Veja tambem o
 * arquivo <code>views.xml</code>.
 * </p>
 * 
 * @author cams7
 *
 */
@Controller
public class LEDController extends AALController<LEDService, LEDEntity> {

	private static final String ATTRIBUTE_ENTITY = "led";

	private final String ATTRIBUTE_ENTITIES = "leds";

	public static final String PAGE_MAIN = "/" + ATTRIBUTE_ENTITY;

	private final String PAGE_LIST = "listar_leds";
	private final String PAGE_INCLUDE = "incluir_led";
	private final String PAGE_EDIT = "editar_led";

	@Autowired
	public LEDController(PinoValidator validator) {
		super(validator);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN, method = RequestMethod.GET)
	public String listar(Model uiModel) {
		String page = super.listar(uiModel);
		return page;
	}

	@Override
	@RequestMapping(value = PAGE_MAIN, params = PARAM_FORM, method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		String page = super.criarForm(uiModel);
		return page;
	}

	@Override
	@RequestMapping(value = "/" + PAGE_INCLUDE, method = RequestMethod.POST)
	public String criar(@Valid @ModelAttribute(ATTRIBUTE_ENTITY) LEDEntity led,
			BindingResult result, Model uiModel, Locale locale) {
		if (result.getFieldErrorCount("ativo") == 0
				&& led.getAtivadoPorBotao() == Boolean.FALSE)
			led.setAtivo(Boolean.TRUE);

		String page = super.criar(led, result, uiModel, locale);

		if (page.equals(PAGE_LIST)) {
			PinoKey key = led.getPino();
			addINFOMessage(
					uiModel,
					getMessageSource().getMessage("msg.ok.summary.salvar.led",
							new Object[] {}, locale),
					getMessageSource().getMessage("msg.ok.detail.salvar.led",
							new Object[] { key.getTipo(), key.getCodigo() },
							locale));

		}

		return page;
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", params = PARAM_FORM, method = RequestMethod.GET)
	public String editarForm(@PathVariable(VARIABLE_ID) Long id, Model uiModel) {
		String page = super.editarForm(id, uiModel);
		return page;
	}

	@Override
	@RequestMapping(value = "/" + PAGE_EDIT, method = RequestMethod.PUT)
	public String atualizaPino(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) LEDEntity led,
			BindingResult result, Model uiModel, Locale locale) {
		String page = super.atualizaPino(led, result, uiModel, locale);
		return page;
	}

	@Override
	@RequestMapping(value = "/atualizar_leds", method = RequestMethod.PUT)
	public String atualizaPinos(Model uiModel, Locale locale) {
		String page = super.atualizaPinos(uiModel, locale);
		return page;
	}

	@Override
	@RequestMapping(value = "/sincronizar_leds", method = RequestMethod.PUT)
	public String sincronizaPinos(Model uiModel, Locale locale) {
		String page = super.sincronizaPinos(uiModel, locale);
		return page;
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", method = RequestMethod.DELETE)
	public String remover(@PathVariable(VARIABLE_ID) Long id, Model uiModel,
			Locale locale) {
		String page = super.remover(id, uiModel, locale);

		if (page.equals(PAGE_LIST))
			addINFOMessage(
					uiModel,
					getMessageSource().getMessage("msg.ok.summary.remover.led",
							new Object[] {}, locale),
					getMessageSource().getMessage("msg.ok.detail.remover.led",
							new Object[] { id }, locale));

		return page;
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/synch", method = RequestMethod.GET)
	public String atualizar(Model uiModel) {
		String page = super.atualizar(uiModel);
		return page;
	}

	@ModelAttribute("eventos")
	public Evento[] populateEventos() {
		Evento[] eventos = new Evento[3];
		eventos[0] = Evento.ACENDE_APAGA;
		eventos[1] = Evento.PISCA_PISCA;
		eventos[2] = Evento.FADE;

		return eventos;
	}

	@ModelAttribute("cores")
	public CorLED[] populateCores() {
		return CorLED.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.gae.controller.AbstractAppController#getAttributeEntity()
	 */
	@Override
	protected String getAttributeEntity() {
		return ATTRIBUTE_ENTITY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.gae.controller.AbstractAppController#getAttributeEntities()
	 */
	@Override
	protected String getAttributeEntities() {
		return ATTRIBUTE_ENTITIES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageList()
	 */
	@Override
	protected String getPageList() {
		return PAGE_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageInclude()
	 */
	@Override
	protected String getPageInclude() {
		return PAGE_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageEdit()
	 */
	@Override
	protected String getPageEdit() {
		return PAGE_EDIT;
	}

}
