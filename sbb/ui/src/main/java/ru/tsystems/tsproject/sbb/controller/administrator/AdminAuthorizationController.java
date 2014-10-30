package ru.tsystems.tsproject.sbb.controller.administrator;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.AdminLoginBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller, which gets and proceeds requests of admin authorization
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class AdminAuthorizationController {

    private static final Logger log = Logger.getLogger(AdminAuthorizationController.class);
    private static final String user = "admin";
    private static final String password = "qwerty";

    @RequestMapping("/authorization")
    public String authorize(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String action = request.getParameter("loginAction");
        if (action == null) {
            return "redirect:/adminAuthorization.jsp";
        } else if (action.equals("Back")) {
            return "redirect:/index.jsp";
        } else {
            HttpSession httpSession = request.getSession();
            httpSession.removeAttribute("user");
            httpSession.removeAttribute("passDoc");
            AdminLoginBean adminLoginBean = new AdminLoginBean();
            adminLoginBean.setLogin(request.getParameter("login"));
            adminLoginBean.setPassword(request.getParameter("password"));
            ValidationBean validationBean = Validator.validate(adminLoginBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("loginResult", adminLoginBean);
                request.setAttribute("validationBean", validationBean);
                return "/adminAuthorization";
            } else {
                String loginExpected = user;
                String passExpected = password;
                if ((loginExpected.equals(adminLoginBean.getLogin()))
                        && (passExpected.equals(adminLoginBean.getPassword()))) {
                    log.info("Admin logged in system");
                    httpSession.setAttribute("user", "admin");
                    httpSession.setMaxInactiveInterval(30*60);
                    return "redirect:/administrator/administratorMain.jsp";
                } else {
                    validationBean.setValidationMessage("Invalid credentials");
                    request.setAttribute("validationBean", validationBean);
                    request.setAttribute("loginResult", adminLoginBean);
                    return "/adminAuthorization";
                }
            }
        }

    }
}
