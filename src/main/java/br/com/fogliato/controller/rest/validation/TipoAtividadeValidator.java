package br.com.fogliato.controller.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.fogliato.model.core.domain.enums.TipoAtividade;

public class TipoAtividadeValidator implements ConstraintValidator<CheckTipoAtividade, String> {

    @Override
    public void initialize(CheckTipoAtividade constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }

        TipoAtividade tipoAtividade = TipoAtividade.valueOf(value);

        return tipoAtividade != null;
    }

}
