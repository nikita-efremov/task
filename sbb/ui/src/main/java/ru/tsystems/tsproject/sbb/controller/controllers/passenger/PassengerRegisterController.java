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

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private PassengerControllersHelper passengerControllersHelper;

    /**
     * Needs for searching registered user and its details
     */
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * Needs for authentication after successful registration
     */
    @Autowired
    @Qualifier("customAuthenticationManager")
    protected AuthenticationManager authenticationManager;

    /**
     * Creates date formatter for jsp
     *
     * @param binder
     *        Binder which binds data in view
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    /**
     * Adds passengerViewBean to the view and forwards to JSP with form of adding new passenger
     * @return JSP address to forwards
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView initPassengerBean() {
        return new ModelAndView("/register", PassengerViewBean.DEFAULT_NAME, new PassengerViewBean());
    }

    /**
     * Proceeds requests of passenger registering and then forwards to appropriate page.
     * If registration was successful, passenger will be authorized in system
     * @param passengerBean
     *        ViewBean with new passenger info
     * @param modelMap
     *        Map with view beans
     * @return JSP address to forward
     */
    @RequestMapping("/RegisterPassenger")
    public String register(@ModelAttribute("passengerBean") PassengerViewBean passengerBean, ModelMap modelMap) {
        log.info("Servlet got viewBean: " + passengerBean);
        String password = passengerBean.getPassword();
        ValidationBean validationBean = Validator.validate(passengerBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute(PassengerViewBean.DEFAULT_NAME, passengerBean);
            modelMap.addAttribute(ValidationBean.DEFAULT_NAME, validationBean);
            return "/register";
        } else {
            passengerBean = passengerControllersHelper.addPassenger(passengerBean);
            modelMap.addAttribute(PassengerViewBean.DEFAULT_NAME, passengerBean);
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
