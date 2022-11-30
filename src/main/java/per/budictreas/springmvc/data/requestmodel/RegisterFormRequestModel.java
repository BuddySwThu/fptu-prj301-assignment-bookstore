package per.budictreas.springmvc.data.requestmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import per.budictreas.springmvc.common.validator.ConfirmPasswordNotMatch;
import per.budictreas.springmvc.common.validator.UsernameExisted;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfirmPasswordNotMatch
public class RegisterFormRequestModel implements Serializable {
    @Size(min = 6, max = 20)
    @UsernameExisted
    private String username;
    @Size(min = 8, max = 30)
    private String password;
    private String confirmPassword;
    @Size(min = 2, max = 50)
    private String lastname;
}
