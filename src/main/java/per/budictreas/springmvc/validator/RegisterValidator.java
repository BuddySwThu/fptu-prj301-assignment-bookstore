package per.budictreas.springmvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import per.budictreas.springmvc.data.requestmodel.RegisterFormRequestModel;
import per.budictreas.springmvc.service.UserAccountService;

@Component("registerValidator")
public class RegisterValidator implements Validator {
    UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterFormRequestModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterFormRequestModel registerFormRequestModel = (RegisterFormRequestModel) target;

        String username = registerFormRequestModel.getUsername();
        String password = registerFormRequestModel.getPassword();

        int usernameLength = username.length();
        int passwordLength = password.length();
        int lastnameLength = registerFormRequestModel.getLastname().length();

        if (usernameLength < 6 || usernameLength > 20) {
            errors.rejectValue("username", "username-length", "username must be in 6 to 20 characters !");
        }

        if (passwordLength < 8 || passwordLength > 30) {
            errors.rejectValue("password", "password-length", "password must be in 8 to 30 characters !");
        }

        if (lastnameLength < 2 || lastnameLength > 50) {
            errors.rejectValue("lastname", "lastname-length", "lastname must be in 2 to 50 characters !");
        }
    }
}
