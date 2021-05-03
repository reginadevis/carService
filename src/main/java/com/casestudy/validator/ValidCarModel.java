package com.casestudy.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CarModelValidator.class })
@Documented
public @interface ValidCarModel {
	String message() default "invalid.model";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
