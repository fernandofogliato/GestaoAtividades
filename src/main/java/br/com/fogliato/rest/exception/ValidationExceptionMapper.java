package br.com.fogliato.rest.exception;

import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import br.com.fogliato.core.dto.MensagemDto;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

    @Override
    public Response toResponse(ResteasyViolationException exception) {
        String message = exception.getParameterViolations()
                .stream()
                .map(ResteasyConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        return Response.serverError().entity(new MensagemDto(message)).build();
    }

}
