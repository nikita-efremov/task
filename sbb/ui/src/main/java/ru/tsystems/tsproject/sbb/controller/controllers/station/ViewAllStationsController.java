package ru.tsystems.tsproject.sbb.controller.controllers.station;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    private static final String STATIONS = "allStations";

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private StationControllersHelper stationControllersHelper;

    /**
     * Proceeds requests of getting all stations and forwards to passengers view page
     * @param modelMap
     *        Map of viewBean
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping("/administrator/station/ViewAllStations")
    public String viewStations(ModelMap modelMap) {
        Collection<StationViewBean> stations = stationControllersHelper.getAllStations();
        modelMap.addAttribute(STATIONS, stations);
        return "/administrator/station/viewAllStations";
    }
}
