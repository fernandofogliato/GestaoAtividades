package br.com.fogliato.model.core.service;

import java.util.List;

import br.com.fogliato.controller.messenger.dto.AtividadeDto;
import br.com.fogliato.model.core.exceptions.AtividadeException;

public interface AtividadeService {

    AtividadeDto inserirAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    AtividadeDto alterarAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    void removerAtividade(AtividadeDto atividadeDto) throws AtividadeException;

    AtividadeDto getAtividadeById(Long idAtividade) throws AtividadeException;

    List<AtividadeDto> getAtividadesEmAberto();

    List<AtividadeDto> getAtividadesConcluidas();

    void concluirAtividade(Long idAtividade) throws AtividadeException;

    AtividadeDto reabrirAtividade(Long idAtividade) throws AtividadeException;
}
