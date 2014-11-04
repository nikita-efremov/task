package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.validation.ValidationBean;
import ru.tsystems.tsproject.sbb.validation.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of train searching and viewing
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTrainController {

    private static final Logger log = Logger.getLogger(SearchTrainController.class);

    @Autowired
    private TrainModel trainModel;

    @RequestMapping(value = "/administrator/train/searchTrain", method = RequestMethod.GET)
    public ModelAndView initTrainBean() {
        return new ModelAndView("/administrator/train/searchTrain", "trainBean", new TrainBean());
    }

    @RequestMapping(value = "/administrator/train/SearchTrain",
            method = RequestMethod.GET,
            params = "trainSearchAction=watch passengers")
    public String watchPassengers(@RequestParam("Train_number") String trainNumber, ModelMap modelMap) {
        TrainBean trainBean = new TrainBean();
        trainBean.setNumber(trainNumber);
        Collection<PassengerBean> passengerBeanSet = trainModel.findTrainPassengers(trainBean);
        modelMap.addAttribute("trainPassengers", passengerBeanSet);
        modelMap.addAttribute("trainBean", trainBean);
        return "/administrator/passengers/passengersOnTrain";
    }

    @RequestMapping(value = "/administrator/train/SearchTrain",
            method = RequestMethod.GET,
            params = "trainSearchAction=watch timetable")
    public String watchTimetable(@RequestParam("Train_number") String trainNumber, ModelMap modelMap) {
        TrainBean trainBean = new TrainBean();
        trainBean.setNumber(trainNumber);
        trainBean = trainModel.findTrain(trainBean);
        modelMap.addAttribute("trainBean", trainBean);
        return "/common/trainTimetable";
    }

    @RequestMapping(value = "/administrator/train/SearchTrain", method = RequestMethod.POST)
    public String searchTrain(@ModelAttribute("trainBean") TrainBean trainBean, ModelMap modelMap) {
        log.info("Servlet got bean: " + trainBean);
        ValidationBean validationBean = Validator.validate(trainBean, "number");
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
        } else {
            trainBean = trainModel.findTrain(trainBean);
        }
        modelMap.addAttribute("trainBean", trainBean);
        return "/administrator/train/searchTrain";
    }
}
