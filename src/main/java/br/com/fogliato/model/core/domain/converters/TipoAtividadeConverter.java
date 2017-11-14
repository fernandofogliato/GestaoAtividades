package br.com.fogliato.model.core.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.fogliato.model.core.domain.enums.TipoAtividade;

@Converter
public class TipoAtividadeConverter implements AttributeConverter<TipoAtividade, String> {

    public String convertToDatabaseColumn(TipoAtividade tipoAtividade) {
        if (tipoAtividade == null) {
            return null;
        }

        return tipoAtividade.getValue();
    }

    public TipoAtividade convertToEntityAttribute(String tipoAtividade) {
        if (tipoAtividade == null) {
            return null;
        }

        return TipoAtividade.fromType(tipoAtividade);
    }
}
