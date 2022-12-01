package per.budictreas.springmvc.data.requestmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import per.budictreas.springmvc.common.validator.ConfirmPasswordNotMatch;
import per.budictreas.springmvc.common.validator.UsernameExisted;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfirmPasswordNotMatch
public class RegisterFormRequestModel implements Serializable {
    @UsernameExisted
    private String username;
    private String password;
    private String confirmPassword;
    private String lastname;
}
