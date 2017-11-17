package br.com.fogliato.core.service;

import java.util.List;

import br.com.fogliato.core.dto.AtividadeDto;
import br.com.fogliato.core.exception.AtividadeException;

public interface AtividadeService {

    AtividadeDto inserirAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    AtividadeDto alterarAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    void removerAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    AtividadeDto getAtividadeById(Long idAtividade) throws AtividadeException;

    List<AtividadeDto> getAtividadesEmAberto();

    List<AtividadeDto> getAtividadesConcluidas();

    AtividadeDto concluirAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    AtividadeDto reabrirAtividade(Long idAtividade) throws AtividadeException;
}
