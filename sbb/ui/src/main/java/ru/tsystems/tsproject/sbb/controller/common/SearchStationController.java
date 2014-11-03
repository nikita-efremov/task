package ru.tsystems.tsproject.sbb.controller.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.validation.ValidationBean;
import ru.tsystems.tsproject.sbb.validation.Validator;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of station timetable searching
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchStationController {

    private static final Logger log = Logger.getLogger(SearchStationController.class);

    @Autowired
    private TrainModel trainModel;

    @RequestMapping(value = "/common/searchStation", method = RequestMethod.GET)
    public ModelAndView initStationBean() {
        return new ModelAndView("/common/searchStation", "stationBean", new StationBean());
    }

    @RequestMapping("/common/SearchStation")
    public String searchStation(@ModelAttribute("stationBean") StationBean stationBean, ModelMap modelMap) {
        log.info("Servlet got bean: " + stationBean);
        ValidationBean validationBean = Validator.validate(stationBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("stationBean", stationBean);
            return "/common/searchStation";
        } else {
            Collection<TrainBean> trains = trainModel.findTrainsByStation(stationBean);
            if (stationBean.isProcessingFailed()) {
                modelMap.addAttribute("stationBean", stationBean);
                return "common/searchStation";
            } else {
                modelMap.addAttribute("foundTrains", trains);
                return "common/viewFoundTrains";
            }
        }
    }
}
