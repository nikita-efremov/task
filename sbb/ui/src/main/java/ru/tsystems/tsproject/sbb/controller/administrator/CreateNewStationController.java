package ru.tsystems.tsproject.sbb.controller.administrator;

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
import ru.tsystems.tsproject.sbb.model.StationModel;

/**
 * Controller, which gets and proceeds requests of creation new station
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class CreateNewStationController {

    private static final Logger log = Logger.getLogger(CreateNewStationController.class);

    @Autowired
    private StationModel stationModel;

    @RequestMapping(value = "/administrator/station/createNewStation", method = RequestMethod.GET)
    public ModelAndView initStationBean() {
        return new ModelAndView("/administrator/station/createNewStation", "stationBean", new StationBean());
    }

    @RequestMapping("/administrator/station/CreateNewStation")
    public String addStation(@ModelAttribute("stationBean") StationBean stationBean, ModelMap modelMap) {
        log.info("Servlet got bean: " + stationBean);
        ValidationBean validationBean = Validator.validate(stationBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("stationBean", stationBean);
            return "/administrator/station/createNewStation";
        } else {
            stationBean = stationModel.addStation(stationBean);
            modelMap.addAttribute("stationBean", stationBean);
            if (stationBean.isProcessingFailed()) {
                return "/administrator/station/createNewStation";
            } else {
                return "/administrator/station/createStationSuccess";
            }
        }
    }
}
