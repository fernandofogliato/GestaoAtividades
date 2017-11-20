package br.com.fogliato.rest.gson;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.fogliato.rest.gson.adapter.LocalDateAdapter;
import br.com.fogliato.rest.gson.adapter.LocalDateTimeAdapter;

/**
 * Classe utilizado pelo CDI para criação de uma instância do {@link Gson}. 
 * Responsável também por realizar o registro dos adaptadores utilizados para conversão de datas e horas no formato JSON.
 * 
 * @author Fernando Fogliato
 */
public class GsonProducer {
	
    private void registrarAdapters(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				   .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    }

    @Produces
    public Gson getResourceBundle(InjectionPoint injectionPoint) {
    	
		GsonBuilder gsonBuilder = new GsonBuilder();
		registrarAdapters(gsonBuilder);
		gsonBuilder.setPrettyPrinting();

		return gsonBuilder.serializeNulls().create();
    }

}
