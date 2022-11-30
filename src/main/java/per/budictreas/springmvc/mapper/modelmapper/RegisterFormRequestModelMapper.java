package per.budictreas.springmvc.mapper.modelmapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.REOGenericMapper;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.requestmodel.RegisterFormRequestModel;

@Mapper(componentModel = "spring")
public interface RegisterFormRequestModelMapper extends REOGenericMapper<RegistrationDTO, RegisterFormRequestModel> {
}
