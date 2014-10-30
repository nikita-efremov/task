package ru.tsystems.tsproject.sbb.controller.common;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;


@Controller
public class SearchStationController {

    private static final Logger log = Logger.getLogger(SearchStationController.class);

    private TrainModel trainModel = CustomApplicationContext.getTrainModel();

    @RequestMapping("/common/SearchStation")
    public String searchStation(HttpServletRequest request,
                               HttpServletResponse response, ModelMap model) {

        StationBean stationBean = new StationBean();
        stationBean.setName(request.getParameter("Station_name"));
        log.info("Servlet got bean: " + stationBean);

        String action = request.getParameter("stationSearchAction");
        if (action == null) {
            return "redirect:common/searchStation.jsp";
        } else {
            ValidationBean validationBean = Validator.validate(stationBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("searchResult", stationBean);
                return "/common/searchStation";
            } else {
                Collection<TrainBean> trains = trainModel.findTrainsByStation(stationBean);
                if (stationBean.isProcessingFailed()) {
                    request.setAttribute("searchResult", stationBean);
                    return "common/searchStation";
                } else {
                    request.setAttribute("foundTrains", trains);
                    return "common/viewFoundTrains";
                }
            }
        }
    }
}
