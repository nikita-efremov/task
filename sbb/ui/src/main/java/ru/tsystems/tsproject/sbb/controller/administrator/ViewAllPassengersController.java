package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of getting all passengers
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ViewAllPassengersController {

    private static final Logger log = Logger.getLogger(ViewAllPassengersController.class);

    @Autowired
    private PassengerModel passengerModel;

    @RequestMapping("/administrator/passengers/ViewAllPassengers")
    public String viewPassengers(ModelMap modelMap) {
        Collection<PassengerBean> passengers = passengerModel.getAllPassengers();
        modelMap.addAttribute("passengers", passengers);
        return "/administrator/passengers/viewAllPassengers";
    }
}
