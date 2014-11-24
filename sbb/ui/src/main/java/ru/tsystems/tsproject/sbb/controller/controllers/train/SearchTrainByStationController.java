package ru.tsystems.tsproject.sbb.controller.controllers.train;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.viewbean.StationViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of station timetable searching
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTrainByStationController {

    private static final Logger log = Logger.getLogger(SearchTrainByStationController.class);
    private static final String TRAINS = "foundTrains";

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private TrainControllersHelper trainControllersHelper;

    /**
     * Adds stationViewBean to the view and forwards to JSP with form of searching trains by station
     * @return JSP address to forward
     */
    @RequestMapping(value = "/common/searchStation", method = RequestMethod.GET)
    public ModelAndView initStationBean() {
        return new ModelAndView("/common/searchStation", StationViewBean.DEFAULT_NAME, new StationViewBean());
    }

    /**
     * Proceeds requests of station timetable searching and then forwards to appropriate page
     * @param stationBean
     *        ViewBean with station information
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to forward
     */
    @RequestMapping("/common/SearchStation")
    public String searchStation(@ModelAttribute("stationBean") @Valid StationViewBean stationBean,
                                BindingResult bindingResult,
                                ModelMap modelMap) {
        log.info("Servlet got viewBean: " + stationBean);
        if (bindingResult.hasErrors()) {
            return "/common/searchStation";
        }
        Collection<TrainViewBean> trains = trainControllersHelper.findTrainsByStation(stationBean);
        if (stationBean.isProcessingFailed()) {
            modelMap.addAttribute(StationViewBean.DEFAULT_NAME, stationBean);
            return "/common/searchStation";
        } else {
            modelMap.addAttribute(TRAINS, trains);
            return "/common/viewFoundTrains";
        }
    }
}
