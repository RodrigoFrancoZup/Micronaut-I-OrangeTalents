package br.com.zup.annotation

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton

//ATENÇÃO, AQUI O ConstraintValidator TEM QUE SER DO PACOTE IO.MICRONAUT...
@Singleton
class NomeValidator : ConstraintValidator<Nome, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Nome>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null) {
            return true
        }
        return value.matches("[A-Z][a-z]* [A-Z][a-z]*".toRegex())
    }

}