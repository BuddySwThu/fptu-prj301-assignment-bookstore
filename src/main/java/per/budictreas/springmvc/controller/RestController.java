package per.budictreas.springmvc.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import per.budictreas.springmvc.data.requestmodel.RegisterFormRequestModel;
import per.budictreas.springmvc.data.requestmodel.UpdateUserRequestModel;
import per.budictreas.springmvc.data.responsemodel.CommonResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.RegisterFormRequestModelMapper;
import per.budictreas.springmvc.mapper.modelmapper.RegistrationResponseModelMapper;
import per.budictreas.springmvc.data.responsemodel.RegistrationResponseModel;
import per.budictreas.springmvc.mapper.modelmapper.UpdateUserRequestModelMapper;
import per.budictreas.springmvc.service.UserAccountService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/user")
public class RestController {
    private final UserAccountService userAccountService;
    private final RegistrationResponseModelMapper registrationResponseModelMapper;
    private final UpdateUserRequestModelMapper updateUserRequestModelMapper;
    private final RegisterFormRequestModelMapper registerFormRequestModelMapper;
    private final Validator validator;

    public RestController(UserAccountService userAccountService,
                          RegistrationResponseModelMapper registrationResponseModelMapper,
                          UpdateUserRequestModelMapper updateUserRequestModelMapper,
                          RegisterFormRequestModelMapper registerFormRequestModelMapper,
                          @Qualifier("registerValidator") Validator validator) {
        this.userAccountService = userAccountService;
        this.registrationResponseModelMapper = registrationResponseModelMapper;
        this.updateUserRequestModelMapper = updateUserRequestModelMapper;
        this.registerFormRequestModelMapper = registerFormRequestModelMapper;
        this.validator = validator;
    }

    @GetMapping(value = "/search-user")
    public ResponseEntity<List<RegistrationResponseModel>> search(@RequestParam("searchValue") String searchValue) {
        return ResponseEntity.ok(this.registrationResponseModelMapper.toREO(this.userAccountService.searchByLastname(searchValue)));
    }

    @PutMapping(value = "/update-user")             //@Valid for hibernate-validator version 5.?.?
    public ResponseEntity<CommonResponseModel> update(@Validated @RequestBody UpdateUserRequestModel request) {
        return ResponseEntity.ok(CommonResponseModel.build(this.userAccountService.updateUser(this.updateUserRequestModelMapper.toDTO(request)), "Update successfully", null));
    }

    @DeleteMapping(value = "/delete-user")
    public ResponseEntity<CommonResponseModel> delete(@RequestParam("username") String username) {
        boolean result = this.userAccountService.deleteUseṛ(username);
        String message = result ? "Delete successfully." : "Delete fail !";
        return ResponseEntity.ok(CommonResponseModel.build(result, message, null));
    }

//    @InitBinder("registerFormRequestModel")
//    private void initBinder(WebDataBinder binder) {
//        binder.addValidators(validator);
//    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<CommonResponseModel> register(@Validated @RequestBody RegisterFormRequestModel registerFormRequestModel) {
        this.userAccountService.createUser(this.registerFormRequestModelMapper.toDTO(registerFormRequestModel));
        return ResponseEntity.ok(new CommonResponseModel(true, "Create successfully", null));

    }
}
