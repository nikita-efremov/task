package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.model.StationModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private StationModel stationModel;

    @RequestMapping("/administrator/station/ViewAllStations")
    public String viewStations(HttpServletRequest request) {
        Collection<StationBean> stations = stationModel.getAllStations();
        request.setAttribute("allStations", stations);
        return "/administrator/station/viewAllStations";
    }
}
