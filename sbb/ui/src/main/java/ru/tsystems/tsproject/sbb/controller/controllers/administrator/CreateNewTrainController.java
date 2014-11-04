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
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

/**
 * Controller, which gets and proceeds requests of train creation
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class CreateNewTrainController {

    private static final Logger log = Logger.getLogger(CreateNewTrainController.class);

    @Autowired
    private TrainControllersHelper trainControllersHelper;

    @RequestMapping(value = "/administrator/train/createNewTrain", method = RequestMethod.GET)
    public ModelAndView initTrainBean() {
        return new ModelAndView("/administrator/train/createNewTrain", "trainBean", new TrainViewBean());
    }

    @RequestMapping("/administrator/train/CreateNewTrain")
    public String addTrain(@ModelAttribute("trainBean") TrainViewBean trainBean, ModelMap modelMap) {
        trainBean.setSeats(trainBean.getTotalSeats());
        log.info("Servlet got viewBean: " + trainBean);
        ValidationBean validationBean = Validator.validate(trainBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("trainBean", trainBean);
            return "/administrator/train/createNewTrain";
        } else {
            trainBean = trainControllersHelper.addTrain(trainBean);
            modelMap.addAttribute("trainBean", trainBean);
            if (trainBean.isProcessingFailed()) {
                return "/administrator/train/createNewTrain";
            } else {
                return "/administrator/train/createTrainSuccess";
            }
        }
    }
}
