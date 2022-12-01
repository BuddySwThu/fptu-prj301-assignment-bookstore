package per.budictreas.springmvc.mapper.modelmapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.REOGenericMapper;
import per.budictreas.springmvc.data.dto.ProductDTO;
import per.budictreas.springmvc.data.responsemodel.CartProductResponseModel;

@Mapper(componentModel = "spring")
public interface CartProductResponseModelMapper extends REOGenericMapper<ProductDTO, CartProductResponseModel> {
}
