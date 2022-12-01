package per.budictreas.springmvc.mapper.modelmapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.REOGenericMapper;
import per.budictreas.springmvc.data.dto.ProductDTO;
import per.budictreas.springmvc.data.responsemodel.ProductResponseModel;

@Mapper(componentModel = "spring")
public interface ProductResponseModelMapper extends REOGenericMapper<ProductDTO, ProductResponseModel> {
}
