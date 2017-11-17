package br.com.fogliato.core.domain.entities;

import br.com.fogliato.model.core.domain.enums.TipoAtividade;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-17T13:53:44.235-0200")
@StaticMetamodel(Atividade.class)
public class Atividade_ {
	public static volatile SingularAttribute<Atividade, Long> idAtividade;
	public static volatile SingularAttribute<Atividade, String> titulo;
	public static volatile SingularAttribute<Atividade, String> descricao;
	public static volatile SingularAttribute<Atividade, TipoAtividade> tipoAtividade;
	public static volatile SingularAttribute<Atividade, Date> dataCriacao;
	public static volatile SingularAttribute<Atividade, Date> dataConclusao;
}
