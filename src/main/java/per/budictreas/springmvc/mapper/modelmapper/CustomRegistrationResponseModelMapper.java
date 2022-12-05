package per.budictreas.springmvc.mapper.modelmapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import per.budictreas.springmvc.common.mapper.REOGenericMapper;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.responsemodel.CustomRegistrationResponeModel;

@Mapper(componentModel = "spring")
public interface CustomRegistrationResponseModelMapper extends REOGenericMapper<RegistrationDTO, CustomRegistrationResponeModel> {
    @Mappings(value = {@Mapping(source = "role", target = "admin")})
    CustomRegistrationResponeModel toREO(RegistrationDTO dto);
}
