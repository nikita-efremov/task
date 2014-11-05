package ru.tsystems.tsproject.sbb.controller.controllers.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.validation.ValidationBean;
import ru.tsystems.tsproject.sbb.validation.Validator;
import ru.tsystems.tsproject.sbb.viewbean.TimetableViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller, which gets and proceeds requests of adding a new stop for train
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class AddNewTrainStopController {

    private static final Logger log = Logger.getLogger(AddNewTrainStopController.class);

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
     * Method adds timetableViewBean to view with specified trainNumber and forwards to JSP with form of adding new train stop
     * @param trainNumber
     *        Number of train, on which stop must be added
     * @return ModelAndView
     *         JSP page with form of adding new train stop
     */
    @RequestMapping(value = "/administrator/train/addNewTrainStop", method = RequestMethod.GET)
    public ModelAndView initTimetableBean(@RequestParam("Train_number") String trainNumber) {
        TimetableViewBean timetableBean = new TimetableViewBean();
        timetableBean.setTrainNumber(trainNumber);
        return new ModelAndView("/administrator/timetable/addNewTimetable", TimetableViewBean.DEFAULT_NAME, timetableBean);
    }

    /**
     * Proceeds requests of adding a new stop for train and then forwards to appropriate page
     * @param timetableBean
     *        ViewBean with information about new stop of the train
     * @param modelMap
     *        Map of view beans
     * @return JSP page address to forward
     */
    @RequestMapping("/administrator/train/AddNewTrainStop")
    public String addStrop(@ModelAttribute("timetableBean") TimetableViewBean timetableBean, ModelMap modelMap) {
        log.info("Servlet got viewBean:" + timetableBean);
        ValidationBean validationBean = Validator.validate(timetableBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute(TimetableViewBean.DEFAULT_NAME, timetableBean);
            modelMap.addAttribute(ValidationBean.DEFAULT_NAME, validationBean);
            return "/administrator/timetable/addNewTimetable";
        } else {
            timetableBean = trainControllersHelper.addTrainStop(timetableBean);
            if (timetableBean.isProcessingFailed()) {
                modelMap.addAttribute(TimetableViewBean.DEFAULT_NAME, timetableBean);
                return "/administrator/timetable/addNewTimetable";
            } else {
                TrainViewBean trainBean = new TrainViewBean();
                trainBean.setNumber(timetableBean.getTrainNumber());
                trainBean = trainControllersHelper.findTrain(trainBean);
                modelMap.addAttribute(TrainViewBean.DEFAULT_NAME, trainBean);
                return "/common/trainTimetable";
            }
        }
    }
}
