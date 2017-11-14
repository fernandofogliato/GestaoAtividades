package br.com.fogliato.controller.rest.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.fogliato.controller.rest.adapter.LocalDateAdapter;
import br.com.fogliato.controller.rest.adapter.LocalDateTimeAdapter;

public class GsonProducer {

    @Produces
    public Gson getResourceBundle(InjectionPoint injectionPoint) {
    	
		GsonBuilder gsonBuilder = new GsonBuilder();
		applyCustomAdapters(gsonBuilder);
		gsonBuilder.setPrettyPrinting();

		return gsonBuilder.serializeNulls().create();
    }

    private void applyCustomAdapters(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    }

}
