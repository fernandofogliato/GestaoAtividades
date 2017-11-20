package br.com.fogliato.core.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.fogliato.core.dao.AtividadeDao;
import br.com.fogliato.core.domain.entities.Atividade;
import br.com.fogliato.core.domain.enums.TipoAtividade;
import br.com.fogliato.core.dto.AtividadeDto;
import br.com.fogliato.core.exception.AtividadeException;

/**
 * 
 * Classe responsável por implementar as regras de negócio envolvendo atividades.
 * 
 * @author Fernando Fogliato
 *
 */
public class AtividadeBusiness {

    private static final int HORA_LIMITE_MANUTENCAO_URGENTE = 13;
    private static final int QTD_CARACTERES_MINIMO_DESC_ATIVIDADE = 50;

    @Inject
    private AtividadeDao atividadeDao;

    @Inject
    private AtividadeMapper atividadeMapper;

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
    	
        if (atividadeDto.getTipoAtividade() == TipoAtividade.MANUTENCAO_URGENTE) {
            validarHorarioManutencaoUrgente();
        }
    }

	private void validarHorarioManutencaoUrgente() throws AtividadeException {
		
		Calendar calendar = Calendar.getInstance();
		
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				
		boolean isFinalSemana = diaSemana == Calendar.FRIDAY || diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY;
		
		boolean isAposHorarioLimite = calendar.get(Calendar.HOUR_OF_DAY) >= HORA_LIMITE_MANUTENCAO_URGENTE; 
		
		if (isFinalSemana && isAposHorarioLimite) {
		    throw new AtividadeException("Não é possível criar/reabrir atividades do tipo "
		    		+ "\"Manutenção Urgente\" após às 13:00 hrs de sexta-feira.");
		}
	}
	
    public AtividadeDto inserirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
    	
        validarHorarioAtividade(atividadeDto);

        Atividade atividade = atividadeMapper.atividadeDtoParaEntidade(atividadeDto);
        atividade.setDataCriacao(new Date());
        atividadeDao.inserir(atividade);

        return atividadeMapper.entidadeAtividadeParaDto(atividade);
    }

    public AtividadeDto alterarAtividade(AtividadeDto atividadeDto) throws AtividadeException {
    	
        Atividade atividadeBanco = atividadeDao.findById(atividadeDto.getIdAtividade());
        
        // Verifica se foi modificado o tipo da atividade para então verificar  
        // se a atividade está respeitando o horário para criação/alteração de determinados tipos de atividades.
        if (atividadeBanco.getTipoAtividade() != atividadeDto.getTipoAtividade()) {
            validarHorarioAtividade(atividadeDto);
        }

        Atividade atividade = atividadeMapper.atividadeDtoParaEntidade(atividadeDto);
        atividadeDao.alterar(atividade);
        return atividadeMapper.entidadeAtividadeParaDto(atividade);
    }

    public AtividadeDto concluirAtividade(AtividadeDto atividadeDto) throws AtividadeException {
    	
    	Atividade atividade = atividadeMapper.atividadeDtoParaEntidade(atividadeDto);
    	
        validarConclusao(atividade);
        
        atividade.setDataConclusao(new Date());
        atividadeDao.alterar(atividade);
        
        return atividadeMapper.entidadeAtividadeParaDto(atividade);
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
        
        validarHorarioAtividade(atividadeMapper.entidadeAtividadeParaDto(atividade));

        if (atividade == null) {
            throw new AtividadeException("Não foi encontrado atividade com id " + idAtividade + " para ser reaberta.");
        }

        atividade.setDataConclusao(null);
        atividadeDao.alterar(atividade);

        return atividadeMapper.entidadeAtividadeParaDto(atividade);
    }

    public List<AtividadeDto> getAtividadesEmAberto() {
        return atividadeMapper.listaEntidadesAtividadeParaListaDto(atividadeDao.getAtividadesEmAberto());
    }

    public List<AtividadeDto> getAtividadesConcluidas() {
        return atividadeMapper.listaEntidadesAtividadeParaListaDto(atividadeDao.getAtividadesConcluidas());
    }

    public AtividadeDto getAtividadeById(Long idAtividade) throws AtividadeException {
    	
        Atividade atividade = atividadeDao.findById(idAtividade);

        if (atividade == null) {
            throw new AtividadeException("Não foi encontrado atividade com id " + idAtividade + ". Por favor, verifique.");
        }

        return atividadeMapper.entidadeAtividadeParaDto(atividade);
    }	
}
