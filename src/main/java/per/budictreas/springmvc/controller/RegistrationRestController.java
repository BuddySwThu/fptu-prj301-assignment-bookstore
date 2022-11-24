package per.budictreas.springmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.budictreas.springmvc.mapper.modelmapper.RegistrationResponseModelMapper;
import per.budictreas.springmvc.data.responsemodel.RegistrationResponseModel;
import per.budictreas.springmvc.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class RegistrationRestController {
    private final RegistrationService registrationService;
    private final RegistrationResponseModelMapper registrationResponseModelMapper;

    public RegistrationRestController(RegistrationService registrationService,
                                      RegistrationResponseModelMapper registrationResponseModelMapper) {
        this.registrationService = registrationService;
        this.registrationResponseModelMapper = registrationResponseModelMapper;
    }

    @GetMapping(value = "/search-user")
    public ResponseEntity<List<RegistrationResponseModel>> search(@RequestParam("searchValue") String searchValue) {
        return ResponseEntity.ok(this.registrationResponseModelMapper.toResponseModel(this.registrationService.searchByLastname(searchValue)));
    }
}
