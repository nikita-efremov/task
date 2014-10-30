package ru.tsystems.tsproject.sbb.controller.passenger;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class PassengerLoginController {

    private static final Logger log = Logger.getLogger(PassengerLoginController.class);
    private PassengerModel passengerModel = CustomApplicationContext.getPassengerModel();

    @RequestMapping("/PassengerLogin")
    public String passengerLogin(HttpServletRequest request,
                                 HttpServletResponse response, ModelMap model) {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("passDoc");
        String action = request.getParameter("loginAction");
        if (action == null) {
            return "redirect:/passengerLogin";
        } else if (action.equals("Back")) {
            return "redirect:/index.jsp";
        } else {
            PassengerBean passengerBean = new PassengerBean();
            passengerBean.setDocNumber(request.getParameter("Document_number"));
            log.info("Servlet got bean: " + passengerBean);
            ValidationBean validationBean = Validator.validate(passengerBean, "docNumber");
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("loginResult", passengerBean);
                return "passengerLogin";
            } else {
                passengerBean = passengerModel.getPassenger(passengerBean);
                request.setAttribute("loginResult", passengerBean);
                if (passengerBean.isProcessingFailed()) {
                    return "passengerLogin";
                } else {
                    httpSession.setAttribute("user", passengerBean.getLastName() + " " + passengerBean.getFirstName().charAt(0) + ".");
                    httpSession.setAttribute("passDoc", passengerBean.getDocNumber());
                    httpSession.setMaxInactiveInterval(30*60);
                    log.info("Passenger with docNumber=" + passengerBean.getDocNumber() + " logged in");
                    return "index";
                }
            }
        }
    }
}
