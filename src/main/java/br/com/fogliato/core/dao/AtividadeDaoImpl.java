package br.com.fogliato.core.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.fogliato.core.domain.entities.Atividade;
import br.com.fogliato.core.exception.AtividadeException;

public class AtividadeDaoImpl extends GenericDaoImpl implements AtividadeDao {

	private static final long serialVersionUID = 1L;

	@Override
    public Atividade findById(Long idAtividade) throws AtividadeException {
        return entityManager.find(Atividade.class, idAtividade);
    }

    @Override
    public List<Atividade> getAtividadesEmAberto() {
        TypedQuery<Atividade> query = entityManager.createNamedQuery(Atividade.NQ_ATIVIDADES_EM_ABERTO, Atividade.class);
        return query.getResultList();
    }

    @Override
    public List<Atividade> getAtividadesConcluidas() {
        TypedQuery<Atividade> query = entityManager.createNamedQuery(Atividade.NQ_ATIVIDADES_CONCLUIDAS, Atividade.class);
        return query.getResultList();
    }

    @Override
    public void inserir(Atividade atividade) {
        persist(atividade);
    }

    @Override
    public void alterar(Atividade atividade) {
        merge(atividade);
    }

    @Override
    public void remover(Atividade atividade) {
        remove(atividade);
    }

}
