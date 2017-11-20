package br.com.fogliato.core.service;

import java.util.List;

import javax.inject.Inject;

import br.com.fogliato.core.business.AtividadeBusiness;
import br.com.fogliato.core.dto.AtividadeDto;
import br.com.fogliato.core.exception.AtividadeException;

/** 
 * @author Fernando Fogliato
 *
 */
public class AtividadeService {

    @Inject
    private AtividadeBusiness atividadeBusiness;

    public AtividadeDto inserirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        return atividadeBusiness.inserirAtividade(atividadeDto);
    }

    public AtividadeDto alterarAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        return atividadeBusiness.alterarAtividade(atividadeDto);
    }

    public void removerAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        atividadeBusiness.removerAtividade(atividadeDto);
    }

    public AtividadeDto getAtividadeById(Long idAtividade) throws AtividadeException {
        return atividadeBusiness.getAtividadeById(idAtividade);
    }

    public List<AtividadeDto> getAtividadesEmAberto() {
        return atividadeBusiness.getAtividadesEmAberto();
    }

    public List<AtividadeDto> getAtividadesConcluidas() {
        return atividadeBusiness.getAtividadesConcluidas();
    }

    public AtividadeDto concluirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        return atividadeBusiness.concluirAtividade(atividadeDto);
    }

    public AtividadeDto reabrirAtividade(Long idAtividade) throws AtividadeException {
        return atividadeBusiness.reabrirAtividade(idAtividade);
    }
}
