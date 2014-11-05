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
    private static final String TRAINS = "foundTrains";

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private TrainControllersHelper trainControllersHelper;

    /**
     * Creates date formatter for jsp
     *
     * @param binder
     *        Binder which binds data in view
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    /**
     * Adds complexTrainSearchViewBean to the view and forwards to JSP with form of searching trains by stations and date
     * @return JSP address to forward
     */
    @RequestMapping(value = "/common/searchStationDateTrain", method = RequestMethod.GET)
    public ModelAndView initTimetableBean() {
        return new ModelAndView("/common/searchStationDateTrain", ComplexTrainSearchViewBean.DEFAULT_NAME, new ComplexTrainSearchViewBean());
    }

    /**
     * Proceeds requests of searching trains by stations and date and then forwards to appropriate page
     * @param complexTrainSearchBean
     *        ViewBean with search information
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to forward
     */
    @RequestMapping("/common/SearchStationDateTrain")
    public String searchTrains(@ModelAttribute("complexTrainSearchBean") ComplexTrainSearchViewBean complexTrainSearchBean, ModelMap modelMap) {
        log.info("Servlet got viewBean " + complexTrainSearchBean);
        ValidationBean validationBean = Validator.validate(complexTrainSearchBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute(ValidationBean.DEFAULT_NAME, validationBean);
            modelMap.addAttribute(ComplexTrainSearchViewBean.DEFAULT_NAME, complexTrainSearchBean);
            return "/common/searchStationDateTrain";
        } else {
            Collection<TrainViewBean> trains = trainControllersHelper.findTrainsByStationsAndDate(complexTrainSearchBean);
            if (complexTrainSearchBean.isProcessingFailed()) {
                modelMap.addAttribute(ComplexTrainSearchViewBean.DEFAULT_NAME, complexTrainSearchBean);
                return "/common/searchStationDateTrain";
            } else {
                modelMap.addAttribute(TRAINS, trains);
                return "/common/viewFoundTrains";
            }
        }
    }
}
