package br.com.fogliato.model.core.service;

import java.util.List;

import javax.inject.Inject;

import br.com.fogliato.controller.messenger.dto.AtividadeDto;
import br.com.fogliato.model.core.business.mappers.AtividadeBusiness;
import br.com.fogliato.model.core.exceptions.AtividadeException;

public class AtividadeServiceImpl implements AtividadeService {

    @Inject
    private AtividadeBusiness atividadeBusiness;

    @Override
    public AtividadeDto inserirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        return atividadeBusiness.inserirAtividade(atividadeDto);
    }

    @Override
    public AtividadeDto alterarAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        return atividadeBusiness.alterarAtividade(atividadeDto);
    }

    @Override
    public void removerAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        atividadeBusiness.removerAtividade(atividadeDto);
    }

    @Override
    public AtividadeDto getAtividadeById(Long idAtividade) throws AtividadeException {
        return atividadeBusiness.getAtividadeById(idAtividade);
    }

    @Override
    public List<AtividadeDto> getAtividadesEmAberto() {
        return atividadeBusiness.getAtividadesEmAberto();
    }

    @Override
    public List<AtividadeDto> getAtividadesConcluidas() {
        return atividadeBusiness.getAtividadesConcluidas();
    }

    @Override
    public void concluirAtividade(Long idAtividade) throws AtividadeException {
        atividadeBusiness.concluirAtividade(idAtividade);
    }

    @Override
    public AtividadeDto reabrirAtividade(Long idAtividade) throws AtividadeException {
        return atividadeBusiness.reabrirAtividade(idAtividade);
    }
}
