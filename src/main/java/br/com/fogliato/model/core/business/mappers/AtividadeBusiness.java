package br.com.fogliato.model.core.business.mappers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.fogliato.controller.messenger.dto.AtividadeDto;
import br.com.fogliato.model.core.domain.entities.Atividade;
import br.com.fogliato.model.core.domain.enums.TipoAtividade;
import br.com.fogliato.model.core.exceptions.AtividadeException;
import br.com.fogliato.model.persistence.dao.AtividadeDao;

public class AtividadeBusiness {

    private static final int HORA_LIMITE_MANUTENCAO_URGENTE = 13;
    private static final int QTD_CARACTERES_MINIMO_DESC_ATIVIDADE = 50;

    @Inject
    private AtividadeDao atividadeDao;

    @Inject
    private AtividadeMapper atividadeMapper;

    public AtividadeDto inserirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
        validarHorarioAtividade(atividadeDto);

        Atividade atividade = atividadeMapper.atividadeDtoToEntity(atividadeDto);
        atividade.setDataCriacao(new Date());

        atividadeDao.inserir(atividade);

        return atividadeMapper.atividadeEntityToDto(atividade);
    }

    public AtividadeDto alterarAtividade(AtividadeDto atividadeDto) throws AtividadeException {
    	
        Atividade atividadeDatabase = atividadeDao.findById(atividadeDto.getIdAtividade());
        
        // Verifica se foi modificado o tipo da atividade, se sim, 
        // verifica se a atividade está respeitando o horário para criação/alteração de determinados tipos de atividades.
        if (!atividadeDatabase.getTipoAtividade().toString().equals(atividadeDto.getTipoAtividade())) {
            validarHorarioAtividade(atividadeDto);
        }

        Atividade atividade = atividadeMapper.atividadeDtoToEntity(atividadeDto);
        atividadeDao.alterar(atividade);
        return atividadeMapper.atividadeEntityToDto(atividade);
    }

    public void concluirAtividade(Long idAtividade) throws AtividadeException {
    	
        Atividade atividade = atividadeDao.findById(idAtividade);
        
        if (atividade == null) {
            throw new AtividadeException("Não foi encontrada nenhuma atividade com o id " + idAtividade + ". Por favor, verifique.");
        }

        validarConclusao(atividade);
        atividade.setDataConclusao(new Date());
        atividadeDao.alterar(atividade);
    }

    public void removerAtividade(AtividadeDto atividadeDto) throws AtividadeException {
    	
        Atividade atividade = atividadeDao.findById(atividadeDto.getIdAtividade());

        if (atividade == null) {
            throw new AtividadeException("Não foi encontrado nenhuma atividade com o id " + atividadeDto.getIdAtividade() 
            	+ " para ser removida. Por favor, verifique.");
        }

        validarRemocao(atividade);
        atividadeDao.remover(atividade);
    }

    public AtividadeDto reabrirAtividade(Long idAtividade) throws AtividadeException {
    	
        Atividade atividade = atividadeDao.findById(idAtividade);
        
        validarHorarioAtividade(atividadeMapper.atividadeEntityToDto(atividade));

        if (atividade == null) {
            throw new AtividadeException("Não foi encontrado atividade com id " + idAtividade + " para ser reaberta.");
        }

        atividade.setDataConclusao(null);
        atividadeDao.alterar(atividade);

        return atividadeMapper.atividadeEntityToDto(atividade);
    }

    public List<AtividadeDto> getAtividadesEmAberto() {
        return atividadeMapper.atividadeEntityListToDtoList(atividadeDao.getAtividadesEmAberto());
    }

    public List<AtividadeDto> getAtividadesConcluidas() {
        return atividadeMapper.atividadeEntityListToDtoList(atividadeDao.getAtividadesConcluidas());
    }

    public AtividadeDto getAtividadeById(Long idAtividade) throws AtividadeException {
        Atividade atividade = atividadeDao.findById(idAtividade);

        if (atividade == null) {
            throw new AtividadeException("Não foi encontrado atividade com id " + idAtividade + ". Por favor, verifique.");
        }

        return atividadeMapper.atividadeEntityToDto(atividade);
    }

    private void validarRemocao(Atividade atividade) throws AtividadeException {
        if (atividade.getTipoAtividade() == TipoAtividade.MANUTENCAO_URGENTE) {
            throw new AtividadeException("Atividades do tipo \"Manutenção Urgente\" não podem ser removidas, apenas concluídas."); 
        }
    }

    private void validarConclusao(Atividade atividade) throws AtividadeException {
    	
    	boolean isAtendimento = atividade.getTipoAtividade() == TipoAtividade.ATENDIMENTO;
    	boolean isManutencaoUrgente = atividade.getTipoAtividade() == TipoAtividade.MANUTENCAO_URGENTE;
    	boolean isDescricaoMenorLimite = atividade.getDescricao().length() < QTD_CARACTERES_MINIMO_DESC_ATIVIDADE;
    	
        if ((isAtendimento || isManutencaoUrgente) && isDescricaoMenorLimite) {
        	throw new AtividadeException("Atividades do tipo \"Atendimento\" e \"Manutenção Urgente\""
                		+ " exigem mais de 50 caracteres na descrição. Por favor, verifique.");
        }
    }

    private void validarHorarioAtividade(AtividadeDto atividadeDto) throws AtividadeException {
    	
        if (atividadeDto.getTipoAtividade().equals(TipoAtividade.MANUTENCAO_URGENTE.toString())) {
            Calendar calendar = Calendar.getInstance();
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && calendar.get(Calendar.HOUR_OF_DAY) >= HORA_LIMITE_MANUTENCAO_URGENTE) {
                throw new AtividadeException("Não é possível criar/editar/reabrir atividades do tipo \"Manutenção Urgente\" após às 13:00 hrs de sexta-feira.");
            }
        }
    }
}
