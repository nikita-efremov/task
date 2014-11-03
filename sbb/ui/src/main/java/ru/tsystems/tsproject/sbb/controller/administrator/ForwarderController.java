package ru.tsystems.tsproject.sbb.controller.administrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, which forwards requests to appropriate jsp pages
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ForwarderController {

    @RequestMapping(value = {"/", "/index"})
    public String toIndexPage() {
        return "/index";
    }

    @RequestMapping("/administrator/administratorMain")
    public String toAdministratorPage() {
        return "/administrator/administratorMain";
    }

    @RequestMapping("/login")
    public String toLoginPage() {
        return "/login";
    }

    @RequestMapping("/error403")
    public String toErrorPage() {
        return "/error403";
    }
}
