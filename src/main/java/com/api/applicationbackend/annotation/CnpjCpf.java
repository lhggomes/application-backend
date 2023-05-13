package com.api.applicationbackend.annotation;

import com.api.applicationbackend.validators.CnpjCpfValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {CnpjCpfValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CnpjCpf {

    String message() default "CNPJ/CPF is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
