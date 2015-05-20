package br.com.cams7.sisbarc.aal.domain.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cams7.domain.BaseEntity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Classe de modelo que representa uma mercadoria. A mercadoria é um objeto
 * persistido, por isso utilizamos o nome entidade.
 * 
 * <p>
 * As funcionalidades desse sistema demonstração são concentradas no cadastro
 * (CRUD) de mercadorias.
 * </p>
 * 
 * <p>
 * Essa entidade é mapeada com anotações do <code>Objectify</code>, um framework
 * para persistência alto-nível no datastore (mecanismo de persistência do
 * <code>App Engine</code>).
 * </p>
 * 
 * @author YaW Tecnologia
 */
@Entity
public class MercadoriaEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@NotNull
	@Size(min = 5, max = 30)
	private String nome;

	@Size(min = 5, max = 255)
	private String descricao;

	@NotNull
	@Min(value = 1)
	private Integer quantidade;

	@NotNull
	@Min(value = 1)
	private Double preco;

	public MercadoriaEntity() {
		super();
	}

	public MercadoriaEntity(Long id) {
		super(id);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [id = " + getId() + ", nome = "
				+ getNome() + ", descricao = " + getDescricao()
				+ ", quantidade = " + getQuantidade() + ", preco = "
				+ getPreco() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
