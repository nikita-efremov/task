package ru.tsystems.tsproject.sbb.controller.common;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller, which gets and proceeds requests of getting train timetable
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class TrainTimetableController {

    private final static Logger log = Logger.getLogger(TrainTimetableController.class);
    private TrainModel trainModel = CustomApplicationContext.getTrainModel();

    @RequestMapping("/common/TrainTimetable")
    public String searchTrainTimetable(HttpServletRequest request,
                                       HttpServletResponse response, ModelMap model) {
        String action = request.getParameter("trainSearchAction");
        if (action == null) {
            return "redirect:/index.jsp";
        } else if (action.equals("back")) {
            return "redirect:/index.jsp";
        } else {
            TrainBean trainBean = new TrainBean();
            trainBean.setNumber(request.getParameter("Train_number"));
            log.info("Servlet got bean: " + trainBean);
            trainBean = trainModel.findTrain(trainBean);
            request.setAttribute("trainBean", trainBean);
            return "/common/trainTimetable";
        }
    }
}
