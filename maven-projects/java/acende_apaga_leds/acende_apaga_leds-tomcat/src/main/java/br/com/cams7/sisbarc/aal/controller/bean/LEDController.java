/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.CorLED;
import br.com.cams7.sisbarc.aal.service.LEDService;

/**
 * @author cams7
 *
 */
@Controller(LEDController.CONTROLLER_NAME)
@ManagedBean(name = LEDController.CONTROLLER_NAME)
@ViewScoped
public class LEDController extends AALController<LEDService, LEDEntity> {

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
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.led"),
				getMessageFromI18N("msg.ok.detail.salvar.led",
						getSelectedEntity().getPino().getTipo().name(),
						getSelectedEntity().getPino().getCodigo()));

		return listPage;
	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.led"),
				getMessageFromI18N("msg.ok.detail.remover.led",
						getSelectedEntity().getPino().getTipo().name(),
						getSelectedEntity().getPino().getCodigo()));
	}

	/**
	 * @return
	 */
	public CorLED[] getCores() {
		return CorLED.values();
	}

	@Override
	public Evento[] getEventos() {
		Evento[] eventos = new Evento[3];
		eventos[0] = Evento.ACENDE_APAGA;
		eventos[1] = Evento.PISCA_PISCA;
		eventos[2] = Evento.FADE;

		return eventos;
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
