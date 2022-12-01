package per.budictreas.springmvc.common.validator;

import per.budictreas.springmvc.validator.ConfirmPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPasswordNotMatch {
    String message() default "confirm password mismatched !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
