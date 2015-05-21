/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Evento;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.CorLED;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.service.LEDService;
import br.com.cams7.webapp.AppController;

/**
 * @author cams7
 *
 */
@Controller(LEDController.CONTROLLER_NAME)
@ManagedBean(name = LEDController.CONTROLLER_NAME)
@ViewScoped
public class LEDController extends AppController<LEDService, LEDEntity, String> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "ledMB";

	private final String LIST_PAGE = "listaLEDs";

	/**
	 * 
	 */
	public LEDController() {
		super();
	}

	@Override
	public void includeNewEntity() {
		super.includeNewEntity();
		LEDEntity led = getSelectedEntity();

		Pino pino = new Pino();
		led.setPino(pino);
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.led"),
				getMessageFromI18N("msg.ok.detail.salvar.led",
						getSelectedEntity().getPino().getTipo() + " "
								+ getSelectedEntity().getPino().getCodigo()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.atualizar.led"),
				getMessageFromI18N("msg.ok.detail.atualizar.led",
						getSelectedEntity().getPino().getTipo() + " "
								+ getSelectedEntity().getPino().getCodigo()));

	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.led"),
				getMessageFromI18N("msg.ok.detail.remover.led",
						getSelectedEntity().getPino().getTipo() + " "
								+ getSelectedEntity().getPino().getCodigo()));
	}

	/**
	 * @return
	 */
	public ArduinoPinType[] getTipos() {
		return ArduinoPinType.values();
	}

	/**
	 * @return
	 */
	public Evento[] getEventos() {
		return Evento.values();
	}

	/**
	 * @return
	 */
	public CorLED[] getCores() {
		return CorLED.values();
	}

	/**
	 * @return
	 */
	public EstadoLED[] getEstados() {
		return EstadoLED.values();
	}

	/**
	 * @return
	 */
	public Intervalo[] getIntervalos() {
		return Intervalo.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.webapp.AppController#getListPage()
	 */
	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
