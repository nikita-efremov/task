package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller, which gets and proceeds requests of train creation
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class CreateNewTrainController {

    private static final Logger log = Logger.getLogger(CreateNewTrainController.class);

    @Autowired
    private TrainModel trainModel;

    @RequestMapping(value = "/administrator/train/createNewTrain", method = RequestMethod.GET)
    public ModelAndView initTrainBean() {
        return new ModelAndView("/administrator/train/createNewTrain", "trainBean", new TrainBean());
    }

    @RequestMapping("/administrator/train/CreateNewTrain")
    public String addTrain(@ModelAttribute("trainBean") TrainBean trainBean, ModelMap modelMap) {
        trainBean.setSeats(trainBean.getTotalSeats());
        log.info("Servlet got bean: " + trainBean);
        ValidationBean validationBean = Validator.validate(trainBean);
        if (validationBean.isValidationFailed()) {
            modelMap.addAttribute("validationBean", validationBean);
            modelMap.addAttribute("trainBean", trainBean);
            return "/administrator/train/createNewTrain";
        } else {
            trainBean = trainModel.addTrain(trainBean);
            modelMap.addAttribute("trainBean", trainBean);
            if (trainBean.isProcessingFailed()) {
                return "/administrator/train/createNewTrain";
            } else {
                return "/administrator/train/createTrainSuccess";
            }
        }
    }
}
