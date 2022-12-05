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
public class CustomRegistrationResponeModel implements Serializable {
    private String username;
    private String lastname;
    private boolean admin;
}
