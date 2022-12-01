package per.budictreas.springmvc.mapper.dtomapper;

import org.mapstruct.Mapper;
import per.budictreas.springmvc.common.mapper.DTOGenericMapper;
import per.budictreas.springmvc.data.dto.ProductDTO;
import per.budictreas.springmvc.data.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductDTOMapper extends DTOGenericMapper<ProductEntity, ProductDTO> {
}
