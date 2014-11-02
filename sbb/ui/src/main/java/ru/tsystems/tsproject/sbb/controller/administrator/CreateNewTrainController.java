package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/administrator/train/CreateNewTrain")
    public String addTrain(HttpServletRequest request) {
        String action = request.getParameter("trainCreateAction");
        if (action == null) {
            return "redirect:/administrator/train/createNewTrain.jsp";
        } else if (action.equals("back")) {
            return "redirect:/administrator/administratorMain.jsp";
        } else {
            TrainBean trainBean = new TrainBean();
            trainBean.setNumber(request.getParameter("Train_number"));
            trainBean.setSeats(request.getParameter("Total_seats"));
            trainBean.setTotalSeats(request.getParameter("Total_seats"));
            log.info("Servlet got bean: " + trainBean);
            ValidationBean validationBean = Validator.validate(trainBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("createResult", trainBean);
                return "/administrator/train/createNewTrain";
            } else {
                trainBean = trainModel.addTrain(trainBean);
                request.setAttribute("createResult", trainBean);
                if (trainBean.isProcessingFailed()) {
                    return "/administrator/train/createNewTrain";
                } else {
                    return "/administrator/train/createTrainSuccess";
                }
            }
        }
    }
}
