package ru.tsystems.tsproject.sbb.controller.administrator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.model.StationModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller, which gets and proceeds requests of creation new station
 * @author  Nikita Efremov
 * @since   2.0
 */
@Controller
public class CreateNewStationController {

    private static final Logger log = Logger.getLogger(CreateNewStationController.class);
    private StationModel stationModel = CustomApplicationContext.getStationModel();

    @RequestMapping("/administrator/station/CreateNewStation")
    public String addStation(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String action = request.getParameter("stationCreateAction");
        if (action == null) {
            return "redirect:/administrator/station/createNewStation.jsp";
        } else if (action.equals("back")) {
            return "redirect:/administrator/administratorMain.jsp";
        } else {
            StationBean stationBean = new StationBean();
            stationBean.setName(request.getParameter("Station_name"));
            log.info("Servlet got bean: " + stationBean);
            ValidationBean validationBean = Validator.validate(stationBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("createResult", stationBean);
                return "/administrator/station/createNewStation";
            } else {
                stationBean = stationModel.addStation(stationBean);
                request.setAttribute("createResult", stationBean);
                if (stationBean.isProcessingFailed()) {
                    return "/administrator/station/createNewStation";
                } else {
                    return "/administrator/station/createStationSuccess";
                }
            }
        }
    }
}
