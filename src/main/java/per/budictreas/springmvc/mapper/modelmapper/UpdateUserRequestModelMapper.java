package per.budictreas.springmvc.mapper.modelmapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.REOGenericMapper;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.requestmodel.UpdateUserRequestModel;

@Mapper(componentModel = "spring")
public interface UpdateUserRequestModelMapper extends REOGenericMapper<RegistrationDTO, UpdateUserRequestModel> {
}
