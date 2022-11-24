package per.budictreas.springmvc.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TblRegistration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationEntity implements Serializable {
    @Id
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "isAdmin")
    private boolean role;
}
