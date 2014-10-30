package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Controller, which gets and proceeds requests of getting all trains
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class ViewAllTrainsController {

    private static final Logger log = Logger.getLogger(ViewAllTrainsController.class);
    private TrainModel trainModel = CustomApplicationContext.getTrainModel();

    @RequestMapping("/administrator/train/ViewAllTrains")
    public String viewTrains(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Collection<TrainBean> trains = trainModel.getAllTrains();
        request.setAttribute("allTrains", trains);
        return "/administrator/train/viewAllTrains";
    }
}