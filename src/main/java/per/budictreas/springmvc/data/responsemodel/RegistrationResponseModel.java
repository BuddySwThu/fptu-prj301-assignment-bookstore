package per.budictreas.springmvc.data.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseModel implements Serializable {
    private String username;
    private String password;
    private String lastname;
    private boolean role;
}
