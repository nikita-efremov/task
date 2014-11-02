package ru.tsystems.tsproject.sbb.controller.passenger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TicketBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of searching passenger tickets
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTicketsController {

    private static final Logger log = Logger.getLogger(SearchTicketsController.class);

    @Autowired
    private PassengerModel passengerModel;

    @RequestMapping("/passenger/WatchTickets")
    public String searchTickets(ModelMap modelMap) {
        PassengerBean passengerBean = new PassengerBean();
        passengerBean.setDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        Collection<TicketBean> ticketBeans = passengerModel.getPassengerTickets(passengerBean);
        modelMap.addAttribute("tickets", ticketBeans);
        return "/passenger/watchTickets";
    }
}
