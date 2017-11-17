package br.com.fogliato.core.business;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fogliato.core.domain.entities.Atividade;
import br.com.fogliato.core.dto.AtividadeDto;

@Mapper
public interface AtividadeMapper {

    AtividadeMapper MAPPER = Mappers.getMapper(AtividadeMapper.class);

    Atividade atividadeDtoToEntity(AtividadeDto atividadeDto);

    AtividadeDto atividadeEntityToDto(Atividade atividade);

    List<AtividadeDto> atividadeEntityListToDtoList(List<Atividade> atividades);
}
