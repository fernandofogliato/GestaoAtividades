package br.com.fogliato.model.core.domain.entities;

import br.com.fogliato.model.core.domain.enums.TipoAtividade;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-14T07:32:32.806-0200")
@StaticMetamodel(Atividade.class)
public class Atividade_ {
	public static volatile SingularAttribute<Atividade, Integer> idAtividade;
	public static volatile SingularAttribute<Atividade, String> titulo;
	public static volatile SingularAttribute<Atividade, String> descricao;
	public static volatile SingularAttribute<Atividade, TipoAtividade> tipoAtividade;
	public static volatile SingularAttribute<Atividade, Date> dataCriacao;
	public static volatile SingularAttribute<Atividade, Date> dataConclusao;
}
