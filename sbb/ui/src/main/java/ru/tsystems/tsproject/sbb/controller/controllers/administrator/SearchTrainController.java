package ru.tsystems.tsproject.sbb.controller.controllers.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.validation.ValidationBean;
import ru.tsystems.tsproject.sbb.validation.Validator;
import ru.tsystems.tsproject.sbb.viewbean.PassengerViewBean;
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of train searching and viewing
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTrainController {

    private static final Logger log = Logger.getLogger(SearchTrainController.class);
    private static final String TRAIN_PASSENGERS = "trainPassengers";


    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private TrainControllersHelper trainControllersHelper;

    /**
     * Adds trainViewBean to the view and forwards to JSP with form of searching train
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/administrator/train/searchTrain", method = RequestMethod.GET)
    public ModelAndView initTrainBean() {
        return new ModelAndView("/administrator/train/searchTrain", TrainViewBean.DEFAULT_NAME, new TrainViewBean());
    }

    /**
     * Proceeds requests of watching train passengers and then forwards to watching passengers page
     * @param trainNumber
     *        Number of the train
     * @param modelMap
     *        Map with view beans
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/administrator/train/SearchTrain",
            method = RequestMethod.GET,
            params = "trainSearchAction=watch passengers")
    public String watchPassengers(@RequestParam("Train_number") String trainNumber, ModelMap modelMap) {
        TrainViewBean trainBean = new TrainViewBean();
        trainBean.setNumber(trainNumber);
        Collection<PassengerViewBean> passengerBeanSet = trainControllersHelper.findTrainPassengers(trainBean);
        modelMap.addAttribute(TRAIN_PASSENGERS, passengerBeanSet);
        modelMap.addAttribute(TrainViewBean.DEFAULT_NAME, trainBean);
        return "/administrator/passengers/passengersOnTrain";
    }

    /**
     * Proceeds requests of watching train timetable and then forwards to watching timetable page
     * @param trainNumber
     *        Number of the train
     * @param modelMap
     *        Map with view beans
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/administrator/train/SearchTrain",
            method = RequestMethod.GET,
            params = "trainSearchAction=watch timetable")
    public String watchTimetable(@RequestParam("Train_number") String trainNumber, ModelMap modelMap) {
        TrainViewBean trainBean = new TrainViewBean();
        trainBean.setNumber(trainNumber);
        trainBean = trainControllersHelper.findTrain(trainBean);
        modelMap.addAttribute(TrainViewBean.DEFAULT_NAME, trainBean);
        return "/common/trainTimetable";
    }

    /**
     * Proceeds requests od train searching and then forwards to train search page
     * @param trainBean
     *        ViewBean with train information
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to forward
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/administrator/train/SearchTrain", method = RequestMethod.POST)
    public String searchTrain(@ModelAttribute("trainBean") TrainViewBean trainBean, ModelMap modelMap) {
        log.info("Servlet got viewBean: " + trainBean);
        ValidationBean validationBean = Validator.validate(trainBean, "number");
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute(ValidationBean.DEFAULT_NAME, validationBean);
        } else {
            trainBean = trainControllersHelper.findTrain(trainBean);
        }
        modelMap.addAttribute(TrainViewBean.DEFAULT_NAME, trainBean);
        return "/administrator/train/searchTrain";
    }
}
