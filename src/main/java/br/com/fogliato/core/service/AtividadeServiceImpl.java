package br.com.fogliato.core.service;

import java.util.List;

import javax.inject.Inject;

import br.com.fogliato.core.business.AtividadeBusiness;
import br.com.fogliato.core.dto.AtividadeDto;
import br.com.fogliato.core.exception.AtividadeException;

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
    public AtividadeDto concluirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        return atividadeBusiness.concluirAtividade(atividadeDto);
    }

    @Override
    public AtividadeDto reabrirAtividade(Long idAtividade) throws AtividadeException {
        return atividadeBusiness.reabrirAtividade(idAtividade);
    }
}
