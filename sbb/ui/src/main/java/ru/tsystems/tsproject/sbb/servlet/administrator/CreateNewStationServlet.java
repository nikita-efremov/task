package ru.tsystems.tsproject.sbb.servlet.administrator;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.ApplicationContext;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.model.StationModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet launches creating new station, than it analyzes result and send to view
 * @author  Nikita Efremov
 * @since   1.0
 */
public class CreateNewStationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CreateNewStationServlet.class);
    private StationModel stationModel;

    /**
     * Initialize servlet`s attribute - stationModel
     */
    public void init() {
        stationModel = ApplicationContext.getStationModel();
    }

    /**
     * Method proceeds both GET and POST requests. It launches station creation, analyses result of creation, send result to view
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("stationCreateAction");
        if (action == null) {
            response.sendRedirect("/ui/administrator/station/createNewStation.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/administrator/administratorMain.jsp");
        } else {
            StationBean stationBean = new StationBean();
            stationBean.setName(request.getParameter("Station name"));
            log.info("Servlet got bean: " + stationBean);
            stationBean.validate();
            if (stationBean.isValidationFailed()) {
                request.setAttribute("createResult", stationBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/station/createNewStation.jsp");
                requestDispatcher.forward(request, response);
            } else {
                stationBean = stationModel.addStation(stationBean);
                request.setAttribute("createResult", stationBean);
                RequestDispatcher requestDispatcher;
                if (stationBean.isProcessingFailed()) {
                    requestDispatcher = request.getRequestDispatcher("/administrator/station/createNewStation.jsp");
                } else {
                    requestDispatcher = request.getRequestDispatcher("/administrator/station/createStationSuccess.jsp");
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
