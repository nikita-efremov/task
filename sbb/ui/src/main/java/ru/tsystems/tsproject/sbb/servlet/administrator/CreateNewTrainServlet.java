package ru.tsystems.tsproject.sbb.servlet.administrator;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet, which gets and proceeds requests of train creation
 * @author  Nikita Efremov
 * @since   1.0
 */
public class CreateNewTrainServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CreateNewTrainServlet.class);
    private TrainModel trainModel;

    /**
     * Initialize servlet`s attribute - trainModel
     */
    public void init() {
        trainModel = CustomApplicationContext.getTrainModel();
    }

    /**
     * Method proceeds both GET and POST requests. It proceeds request of adding a new train
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
        String action = request.getParameter("trainCreateAction");
        if (action == null) {
            response.sendRedirect("/ui/administrator/train/createNewTrain.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/administrator/administratorMain.jsp");
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
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/train/createNewTrain.jsp");
                requestDispatcher.forward(request, response);
            } else {
                trainBean = trainModel.addTrain(trainBean);
                request.setAttribute("createResult", trainBean);
                RequestDispatcher requestDispatcher;
                if (trainBean.isProcessingFailed()) {
                    requestDispatcher = request.getRequestDispatcher("/administrator/train/createNewTrain.jsp");
                } else {
                    requestDispatcher = request.getRequestDispatcher("/administrator/train/createTrainSuccess.jsp");
                }
                requestDispatcher.forward(request, response);
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
