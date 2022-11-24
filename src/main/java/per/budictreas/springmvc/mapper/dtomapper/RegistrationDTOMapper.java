package per.budictreas.springmvc.mapper.dtomapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.DTOGenericMapper;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.entity.RegistrationEntity;

@Mapper(componentModel = "spring")                              //S class           //D class
public interface RegistrationDTOMapper extends DTOGenericMapper<RegistrationEntity, RegistrationDTO> {
}
