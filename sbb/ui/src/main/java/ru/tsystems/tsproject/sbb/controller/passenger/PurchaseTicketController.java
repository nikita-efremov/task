package ru.tsystems.tsproject.sbb.controller.passenger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.validation.ValidationBean;
import ru.tsystems.tsproject.sbb.validation.Validator;
import ru.tsystems.tsproject.sbb.bean.TicketBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

/**
 * Controller, which gets and proceeds requests of ticket purchasing
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class PurchaseTicketController {

    private static final Logger log = Logger.getLogger(PurchaseTicketController.class);

    @Autowired
    private PassengerModel passengerModel;

    @RequestMapping(value = "/passenger/purchase", method = RequestMethod.GET)
    public ModelAndView initTicketBean() {
        return new ModelAndView("/passenger/purchase", "ticketBean", new TicketBean());
    }

    @RequestMapping(value = "/passenger/TicketPurchase",
            method = RequestMethod.GET,
            params = "purchaseAction=Purchase")
    public String purchaseFromTrainList(@RequestParam("trainNumber") String trainNumber, ModelMap modelMap) {
        TicketBean ticketBean = new TicketBean();
        ticketBean.setTrainNumber(trainNumber);
        ticketBean.setPassengerDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        return makePurchase(ticketBean, modelMap);
    }

    @RequestMapping(value = "/passenger/TicketPurchase",
            method = RequestMethod.POST)
    public String purchaseFromSpecialForm(@ModelAttribute("ticketBean") TicketBean ticketBean, ModelMap modelMap) {
        ticketBean.setPassengerDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        return makePurchase(ticketBean, modelMap);
    }

    private String makePurchase(TicketBean ticketBean, ModelMap modelMap) {
        log.info("Servlet got bean: " + ticketBean);
        ValidationBean validationBean = Validator.validate(ticketBean, "trainNumber");
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("ticketBean", ticketBean);
            return "/passenger/purchase";
        } else {
            ticketBean = passengerModel.purchaseTicket(ticketBean);
            modelMap.addAttribute("ticketBean", ticketBean);
            if (ticketBean.isProcessingFailed()) {
                return "/passenger/purchase";
            } else {
                return "/passenger/purchaseSuccess";
            }
        }
    }
}
