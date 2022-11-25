package per.budictreas.springmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.budictreas.springmvc.data.requestmodel.UpdateUserRequestModel;
import per.budictreas.springmvc.data.responsemodel.CommonResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.RegistrationResponseModelMapper;
import per.budictreas.springmvc.data.responsemodel.RegistrationResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.UpdateUserRequestModelMapper;
import per.budictreas.springmvc.service.BookStoreService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/user")
public class RestController {
    private final BookStoreService bookStoreService;
    private final RegistrationResponseModelMapper registrationResponseModelMapper;
    private final UpdateUserRequestModelMapper updateUserRequestModelMapper;

    public RestController(BookStoreService bookStoreService,
                          RegistrationResponseModelMapper registrationResponseModelMapper,
                          UpdateUserRequestModelMapper updateUserRequestModelMapper) {
        this.bookStoreService = bookStoreService;
        this.registrationResponseModelMapper = registrationResponseModelMapper;
        this.updateUserRequestModelMapper = updateUserRequestModelMapper;
    }

    @GetMapping(value = "/search-user")
    public ResponseEntity<List<RegistrationResponseModel>> search(@RequestParam("searchValue") String searchValue) {
        return ResponseEntity.ok(this.registrationResponseModelMapper.toREO(this.bookStoreService.searchByLastname(searchValue)));
    }

    @PutMapping(value = "/update-user")             //@Valid for hibernate-validator version 5.?.?
    public ResponseEntity<CommonResponseModel> update(@Validated @RequestBody UpdateUserRequestModel request) {
        return ResponseEntity.ok(CommonResponseModel.build(this.bookStoreService.updateUser(this.updateUserRequestModelMapper.toDTO(request)), "Update successfully", null));
    }
}
