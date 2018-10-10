package com.lbw.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Author by lbw , Date on 2018/10/10.
 */

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =MyConstraintValdate.class )
public @interface MyConstraint {

  String message() default "{javax.validation.constraints.NotBlank.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
