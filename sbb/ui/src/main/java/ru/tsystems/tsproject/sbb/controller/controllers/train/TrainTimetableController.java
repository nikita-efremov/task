package ru.tsystems.tsproject.sbb.controller.controllers.train;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.tsproject.sbb.viewbean.TrainViewBean;
import ru.tsystems.tsproject.sbb.controller.helpers.TrainControllersHelper;

/**
 * Controller, which gets and proceeds requests of getting train timetable
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class TrainTimetableController {

    private final static Logger log = Logger.getLogger(TrainTimetableController.class);

    /**
     * Used for mapping data and launching service methods
     */
    @Autowired
    private TrainControllersHelper trainControllersHelper;

    /**
     * Proceeds requests of getting train timetable and forwards to watching timetable view page
     * @param trainNumber
     *        Number of the train to search timetable
     * @param modelMap
     *        Map with viewBeans
     * @return JSP address to forward
     */
    @RequestMapping(value = "/common/TrainTimetable",
            method = RequestMethod.GET,
            params = "trainSearchAction=watch timetable")
    public String watchTimetable(@RequestParam("Train_number") String trainNumber, ModelMap modelMap) {
        TrainViewBean trainBean = new TrainViewBean();
        trainBean.setNumber(trainNumber);
        log.info("Servlet got viewBean: " + trainBean);
        trainBean = trainControllersHelper.findTrain(trainBean);
        modelMap.addAttribute(TrainViewBean.DEFAULT_NAME, trainBean);
        return "/common/trainTimetable";
    }
}
