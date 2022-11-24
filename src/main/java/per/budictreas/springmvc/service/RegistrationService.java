package per.budictreas.springmvc.service;

import org.springframework.stereotype.Service;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.mapper.dtomapper.RegistrationDTOMapper;
import per.budictreas.springmvc.repository.RegistrationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationDTOMapper registrationDTOMapper;

    public RegistrationService(RegistrationRepository registrationRepository, RegistrationDTOMapper registrationDTOMapper) {
        this.registrationRepository = registrationRepository;
        this.registrationDTOMapper = registrationDTOMapper;
    }

    public RegistrationEntity checkLogin(String username, String password) {
        boolean result = this.registrationRepository.countByUsernameAndPassword(username, password) > 0;
        if (result) return this.registrationRepository.findByUsername(username);
        return null;
    }

    public List<RegistrationDTO> searchByLastname(String lastname) {
        return this.registrationDTOMapper.toDTO(this.registrationRepository.getByLastnameContainingIgnoreCase(lastname));
    }

    public boolean updateUser(RegistrationDTO registrationDTO) {
        RegistrationEntity registrationEntity = this.registrationDTOMapper.toEntity(registrationDTO);
        if (this.registrationRepository.findById(registrationEntity.getUsername()).orElseThrow(EntityNotFoundException::new) != null) {
            this.registrationRepository.saveAndFlush(registrationEntity);
        }

        return true;
    }
}
