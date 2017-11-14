package br.com.fogliato.model.persistence.dao;

import java.util.List;

import br.com.fogliato.model.core.domain.entities.Atividade;
import br.com.fogliato.model.core.exceptions.AtividadeException;

public interface AtividadeDao {

    void inserir(Atividade atividade);

    void alterar(Atividade atividade);

    void remover(Atividade atividade);

    Atividade findById(Long idAtividade) throws AtividadeException;

    List<Atividade> getAtividadesEmAberto();

    List<Atividade> getAtividadesConcluidas();
}
