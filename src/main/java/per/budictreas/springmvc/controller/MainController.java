package per.budictreas.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import per.budictreas.springmvc.common.Constant;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.data.requestmodel.RegisterFormRequestModel;
import per.budictreas.springmvc.mapper.modelmapper.RegisterFormRequestModelMapper;
import per.budictreas.springmvc.service.UserAccountService;
import per.budictreas.springmvc.validator.RegisterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    private final static String INVALID_PAGE = "invalid";
    private final static String SEARCH_PAGE = "search";
    private final static String LOGIN_PAGE = "login";
    private final UserAccountService userAccountService;
    private final RegisterFormRequestModelMapper registerFormRequestModelMapper;
    private final RegisterValidator registerValidator;

    @Autowired
    public MainController(UserAccountService userAccountService,
                          RegisterFormRequestModelMapper registerFormRequestModelMapper,
                          RegisterValidator registerValidator) {
        this.userAccountService = userAccountService;
        this.registerFormRequestModelMapper = registerFormRequestModelMapper;
        this.registerValidator = registerValidator;
    }

    @RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) {
        String url = INVALID_PAGE;
        RegistrationEntity registrationEntity = this.userAccountService.checkLogin(request.getParameter("username"), request.getParameter("password"));
        if (registrationEntity != null) {
            url = SEARCH_PAGE;
            request.getSession().setAttribute(Constant.ATTR_USER, registrationEntity);
        }

        return new ModelAndView(url);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        if (session != null) session.invalidate();
        return new ModelAndView(LOGIN_PAGE);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register-taglib", method = RequestMethod.GET)
    public ModelAndView getRegisterTaglibPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", new RegisterFormRequestModel());
        return new ModelAndView("register_taglib", map);
    }

    @RequestMapping(value = "/registerTaglib_Action", method = RequestMethod.POST)
    public ModelAndView registerProcess(@ModelAttribute("user") @Validated RegisterFormRequestModel registerFormRequestModel,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView("register_taglib");
        this.userAccountService.createUser(this.registerFormRequestModelMapper.toDTO(registerFormRequestModel));
        return new ModelAndView(LOGIN_PAGE);
    }

    @InitBinder("user")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(registerValidator);
    }
}
