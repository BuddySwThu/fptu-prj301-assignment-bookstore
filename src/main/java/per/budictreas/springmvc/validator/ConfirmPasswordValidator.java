package per.budictreas.springmvc.validator;

import per.budictreas.springmvc.common.validator.ConfirmPasswordNotMatch;
import per.budictreas.springmvc.data.requestmodel.RegisterFormRequestModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPasswordNotMatch, RegisterFormRequestModel> {
    @Override
    public void initialize(ConfirmPasswordNotMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RegisterFormRequestModel registerFormRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        return registerFormRequestModel.getPassword().equals(registerFormRequestModel.getConfirmPassword());
    }
}
