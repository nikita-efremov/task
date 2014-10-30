package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of train searching and viewing
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTrainController {

    private static final Logger log = Logger.getLogger(SearchTrainController.class);
    private TrainModel trainModel = CustomApplicationContext.getTrainModel();

    @RequestMapping("/administrator/train/SearchTrain")
    public String searchTrain(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String action = request.getParameter("trainSearchAction");
        TrainBean trainBean = new TrainBean();
        trainBean.setNumber(request.getParameter("Train_number"));
        log.info("Servlet got bean: " + trainBean);
        if (action == null) {
            return "redirect:/administrator/train/searchTrain.jsp";
        } else if (action.equals("back")) {
            return "redirect:/administrator/administratorMain.jsp";
        } else if (action.equals("watch passengers")) {
            Collection<PassengerBean> passengerBeanSet = trainModel.findTrainPassengers(trainBean);
            request.setAttribute("trainPassengers", passengerBeanSet);
            request.setAttribute("trainBean", trainBean);
            return "/administrator/passengers/passengersOnTrain";
        } else if (action.equals("watch timetable")) {
            trainBean = trainModel.findTrain(trainBean);
            request.setAttribute("trainBean", trainBean);
            return "/administrator/timetable/trainTimetable";
        } else {
            ValidationBean validationBean = Validator.validate(trainBean, "number");
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("searchResult", trainBean);
                return "/administrator/train/searchTrain";
            } else {
                trainBean = trainModel.findTrain(trainBean);
                request.setAttribute("searchResult", trainBean);
                return "/administrator/train/searchTrain";
            }
        }
    }
}
