package per.budictreas.springmvc.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO implements Serializable {
    private String username;
    private String password;
    private String lastname;
    private boolean role;
}
