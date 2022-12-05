package per.budictreas.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import per.budictreas.springmvc.common.Constant;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.data.requestmodel.RegisterFormRequestModel;
import per.budictreas.springmvc.mapper.modelmapper.RegisterFormRequestModelMapper;
import per.budictreas.springmvc.service.UserAccountService;
import per.budictreas.springmvc.validator.RegisterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping(value = {"/", "login"})
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping(value = "/login")
    public ModelAndView login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String url = INVALID_PAGE;
        RegistrationEntity registrationEntity = this.userAccountService.checkLogin(httpServletRequest.getParameter("username"), httpServletRequest.getParameter("password"));
        if (registrationEntity != null) {
            url = SEARCH_PAGE;
            httpServletRequest.getSession().setAttribute(Constant.ATTR_USER, registrationEntity);
            httpServletResponse.setHeader("Cache-Control", "no-cache, no-store");
        }

        return new ModelAndView(url);
    }

    @GetMapping(value = "/search")
    public ModelAndView renderSearchPage(HttpSession session, HttpServletResponse httpServletResponse) {
        if (session.getAttribute(Constant.ATTR_USER) == null) return new ModelAndView(LOGIN_PAGE);
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store");
        return new ModelAndView(SEARCH_PAGE);
    }

    @GetMapping(value = "/invalid")
    public RedirectView renderInvalidPage() {
        return new RedirectView(INVALID_PAGE);
    }

    @GetMapping(value = "/logout")
    public RedirectView logout(HttpSession session) {
        if (session != null) session.invalidate();
        //return new ModelAndView("redirect:/" + LOGIN_PAGE);
        return new RedirectView(LOGIN_PAGE);
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register");
    }

    @GetMapping(value = "/register-taglib")
    public ModelAndView getRegisterTaglibPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", new RegisterFormRequestModel());
        return new ModelAndView("register_taglib", map);
    }

    @PostMapping(value = "/registerTaglib_Action")
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

    @GetMapping(value = "/shop")
    public ModelAndView getShopPage() {
        return new ModelAndView("shop");
    }

    @GetMapping(value = "/showCart")
    public ModelAndView getCartPage() {
        return new ModelAndView("cart");
    }
}
