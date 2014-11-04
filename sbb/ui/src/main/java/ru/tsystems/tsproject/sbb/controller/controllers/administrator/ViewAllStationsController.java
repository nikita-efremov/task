package ru.tsystems.tsproject.sbb.controller.controllers.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.viewbean.StationViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.StationControllersHelper;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of getting all stations
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ViewAllStationsController {

    private static final Logger log = Logger.getLogger(ViewAllStationsController.class);

    @Autowired
    private StationControllersHelper stationControllersHelper;

    @RequestMapping("/administrator/station/ViewAllStations")
    public String viewStations(ModelMap modelMap) {
        Collection<StationViewBean> stations = stationControllersHelper.getAllStations();
        modelMap.addAttribute("allStations", stations);
        return "/administrator/station/viewAllStations";
    }
}
