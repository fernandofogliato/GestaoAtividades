package br.com.fogliato.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.fogliato.core.dto.MensagemDto;
import br.com.fogliato.core.exception.AtividadeException;

@Provider
public class AtividadeExceptionMapper implements ExceptionMapper<AtividadeException> {

    @Override
    public Response toResponse(AtividadeException exception) {
        return Response.serverError().entity(new MensagemDto(exception.getMessage())).build();
    }

}