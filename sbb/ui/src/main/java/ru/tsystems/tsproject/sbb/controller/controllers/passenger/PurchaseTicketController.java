package ru.tsystems.tsproject.sbb.controller.controllers.passenger;

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
import ru.tsystems.tsproject.sbb.viewbean.TicketViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.PassengerControllersHelper;

/**
 * Controller, which gets and proceeds requests of ticket purchasing
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class PurchaseTicketController {

    private static final Logger log = Logger.getLogger(PurchaseTicketController.class);

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private PassengerControllersHelper passengerControllersHelper;

    /**
     * Adds ticketViewBean to the view and forwards to JSP with form of purchasing ticket
     * @return JSP address to forward
     */
    @RequestMapping(value = "/passenger/purchase", method = RequestMethod.GET)
    public ModelAndView initTicketBean() {
        return new ModelAndView("/passenger/purchase", TicketViewBean.DEFAULT_NAME, new TicketViewBean());
    }

    /**
     * Gets request of purchasing ticket,calls purchase proceeding method and then forwards to appropriate page
     * @param trainNumber
     *        Number of the train to purchase ticket
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to redirect
     */
    @RequestMapping(value = "/passenger/TicketPurchase",
            method = RequestMethod.GET,
            params = "purchaseAction=Purchase")
    public String purchaseFromTrainList(@RequestParam("trainNumber") String trainNumber, ModelMap modelMap) {
        TicketViewBean ticketBean = new TicketViewBean();
        ticketBean.setTrainNumber(trainNumber);
        ticketBean.setPassengerDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        return makePurchase(ticketBean, modelMap);
    }

    /**
     * Gets request of purchasing ticket, calls purchase proceeding method and then forwards to appropriate page
     * @param ticketBean
     *        Bean with ticket info
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to redirect
     */
    @RequestMapping(value = "/passenger/TicketPurchase",
            method = RequestMethod.POST)
    public String purchaseFromSpecialForm(@ModelAttribute("ticketBean") TicketViewBean ticketBean, ModelMap modelMap) {
        ticketBean.setPassengerDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        return makePurchase(ticketBean, modelMap);
    }

    /**
     * Proceeds ticket purchasing and return result to controller method
     * @param ticketBean
     *        ViewBean with ticket info
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to redirect
     */
    private String makePurchase(TicketViewBean ticketBean, ModelMap modelMap) {
        log.info("Servlet got viewBean: " + ticketBean);
        ValidationBean validationBean = Validator.validate(ticketBean, "trainNumber");
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute(ValidationBean.DEFAULT_NAME, validationBean);
            modelMap.addAttribute(TicketViewBean.DEFAULT_NAME, ticketBean);
            return "/passenger/purchase";
        } else {
            ticketBean = passengerControllersHelper.purchaseTicket(ticketBean);
            modelMap.addAttribute(TicketViewBean.DEFAULT_NAME, ticketBean);
            if (ticketBean.isProcessingFailed()) {
                return "/passenger/purchase";
            } else {
                return "/passenger/purchaseSuccess";
            }
        }
    }
}
