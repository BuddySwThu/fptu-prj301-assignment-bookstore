package per.budictreas.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import per.budictreas.springmvc.data.dto.ProductDTO;
import per.budictreas.springmvc.data.responsemodel.CartProductResponseModel;
import per.budictreas.springmvc.mapper.dtomapper.ProductDTOMapper;
import per.budictreas.springmvc.mapper.modelmapper.CartProductResponseModelMapper;
import per.budictreas.springmvc.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    private Map<ProductDTO, Integer> products;
    private ProductRepository productRepository;
    private ProductDTOMapper productDTOMapper;
    private CartProductResponseModelMapper cartProductResponseModelMapper;

    public CartService() {
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductDTOMapper(ProductDTOMapper productDTOMapper) {
        this.productDTOMapper = productDTOMapper;
    }

    @Autowired
    public void setCartProductResponseModelMapper(CartProductResponseModelMapper cartProductResponseModelMapper) {
        this.cartProductResponseModelMapper = cartProductResponseModelMapper;
    }

    //method co san, can generate
    public Map<ProductDTO, Integer> getProducts() {
        return this.products;
    }

    public List<CartProductResponseModel> getProductsAsList() {
        if (this.products == null) return null;
        List<CartProductResponseModel> responseModelList = new ArrayList<>();
        for (ProductDTO dto : this.products.keySet()) {
            CartProductResponseModel responseModel = this.cartProductResponseModelMapper.toREO(dto);
            responseModel.setQuantity(this.products.get(dto));
            responseModelList.add(responseModel);
        }

        return responseModelList;
    }

    public void addProductByID(Integer productID) {
        this.addProduct(this.productDTOMapper.toDTO(this.productRepository.findById(productID).orElseThrow(EntityNotFoundException::new)));
    }

    private void addProduct(ProductDTO productDTO) {
        if (this.products == null) this.products = new HashMap<>(); //end if products not null
        int quantity = 1;
        if (this.products.containsKey(productDTO)) {
            quantity = this.products.get(productDTO) + 1;
        }
        this.products.put(productDTO, quantity);
    }

    public void removeProductByID(Integer productID) {
        this.removeProduct(this.productDTOMapper.toDTO(this.productRepository.findById(productID).orElseThrow(EntityNotFoundException::new)));
    }

    private void removeProduct(ProductDTO productDTO) {
        if (this.products == null) return;
        if (this.products.containsKey(productDTO)) {
            this.products.remove(productDTO);
            if (this.products.isEmpty()) this.products = null;
        }
    }
}
