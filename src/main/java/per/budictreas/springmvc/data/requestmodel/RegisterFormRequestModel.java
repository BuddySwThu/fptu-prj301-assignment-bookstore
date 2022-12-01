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
    //@Length(min = 6, max = 20)
    private String username;
    //@Length(min = 8, max = 30)
    private String password;
    private String confirmPassword;
    //@Length(min = 2, max = 50)
    private String lastname;
}
