package ru.tsystems.tsproject.sbb.controller.controllers.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of getting all trains
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ViewAllTrainsController {

    private static final Logger log = Logger.getLogger(ViewAllTrainsController.class);

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private TrainControllersHelper trainControllersHelper;

    /**
     * Proceeds requests of getting all trains and forwards to trains view page
     * @param modelMap
     *        Map of viewBean
     * @return JSP address to forward
     */
    @RequestMapping("/administrator/train/ViewAllTrains")
    public String viewTrains(ModelMap modelMap) {
        Collection<TrainViewBean> trains = trainControllersHelper.getAllTrains();
        modelMap.addAttribute("allTrains", trains);
        return "/administrator/train/viewAllTrains";
    }
}
