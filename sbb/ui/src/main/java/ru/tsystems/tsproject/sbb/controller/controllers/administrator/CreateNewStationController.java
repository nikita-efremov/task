package ru.tsystems.tsproject.sbb.controller.controllers.administrator;

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
import ru.tsystems.tsproject.sbb.viewbean.StationViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.StationControllersHelper;

/**
 * Controller, which gets and proceeds requests of creation new station
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class CreateNewStationController {

    private static final Logger log = Logger.getLogger(CreateNewStationController.class);

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private StationControllersHelper stationControllersHelper;

    /**
     * Method adds stationViewBEan to the view and forwards to JSP with form of adding new station
     * @return ModelAndView
     *         JSP page with form of adding new station
     */
    @RequestMapping(value = "/administrator/station/createNewStation", method = RequestMethod.GET)
    public ModelAndView initStationBean() {
        return new ModelAndView("/administrator/station/createNewStation", "stationBean", new StationViewBean());
    }

    /**
     * Proceeds requests of creation new station and then forwards to appropriate page
     * @param stationBean
     *        ViewBean with information about new station
     * @param modelMap
     *        Map of viewBeans
     * @return JSP page address to forward
     */
    @RequestMapping("/administrator/station/CreateNewStation")
    public String addStation(@ModelAttribute("stationBean") StationViewBean stationBean, ModelMap modelMap) {
        log.info("Servlet got viewBean: " + stationBean);
        ValidationBean validationBean = Validator.validate(stationBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("stationBean", stationBean);
            return "/administrator/station/createNewStation";
        } else {
            stationBean = stationControllersHelper.addStation(stationBean);
            modelMap.addAttribute("stationBean", stationBean);
            if (stationBean.isProcessingFailed()) {
                return "/administrator/station/createNewStation";
            } else {
                return "/administrator/station/createStationSuccess";
            }
        }
    }
}
