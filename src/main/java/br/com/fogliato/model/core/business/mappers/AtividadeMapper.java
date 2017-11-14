package br.com.fogliato.model.core.business.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fogliato.controller.messenger.dto.AtividadeDto;
import br.com.fogliato.model.core.domain.entities.Atividade;

@Mapper
public interface AtividadeMapper {

    AtividadeMapper MAPPER = Mappers.getMapper(AtividadeMapper.class);

    Atividade atividadeDtoToEntity(AtividadeDto atividadeDto);

    AtividadeDto atividadeEntityToDto(Atividade atividade);

    List<AtividadeDto> atividadeEntityListToDtoList(List<Atividade> atividades);
}
