package per.budictreas.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.budictreas.springmvc.data.dto.ProductDTO;
import per.budictreas.springmvc.mapper.dtomapper.ProductDTOMapper;
import per.budictreas.springmvc.repository.MS.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;
    private ProductDTOMapper productDTOMapper;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductDTOMapper(ProductDTOMapper productDTOMapper) {
        this.productDTOMapper = productDTOMapper;
    }

    //@Transactional
    public List<ProductDTO> getAllProducts() {
        return this.productDTOMapper.toDTO(this.productRepository.findAll());
    }
}
