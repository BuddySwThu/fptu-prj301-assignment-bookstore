package per.budictreas.springmvc.data.requestmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestModel implements Serializable {
    private String username;
    @Length(min = 8, max = 30, message = "Password is required 8 to 30")
    private String password;
    @Length(min = 2, max = 40, message = "Last name is required 2 to 40")
    private String lastname;
    private boolean role;
}
