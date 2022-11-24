package per.budictreas.springmvc.mapper.modelmapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.ResponseModelGenericMapper;
import per.budictreas.springmvc.data.dto.RegistrationDTO;
import per.budictreas.springmvc.data.responsemodel.RegistrationResponseModel;

@Mapper(componentModel = "spring")                                                  //S class        //D class
public interface RegistrationResponseModelMapper extends ResponseModelGenericMapper<RegistrationDTO, RegistrationResponseModel> {
}
