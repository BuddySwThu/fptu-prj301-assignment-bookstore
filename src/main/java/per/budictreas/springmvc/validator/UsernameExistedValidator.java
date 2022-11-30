package per.budictreas.springmvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import per.budictreas.springmvc.common.validator.UsernameExisted;
import per.budictreas.springmvc.service.UserAccountService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameExistedValidator implements ConstraintValidator<UsernameExisted, String> {
    @Autowired
    UserAccountService userAccountService;

    @Override
    public void initialize(UsernameExisted constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userAccountService.isUsernameExisted(s);
    }
}
