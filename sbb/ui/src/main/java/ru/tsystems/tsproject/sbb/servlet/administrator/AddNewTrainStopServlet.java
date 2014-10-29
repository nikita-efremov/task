package ru.tsystems.tsproject.sbb.servlet.administrator;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet, which gets and proceeds requests of adding a new stop for train
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AddNewTrainStopServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddNewTrainStopServlet.class);
    private TrainModel trainModel;

    /**
     * Initialize servlet`s attribute - trainModel
     */
    public void init() {
        trainModel = CustomApplicationContext.getTrainModel();
    }

    /**
     * Method proceeds both GET and POST requests. It proceeds request of adding a new stop for train
     * and after proceeding sends result to view
     * @param request   an {@link javax.servlet.http.HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link javax.servlet.http.HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception java.io.IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception javax.servlet.ServletException  if the request for the GET
     *                                  could not be handled
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("stopAddAction");
        if (action == null) {
            request.setAttribute("trainNumber", request.getParameter("Train_number"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/timetable/addNewTimetable.jsp");
            requestDispatcher.forward(request, response);
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/administrator/administratorMain.jsp");
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
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/timetable/addNewTimetable.jsp");
                requestDispatcher.forward(request, response);
            } else {
                timetableBean = trainModel.addTrainStop(timetableBean);
                if (timetableBean.isProcessingFailed()) {
                    request.setAttribute("timetableBean", timetableBean);
                    request.setAttribute("trainNumber", request.getParameter("Train_number"));
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/timetable/addNewTimetable.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    TrainBean trainBean = new TrainBean();
                    trainBean.setNumber(request.getParameter("Train_number"));
                    trainBean = trainModel.findTrain(trainBean);
                    request.setAttribute("trainBean", trainBean);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/timetable/trainTimetable.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        }
    }

    /**
     * Method proceeds GET request. It delegates processing to method processRequest(..)
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception ServletException  if the request for the GET
     *                                  could not be handled
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method proceeds POST request. It delegates processing to method processRequest(..)
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception ServletException  if the request for the GET
     *                                  could not be handled
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
