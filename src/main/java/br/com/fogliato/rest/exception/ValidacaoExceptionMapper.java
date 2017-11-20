package br.com.fogliato.rest.exception;

import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import br.com.fogliato.core.dto.MensagemDto;

/**
 * 
 * Classe responsável por mapear exceções do Resteasy e criar uma mensagem do tipo {@link MensagemDto} 
 * para ser retornada pela operação.
 *
 * @author Fernando Fogliato
 */
@Provider
public class ValidacaoExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

    @Override
    public Response toResponse(ResteasyViolationException exception) {
    	
    	String mensagem = exception.getParameterViolations()
                .stream()
                .map(ResteasyConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        return Response.serverError().entity(new MensagemDto(mensagem)).build();
    }

}
