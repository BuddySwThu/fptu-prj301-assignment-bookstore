package per.budictreas.springmvc.repository.PG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import per.budictreas.springmvc.data.entity.RegistrationEntity;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<RegistrationEntity, String> {
    long countByUsernameAndPassword(String username, String password);

    RegistrationEntity findByUsername(String username);

    List<RegistrationEntity> getByLastnameContainingIgnoreCase(String lastname);

    @Transactional("pgSQLTransactionManager")
    Integer deleteByUsername(String username);
}
