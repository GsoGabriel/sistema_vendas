package io.github.gsogabriel.validation;

import io.github.gsogabriel.validation.constraintValidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // diz que ele vai verificar em tempo de execução
@Target(ElementType.FIELD) // o alvo (Target) da annotation são os campos(Field)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
    String message() default "A lista não pode ser vazia.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
