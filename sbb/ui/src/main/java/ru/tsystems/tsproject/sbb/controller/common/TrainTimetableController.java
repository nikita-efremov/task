package ru.tsystems.tsproject.sbb.controller.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private TrainModel trainModel;

    @RequestMapping(value = "/common/TrainTimetable",
            method = RequestMethod.GET,
            params = "trainSearchAction=watch timetable")
    public String watchTimetable(@RequestParam("Train_number") String trainNumber, ModelMap modelMap) {
        TrainBean trainBean = new TrainBean();
        trainBean.setNumber(trainNumber);
        log.info("Servlet got bean: " + trainBean);
        trainBean = trainModel.findTrain(trainBean);
        modelMap.addAttribute("trainBean", trainBean);
        return "/common/trainTimetable";
    }
}
