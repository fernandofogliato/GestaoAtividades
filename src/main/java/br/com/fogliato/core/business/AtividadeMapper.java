package br.com.fogliato.core.business;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fogliato.core.domain.entities.Atividade;
import br.com.fogliato.core.dto.AtividadeDto;

/**
 *
 * Interface responsável por definir métodos para conversão de Data Transfer Object (DTO) para entidades (Entity).
 * 
 * A implementação dessa interface fica a cargo do MapStruct.
 * 
 * @author Fernando Fogliato
 *
 */
@Mapper
public interface AtividadeMapper {

    AtividadeMapper MAPPER = Mappers.getMapper(AtividadeMapper.class);

    Atividade atividadeDtoParaEntidade(AtividadeDto atividadeDto);

    AtividadeDto entidadeAtividadeParaDto(Atividade atividade);

    List<AtividadeDto> listaEntidadesAtividadeParaListaDto(List<Atividade> atividades);
}
