package per.budictreas.springmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.budictreas.springmvc.data.requestmodel.UpdateUserRequestModel;
import per.budictreas.springmvc.data.responsemodel.CommonResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.RegistrationResponseModelMapper;
import per.budictreas.springmvc.data.responsemodel.RegistrationResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.UpdateUserModelMapper;
import per.budictreas.springmvc.service.RegistrationService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/user")
public class RestController {
    private final RegistrationService registrationService;
    private final RegistrationResponseModelMapper registrationResponseModelMapper;
    private final UpdateUserModelMapper updateUserModelMapper;

    public RestController(RegistrationService registrationService,
                          RegistrationResponseModelMapper registrationResponseModelMapper,
                          UpdateUserModelMapper updateUserModelMapper) {
        this.registrationService = registrationService;
        this.registrationResponseModelMapper = registrationResponseModelMapper;
        this.updateUserModelMapper = updateUserModelMapper;
    }

    @GetMapping(value = "/search-user")
    public ResponseEntity<List<RegistrationResponseModel>> search(@RequestParam("searchValue") String searchValue) {
        return ResponseEntity.ok(this.registrationResponseModelMapper.toResponseModel(this.registrationService.searchByLastname(searchValue)));
    }

    @PutMapping(value = "/update-user")             //@Valid for hibernate-validator version 5.?.?
    public ResponseEntity<CommonResponseModel> update(@Validated @RequestBody UpdateUserRequestModel request) {
        return ResponseEntity.ok(CommonResponseModel.build(this.registrationService.updateUser(this.updateUserModelMapper.toDTO(request)), "Update successfully", null));
    }
}
