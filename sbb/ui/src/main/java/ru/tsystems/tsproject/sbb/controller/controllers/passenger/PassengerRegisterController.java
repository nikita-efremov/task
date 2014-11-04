package ru.tsystems.tsproject.sbb.controller.controllers.passenger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.validation.ValidationBean;
import ru.tsystems.tsproject.sbb.validation.Validator;
import ru.tsystems.tsproject.sbb.viewbean.PassengerViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.PassengerControllersHelper;

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
    private PassengerControllersHelper passengerControllersHelper;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("customAuthenticationManager")
    protected AuthenticationManager authenticationManager;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView initPassengerBean() {
        return new ModelAndView("/register", "passengerBean", new PassengerViewBean());
    }

    @RequestMapping("/RegisterPassenger")
    public String register(@ModelAttribute("passengerBean") PassengerViewBean passengerBean, ModelMap modelMap) {
        log.info("Servlet got viewBean: " + passengerBean);
        String password = passengerBean.getPassword();
        ValidationBean validationBean = Validator.validate(passengerBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("passengerBean", passengerBean);
            modelMap.addAttribute("validationBean", validationBean);
            return "/register";
        } else {
            passengerBean = passengerControllersHelper.addPassenger(passengerBean);
            modelMap.addAttribute("passengerBean", passengerBean);
            if (passengerBean.isProcessingFailed()) {
                return "/register";
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

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
