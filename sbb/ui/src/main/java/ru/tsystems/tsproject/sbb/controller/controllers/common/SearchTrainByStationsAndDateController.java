package ru.tsystems.tsproject.sbb.controller.controllers.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import ru.tsystems.tsproject.sbb.viewbean.ComplexTrainSearchViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Controller, which gets and proceeds requests of searching trains by stations and date
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTrainByStationsAndDateController {

    private static final Logger log = Logger.getLogger(SearchTrainByStationsAndDateController.class);

    @Autowired
    private TrainControllersHelper trainControllersHelper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    @RequestMapping(value = "/common/searchStationDateTrain", method = RequestMethod.GET)
    public ModelAndView initTimetableBean() {
        return new ModelAndView("/common/searchStationDateTrain", "complexTrainSearchBean", new ComplexTrainSearchViewBean());
    }

    @RequestMapping("/common/SearchStationDateTrain")
    public String searchTrains(@ModelAttribute("complexTrainSearchBean") ComplexTrainSearchViewBean complexTrainSearchBean, ModelMap modelMap) {
        log.info("Servlet got viewBean " + complexTrainSearchBean);
        ValidationBean validationBean = Validator.validate(complexTrainSearchBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("complexTrainSearchBean", complexTrainSearchBean);
            return "/common/searchStationDateTrain";
        } else {
            Collection<TrainViewBean> trains = trainControllersHelper.findTrainsByStationsAndDate(complexTrainSearchBean);
            if (complexTrainSearchBean.isProcessingFailed()) {
                modelMap.addAttribute("complexTrainSearchBean", complexTrainSearchBean);
                return "/common/searchStationDateTrain";
            } else {
                modelMap.addAttribute("foundTrains", trains);
                return "/common/viewFoundTrains";
            }
        }
    }
}
