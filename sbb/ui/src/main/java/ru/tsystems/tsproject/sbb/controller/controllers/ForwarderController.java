package ru.tsystems.tsproject.sbb.controller.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, which forwards requests to appropriate jsp pages
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ForwarderController {

    /**
     * Forwards no index page
     * @return JSP address to forward
     */
    @RequestMapping(value = {"/", "/index"})
    public String toIndexPage() {
        return "/index";
    }

    /**
     * Forwards to administrator main page
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping("/administrator/administratorMain")
    public String toAdministratorPage() {
        return "/administrator/administratorMain";
    }

    /**
     * Forwards to login page
     * @return JSP address to forward
     */
    @RequestMapping("/login")
    public String toLoginPage() {
        return "/login";
    }

    /**
     * Forwards to 403 error page
     * @return JSP address to forward
     */
    @RequestMapping("/error403")
    public String toErrorPage() {
        return "/error403";
    }
}
