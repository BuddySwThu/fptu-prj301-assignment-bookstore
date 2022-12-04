package per.budictreas.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import per.budictreas.springmvc.data.responsemodel.CartProductResponseModel;
import per.budictreas.springmvc.data.responsemodel.CommonResponseModel;
import per.budictreas.springmvc.service.CartService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shoppingCart")
public class CartRestController {
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/products/get-cart")
    public ResponseEntity<List<CartProductResponseModel>> getCart() {
        List<CartProductResponseModel> modelList = cartService.getProductsAsList();
        if (modelList == null) return ResponseEntity.ok(Collections.EMPTY_LIST);
        return ResponseEntity.ok(modelList);
    }

    //Trong giá trị của mapping URL, chúng ta dùng “{productID}” và trong parameter của hàm chúng ta dùng annotation @PathVarible.
    //Spring sẽ tự động binding bất kỳ giá trị nào nằm ở {productID} sẽ được convert sang Interger productID.
    @PostMapping(value = "/products/add-product-to-cart/{productID}")
    public ResponseEntity<CommonResponseModel> addProductToCart(@PathVariable Integer productID) {
        this.cartService.addProductByID(productID);
        return ResponseEntity.ok(CommonResponseModel.build(true, "Hooray, Add Product Successfully.", null));
    }

    @DeleteMapping(value = "/products/remove-product-from-cart/{productID}")
    public ResponseEntity<CommonResponseModel> removeProductFromCart(@PathVariable Integer productID) {
        this.cartService.removeProductByID(productID);
        return ResponseEntity.ok(CommonResponseModel.build(true, "Hooray, Remove Product Successfully.", null));
    }
}
