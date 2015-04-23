package br.com.cams7.sisbarc.aal.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.cams7.gae.ds.DataSource;
import br.com.yaw.spgae.model.Mercadoria;

/**
 * Define um <code>DataSource</code> para entidade (<code>Model</code>)
 * Mercadoria.
 * 
 * <p>
 * As operações de manipulação de dados no App Engine ocorrem de forma
 * assíncrona. Antes de efetivar a mudança em todas as instâncias uma consulta
 * pode trazer dados "sujos" (eventually consistent), a proposta do datasource é
 * manter uma replica dos dados na sessão web, para refletir as mudanças
 * recentes do usuário.
 * </p>
 * 
 * <p>
 * Mantém as mercadorias indexadas pelo <code>id</code> (<code>Long</code>) em
 * um <code>HashMap</code>.
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class MercadoriaDataSource implements Serializable,
		DataSource<Mercadoria, Long> {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(MercadoriaDataSource.class);

	private Map<Long, Mercadoria> data = new LinkedHashMap<Long, Mercadoria>();

	@Override
	public void save(Mercadoria mercadoria) {
		if (mercadoria != null)
			data.put(mercadoria.getId(), mercadoria);

		updateSession();
	}

	@Override
	public Mercadoria update(Mercadoria mercadoria) {
		save(mercadoria);
		return mercadoria;
	}

	@Override
	public Mercadoria remove(Mercadoria mercadoria) {
		if (mercadoria != null)
			data.remove(mercadoria.getId());

		updateSession();
		return mercadoria;
	}

	@Override
	public Mercadoria findOne(Long id) {
		Mercadoria mercadoria = data.get(id);
		return mercadoria;
	}

	@Override
	public List<Mercadoria> findAll() {
		List<Mercadoria> mercadorias = new ArrayList<Mercadoria>(data.values());
		return mercadorias;
	}

	@Override
	public void synch(List<Mercadoria> mercadorias) {
		log.debug("Sincronizando datasource de mercadorias...");
		data.clear();

		if (mercadorias == null)
			return;

		for (Mercadoria mercadoria : mercadorias)
			if (mercadoria != null)
				data.put(mercadoria.getId(), mercadoria);

		updateSession();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * Pede ao App Engine para atualizar a sessão.
	 */
	private void updateSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("forceGaeSessionSerialization",
				System.currentTimeMillis());
	}

}
