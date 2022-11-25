package per.budictreas.springmvc.service;

import org.springframework.stereotype.Service;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.mapper.dtomapper.RegistrationDTOMapper;
import per.budictreas.springmvc.repository.BookStoreRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookStoreService {
    private final BookStoreRepository bookStoreRepository;
    private final RegistrationDTOMapper registrationDTOMapper;

    public BookStoreService(BookStoreRepository bookStoreRepository, RegistrationDTOMapper registrationDTOMapper) {
        this.bookStoreRepository = bookStoreRepository;
        this.registrationDTOMapper = registrationDTOMapper;
    }

    public RegistrationEntity checkLogin(String username, String password) {
        boolean result = this.bookStoreRepository.countByUsernameAndPassword(username, password) > 0;
        if (result) return this.bookStoreRepository.findByUsername(username);
        return null;
    }

    public List<RegistrationDTO> searchByLastname(String lastname) {
        return this.registrationDTOMapper.toDTO(this.bookStoreRepository.getByLastnameContainingIgnoreCase(lastname));
    }

    public boolean updateUser(RegistrationDTO registrationDTO) {
        RegistrationEntity registrationEntity = this.registrationDTOMapper.toEntity(registrationDTO);
        if (this.bookStoreRepository.findById(registrationEntity.getUsername()).orElseThrow(EntityNotFoundException::new) != null)
            this.bookStoreRepository.saveAndFlush(registrationEntity);

        return true;
    }
}
