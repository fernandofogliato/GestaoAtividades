package br.com.fogliato.core.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fogliato.core.domain.converters.TipoAtividadeConverter;
import br.com.fogliato.core.domain.enums.TipoAtividade;

@NamedQueries({
	@NamedQuery(name = Atividade.NQ_ATIVIDADES_EM_ABERTO,
		query = "SELECT a FROM Atividade a WHERE a.dataConclusao IS NULL ORDER BY a.dataCriacao"
	),
	@NamedQuery(name = Atividade.NQ_ATIVIDADES_CONCLUIDAS,
		query = "SELECT a FROM Atividade a WHERE a.dataConclusao IS NOT NULL ORDER BY a.dataConclusao"
	)	
})
@Entity
@Table(name="atividade")
public class Atividade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String NQ_ATIVIDADES_EM_ABERTO = "NQ_ATIVIDADES_EM_ABERTO";
	public static final String NQ_ATIVIDADES_CONCLUIDAS = "NQ_ATIVIDADES_CONCLUIDAS";
	
	@Id
	@Column(name = "id_atividade", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_atividade")	
	@SequenceGenerator(name = "seq_atividade", sequenceName = "seq_atividade", allocationSize=1)
	private Long idAtividade;
	
	@Column(name = "titulo", length=50, nullable = false)
	private String titulo;
	
	@Column(name = "descricao", length=500, nullable = false)
	private String descricao;
	
	@Column(name = "tipo_atividade")
	@Convert(converter = TipoAtividadeConverter.class)
	private TipoAtividade tipoAtividade;
	
	@Column(name = "data_criacao", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Column(name = "data_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
}
