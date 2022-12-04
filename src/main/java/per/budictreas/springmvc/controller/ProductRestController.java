package per.budictreas.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.budictreas.springmvc.data.responsemodel.ProductResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.ProductResponseModelMapper;
import per.budictreas.springmvc.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
//“api” để chỉ rằng URL này là một RESTfull API endpoint
//“v1” để đánh dấu version của API
public class ProductRestController {

    private ProductService productService;
    private ProductResponseModelMapper productResponseModelMapper;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductResponseModelMapper(ProductResponseModelMapper productResponseModelMapper) {
        this.productResponseModelMapper = productResponseModelMapper;
    }

    @GetMapping(value = "/retrieve-books")
    public ResponseEntity<List<ProductResponseModel>> getAllProducts() {
        return ResponseEntity.ok(this.productResponseModelMapper.toREO(this.productService.getAllProducts()));
    }
}
