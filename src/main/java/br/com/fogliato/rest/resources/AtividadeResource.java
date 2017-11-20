package br.com.fogliato.rest.resources;

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

import br.com.fogliato.core.dto.AtividadeDto;
import br.com.fogliato.core.exception.AtividadeException;
import br.com.fogliato.core.service.AtividadeService;

/**
 * Classe responsável por fornecer operações REST para a manipulação de atividades.
 * 
 * @author Fernando Fogliato
 *
 */
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

    @Path("/concluir")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response concluirAtividade(@Valid AtividadeDto atividadeDto) throws AtividadeException {
    	
        AtividadeDto atividadeConcluida = atividadeService.concluirAtividade(atividadeDto);
        return Response.ok(atividadeConcluida).build();
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
