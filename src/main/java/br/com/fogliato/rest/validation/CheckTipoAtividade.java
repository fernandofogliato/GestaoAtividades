package br.com.fogliato.rest.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoAtividadeValidator.class)
@Documented
public @interface CheckTipoAtividade {

    String message() default "O tipo de atividade informado n�o existe.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
