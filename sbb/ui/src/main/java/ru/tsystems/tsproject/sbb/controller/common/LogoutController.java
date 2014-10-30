package ru.tsystems.tsproject.sbb.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller, which gets and proceeds logout requests
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response, ModelMap model) {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("passDoc");
        return "redirect:/index.jsp";
    }
}
