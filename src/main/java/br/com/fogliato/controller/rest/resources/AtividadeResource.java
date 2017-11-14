package br.com.fogliato.controller.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fogliato.controller.messenger.dto.AtividadeDto;
import br.com.fogliato.model.core.exceptions.AtividadeException;
import br.com.fogliato.model.core.service.AtividadeService;

@Path("/atividade")
public class AtividadeResource {

    @Inject
    private AtividadeService atividadeService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarAtividade(@Valid AtividadeDto atividadeDto) throws AtividadeException {
    	
        AtividadeDto atividadeCriada = atividadeService.inserirAtividade(atividadeDto);
        return Response.ok(atividadeCriada).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarAtividade(@Valid AtividadeDto atividadeDto) throws AtividadeException {
    	
        AtividadeDto atividadeAtualizada = atividadeService.alterarAtividade(atividadeDto);
        return Response.ok(atividadeAtualizada).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerAtividade(@Valid AtividadeDto atividadeDto) throws AtividadeException {
    	
        atividadeService.removerAtividade(atividadeDto);
        return Response.ok().build();
    }

    @Path("/{id}/concluir")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response concluirAtividade(@PathParam("id") final Long idAtividade) throws AtividadeException {
    	
        atividadeService.concluirAtividade(idAtividade);
        return Response.ok().build();
    }
    
    @Path("/{id}/reabrir")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reabrirAtividade(@PathParam("id") final Long idAtividade) throws AtividadeException {
    	
        AtividadeDto atividadeReaberta = atividadeService.reabrirAtividade(idAtividade);
        return Response.ok(atividadeReaberta).build();
    }

    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtividadeById(@PathParam("id") final Long idAtividade) throws AtividadeException {
    	
        AtividadeDto atividade = atividadeService.getAtividadeById(idAtividade);
        return Response.ok(atividade).build();
    }

    @Path("/abertas")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtividadesEmAberto() {
    	
        List<AtividadeDto> atividades = atividadeService.getAtividadesEmAberto();
        return Response.ok(atividades).build();
    }
    
    @Path("/concluidas")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtividadesConcluidas() {
    	
        List<AtividadeDto> atividades = atividadeService.getAtividadesConcluidas();
        return Response.ok(atividades).build();
    }

}
