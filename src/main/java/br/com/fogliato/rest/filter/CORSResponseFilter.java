package br.com.fogliato.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Classe responsável por adicionar algumas definições no cabeçalho de resposta das operações. 
 * Conceito Cross-Origin Resource Sharing (CORS) que descreve alguns itens para leitura das informações por parte de um browser. 
 * 
 * @author Fernando Fogliato
 *
 */
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
    	
    	MultivaluedMap<String, Object> headers = responseContext.getHeaders();
    	
    	headers.add("Access-Control-Allow-Origin", "*");
    	headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    	headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization");
    }

}
