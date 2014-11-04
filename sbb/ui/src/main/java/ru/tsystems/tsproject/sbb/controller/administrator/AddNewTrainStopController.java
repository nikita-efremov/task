package ru.tsystems.tsproject.sbb.controller.administrator;

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
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

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

    @Autowired
    private TrainModel trainModel;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    @RequestMapping(value = "/administrator/train/addNewTrainStop", method = RequestMethod.GET)
    public ModelAndView initTimetableBean(@RequestParam("Train_number") String trainNumber) {
        TimetableBean timetableBean = new TimetableBean();
        timetableBean.setTrainNumber(trainNumber);
        return new ModelAndView("/administrator/timetable/addNewTimetable", "timetableBean", timetableBean);
    }

    @RequestMapping("/administrator/train/AddNewTrainStop")
    public String addStrop(@ModelAttribute("timetableBean") TimetableBean timetableBean, ModelMap modelMap) {
        log.info("Servlet got bean:" + timetableBean);
        ValidationBean validationBean = Validator.validate(timetableBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("timetableBean", timetableBean);
            modelMap.addAttribute("validationBean", validationBean);
            return "/administrator/timetable/addNewTimetable";
        } else {
            timetableBean = trainModel.addTrainStop(timetableBean);
            if (timetableBean.isProcessingFailed()) {
                modelMap.addAttribute("timetableBean", timetableBean);
                return "/administrator/timetable/addNewTimetable";
            } else {
                TrainBean trainBean = new TrainBean();
                trainBean.setNumber(timetableBean.getTrainNumber());
                trainBean = trainModel.findTrain(trainBean);
                modelMap.addAttribute("trainBean", trainBean);
                return "/common/trainTimetable";
            }
        }
    }
}
