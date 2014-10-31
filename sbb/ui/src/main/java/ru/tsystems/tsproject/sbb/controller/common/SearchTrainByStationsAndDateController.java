package ru.tsystems.tsproject.sbb.controller.common;

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
import java.util.Collection;
import java.util.Date;

/**
 * Controller, which gets and proceeds requests of searching trains by stations and date
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class SearchTrainByStationsAndDateController {

    private static final Logger log = Logger.getLogger(SearchTrainByStationsAndDateController.class);

    @Autowired
    private TrainModel trainModel;

    @RequestMapping("/common/SearchStationDateTrain")
    public String searchTrains(HttpServletRequest request,
                                HttpServletResponse response, ModelMap model) {
        String action = request.getParameter("stationDateTrainSearchAction");
        if (action == null) {
            return "redirect:/common/searchStationDateTrain.jsp";
        } else if (action.equals("back")) {
            return "redirect:/index.jsp";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            Date startDate;
            Date endDate;
            try {
                String startDateString = request.getParameter("Start_date");
                startDate = simpleDateFormat.parse(startDateString);
            } catch (ParseException e) {
                startDate = null;
            }
            try {
                String endDateString = request.getParameter("End_date");
                endDate = simpleDateFormat.parse(endDateString);
            } catch (ParseException e) {
                endDate = null;
            }

            TimetableBean startBean = new TimetableBean();
            startBean.setStationName(request.getParameter("Station_start_name"));
            startBean.setDate(startDate);

            TimetableBean endBean = new TimetableBean();
            endBean.setStationName(request.getParameter("Station_end_name"));
            endBean.setDate(endDate);

            log.info("Servlet got beans: " + startBean + " " + endBean);

            ValidationBean validationBeanStart = Validator.validate(startBean, "stationName", "date");
            ValidationBean validationBeanEnd = Validator.validate(endBean, "stationName", "date");
            if ((validationBeanStart.isValidationFailed()) || (validationBeanEnd.isValidationFailed())) {
                request.setAttribute("validationBeanStart", validationBeanStart);
                request.setAttribute("validationBeanEnd", validationBeanEnd);
                request.setAttribute("startBean", startBean);
                request.setAttribute("endBean", endBean);
                return "/common/searchStationDateTrain";
            } else {
                Collection<TrainBean> trains = trainModel.findTrainsByStationsAndDate(startBean, endBean);
                if ((startBean.isProcessingFailed()) || (endBean.isProcessingFailed())) {
                    request.setAttribute("startBean", startBean);
                    request.setAttribute("endBean", endBean);
                    return "/common/searchStationDateTrain";
                } else {
                    request.setAttribute("foundTrains", trains);
                    return "/common/viewFoundTrains";
                }
            }
        }
    }
}
