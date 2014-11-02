package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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

    @RequestMapping("/administrator/train/AddNewTrainStop")
    public String addStrop(HttpServletRequest request) {
        String action = request.getParameter("stopAddAction");
        if (action == null) {
            request.setAttribute("trainNumber", request.getParameter("Train_number"));
            return "/administrator/timetable/addNewTimetable";
        } else if (action.equals("back")) {
            return "redirect:/administrator/administratorMain.jsp";
        } else {
            String depDateString = request.getParameter("Departure_date");
            Date depDate;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                depDate = simpleDateFormat.parse(depDateString);
            } catch (ParseException e) {
                depDate = null;
            }
            TimetableBean timetableBean = new TimetableBean();
            timetableBean.setStationName(request.getParameter("Station_name"));
            timetableBean.setDate(depDate);
            timetableBean.setTrainNumber(request.getParameter("Train_number"));
            log.info("Servlet got bean:" + timetableBean);
            ValidationBean validationBean = Validator.validate(timetableBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("timetableBean", timetableBean);
                request.setAttribute("trainNumber", request.getParameter("Train_number"));
                request.setAttribute("validationBean", validationBean);
                return "/administrator/timetable/addNewTimetable";
            } else {
                timetableBean = trainModel.addTrainStop(timetableBean);
                if (timetableBean.isProcessingFailed()) {
                    request.setAttribute("timetableBean", timetableBean);
                    request.setAttribute("trainNumber", request.getParameter("Train_number"));
                    return "/administrator/timetable/addNewTimetable";
                } else {
                    TrainBean trainBean = new TrainBean();
                    trainBean.setNumber(request.getParameter("Train_number"));
                    trainBean = trainModel.findTrain(trainBean);
                    request.setAttribute("trainBean", trainBean);
                    return "/administrator/timetable/trainTimetable";
                }
            }
        }
    }
}
