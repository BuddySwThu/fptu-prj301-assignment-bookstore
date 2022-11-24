package per.budictreas.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.budictreas.springmvc.data.entity.RegistrationEntity;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, String> {
    long countByUsernameAndPassword(String username, String password);

    RegistrationEntity findByUsername(String username);

    List<RegistrationEntity> getByLastnameContainingIgnoreCase(String lastname);
}
