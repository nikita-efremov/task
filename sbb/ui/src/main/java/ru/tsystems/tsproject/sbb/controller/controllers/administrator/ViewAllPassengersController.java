package ru.tsystems.tsproject.sbb.controller.controllers.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.viewbean.PassengerViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.PassengerControllersHelper;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of getting all passengers
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ViewAllPassengersController {

    private static final Logger log = Logger.getLogger(ViewAllPassengersController.class);
    private static final String PASSENGERS = "passengers";

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private PassengerControllersHelper passengerControllersHelper;

    /**
     * Proceeds requests of getting all passengers and forwards to train view page
     * @param modelMap
     *        Map of viewBeans
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping("/administrator/passengers/ViewAllPassengers")
    public String viewPassengers(ModelMap modelMap) {
        Collection<PassengerViewBean> passengers = passengerControllersHelper.getAllPassengers();
        modelMap.addAttribute(PASSENGERS, passengers);
        return "/administrator/passengers/viewAllPassengers";
    }
}
