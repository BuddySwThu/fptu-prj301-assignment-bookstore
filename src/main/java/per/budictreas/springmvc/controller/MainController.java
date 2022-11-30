package per.budictreas.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import per.budictreas.springmvc.common.Constant;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private final static String INVALID_PAGE = "invalid";
    private final static String SEARCH_PAGE = "search";
    private final static String LOGIN_PAGE = "login";
    private final UserAccountService userAccountService;

    public MainController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
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
}
