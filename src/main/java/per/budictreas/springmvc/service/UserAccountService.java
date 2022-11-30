package per.budictreas.springmvc.service;

import org.springframework.stereotype.Service;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.mapper.dtomapper.RegistrationDTOMapper;
import per.budictreas.springmvc.repository.UserAccountRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final RegistrationDTOMapper registrationDTOMapper;

    public UserAccountService(UserAccountRepository userAccountRepository, RegistrationDTOMapper registrationDTOMapper) {
        this.userAccountRepository = userAccountRepository;
        this.registrationDTOMapper = registrationDTOMapper;
    }

    public RegistrationEntity checkLogin(String username, String password) {
        boolean result = this.userAccountRepository.countByUsernameAndPassword(username, password) > 0;
        if (result) return this.userAccountRepository.findByUsername(username);
        return null;
    }

    public List<RegistrationDTO> searchByLastname(String lastname) {
        return this.registrationDTOMapper.toDTO(this.userAccountRepository.getByLastnameContainingIgnoreCase(lastname));
    }

    public boolean updateUser(RegistrationDTO registrationDTO) {
        RegistrationEntity registrationEntity = this.registrationDTOMapper.toEntity(registrationDTO);
        if (this.userAccountRepository.findById(registrationEntity.getUsername()).orElseThrow(EntityNotFoundException::new) != null)
            this.userAccountRepository.saveAndFlush(registrationEntity);

        return true;
    }

    public boolean deleteUseṛ(String username) {
        return this.userAccountRepository.deleteByUsername(username) == 1;
    }

    public void createUser(RegistrationDTO dto) {
        this.userAccountRepository.saveAndFlush(this.registrationDTOMapper.toEntity(dto));
    }

    public boolean isUsernameExisted(String username) {
        return this.userAccountRepository.findByUsername(username) != null;
    }
}
