package br.com.fogliato.controller.rest.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.fogliato.controller.messenger.dto.MessageDto;
import br.com.fogliato.model.core.exceptions.AtividadeException;

@Provider
public class AtividadeExceptionMapper implements ExceptionMapper<AtividadeException> {

    @Override
    public Response toResponse(AtividadeException exception) {
        return Response.serverError().entity(new MessageDto(exception.getMessage())).build();
    }

}
