package ru.tsystems.tsproject.sbb.controller.administrator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller, which redirects to administrator default page
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class AdminRedirectController {

    @RequestMapping("/administrator")
    public String redirect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        return "redirect:/administrator/administratorMain.jsp";
    }
}
