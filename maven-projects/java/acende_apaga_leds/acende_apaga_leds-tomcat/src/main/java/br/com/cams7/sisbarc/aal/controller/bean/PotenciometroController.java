/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.service.PotenciometroService;

/**
 * @author cams7
 *
 */
@Controller(PotenciometroController.CONTROLLER_NAME)
@ManagedBean(name = PotenciometroController.CONTROLLER_NAME)
@ViewScoped
public class PotenciometroController extends
		AALController<PotenciometroService, PotenciometroEntity, String> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "potenciometroMB";

	private final String LIST_PAGE = "listaPotenciometros";

	/**
	 * 
	 */
	public PotenciometroController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.potenciometro"),
				getMessageFromI18N("msg.ok.detail.salvar.potenciometro",
						getSelectedEntity().getPino().getTipo().name(),
						getSelectedEntity().getPino().getCodigo()));

		return listPage;
	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.potenciometro"),
				getMessageFromI18N("msg.ok.detail.remover.potenciometro",
						getSelectedEntity().getPino().getTipo().name(),
						getSelectedEntity().getPino().getCodigo()));
	}

	@Override
	public Evento[] getEventos() {
		Evento[] eventos = new Evento[2];
		eventos[0] = Evento.ACENDE_APAGA;
		eventos[1] = Evento.NENHUM;

		return eventos;
	}

	@Override
	public Intervalo[] getIntervalos() {
		Intervalo[] intervalos = new Intervalo[4];
		intervalos[0] = Intervalo.INTERVALO_100MILISEGUNDOS;
		intervalos[1] = Intervalo.INTERVALO_1SEGUNDO;
		intervalos[2] = Intervalo.INTERVALO_3SEGUNDOS;
		intervalos[3] = Intervalo.SEM_INTERVALO;
		return intervalos;
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
