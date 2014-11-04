package ru.tsystems.tsproject.sbb.controller.controllers.passenger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.viewbean.PassengerViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TicketViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.PassengerControllersHelper;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of searching passenger tickets
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTicketsController {

    private static final Logger log = Logger.getLogger(SearchTicketsController.class);

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private PassengerControllersHelper passengerControllersHelper;

    /**
     * Proceeds requests of searching passenger tickets and forwards to watching tickets page
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to forward
     */
    @RequestMapping("/passenger/WatchTickets")
    public String searchTickets(ModelMap modelMap) {
        PassengerViewBean passengerBean = new PassengerViewBean();
        passengerBean.setDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        Collection<TicketViewBean> ticketBeans = passengerControllersHelper.getPassengerTickets(passengerBean);
        modelMap.addAttribute("tickets", ticketBeans);
        return "/passenger/watchTickets";
    }
}
