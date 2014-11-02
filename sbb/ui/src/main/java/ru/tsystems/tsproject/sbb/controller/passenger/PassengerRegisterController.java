package ru.tsystems.tsproject.sbb.controller.passenger;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller, which gets and proceeds requests of passenger registering
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class PassengerRegisterController {

    private static final Logger log = Logger.getLogger(PassengerRegisterController.class);

    @Autowired
    private PassengerModel passengerModel;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("customAuthenticationManager")
    protected AuthenticationManager authenticationManager;

    @RequestMapping("/RegisterPassenger")
    public String register(HttpServletRequest request) {
        String action = request.getParameter("passengerRegisterAction");
        if (action == null) {
            return "redirect:/register.jsp";
        } else if (action.equals("back")) {
            return "redirect:/index.jsp";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthDateString = request.getParameter("Birth_date");
            Date birthDate;
            try {
                birthDate = simpleDateFormat.parse(birthDateString);
            } catch (ParseException e) {
                birthDate = null;
            }
            String password = request.getParameter("Password");

            PassengerBean passengerBean = new PassengerBean();
            passengerBean.setLastName(request.getParameter("Last_name"));
            passengerBean.setFirstName(request.getParameter("First_name"));
            passengerBean.setDocNumber(request.getParameter("Document_number"));
            passengerBean.setBirthDate(birthDate);
            passengerBean.setPassword(password);
            log.info("Servlet got bean: " + passengerBean);
            ValidationBean validationBean = Validator.validate(passengerBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("createResult", passengerBean);
                request.setAttribute("validationBean", validationBean);
                return "register";
            } else {
                passengerBean = passengerModel.addPassenger(passengerBean);
                request.setAttribute("createResult", passengerBean);
                if (passengerBean.isProcessingFailed()) {
                    return "register";
                } else {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(passengerBean.getDocNumber());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, password, userDetails.getAuthorities());
                    authenticationManager.authenticate(auth);

                    if(auth.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                    return "/passenger/registerSuccess";
                }
            }
        }
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
