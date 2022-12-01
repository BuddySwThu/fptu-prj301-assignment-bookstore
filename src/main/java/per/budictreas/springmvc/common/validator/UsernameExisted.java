package per.budictreas.springmvc.common.validator;

import per.budictreas.springmvc.validator.UsernameExistedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameExistedValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameExisted {
    String message() default "username has been existed !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
