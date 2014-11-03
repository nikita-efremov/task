package ru.tsystems.tsproject.sbb.controller.passenger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TicketBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/passenger/TicketPurchase")
    public String purchase(HttpServletRequest request) {
        String action = request.getParameter("purchaseAction");
        if (action == null) {
            return "redirect:/passenger/purchase";
        } else if (action.equals("Back")) {
            return "redirect:/index.jsp";
        } else {
            TicketBean ticketBean = new TicketBean();
            ticketBean.setTrainNumber(request.getParameter("Train_number"));
            ticketBean.setPassengerDocNumber(SecurityContextHolder.getContext().getAuthentication().getName());
            log.info("Servlet got bean: " + ticketBean);
            ValidationBean validationBean = Validator.validate(ticketBean, "trainNumber");
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("purchaseResult", ticketBean);
                return "/passenger/purchase";
            } else {
                ticketBean = passengerModel.purchaseTicket(ticketBean);
                request.setAttribute("purchaseResult", ticketBean);
                if (ticketBean.isProcessingFailed()) {
                    return "/passenger/purchase";
                } else {
                    return "/passenger/purchaseSuccess";
                }
            }
        }
    }
}
