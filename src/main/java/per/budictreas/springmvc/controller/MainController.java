package per.budictreas.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import per.budictreas.springmvc.common.Constant;
import per.budictreas.springmvc.data.entity.RegistrationEntity;
import per.budictreas.springmvc.service.BookStoreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private final static String INVALID_PAGE = "invalid";
    private final static String SEARCH_PAGE = "search";
    private final static String LOGIN_PAGE = "login";
    private final BookStoreService bookStoreService;

    public MainController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) {
        String url = INVALID_PAGE;
        RegistrationEntity registrationEntity = this.bookStoreService.checkLogin(request.getParameter("username"), request.getParameter("password"));
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
}
