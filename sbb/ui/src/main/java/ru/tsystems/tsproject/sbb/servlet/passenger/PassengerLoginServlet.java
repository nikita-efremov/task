package ru.tsystems.tsproject.sbb.servlet.passenger;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.ApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet launches passenger login process, than it analyzes result and send to view
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerLoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(PassengerLoginServlet.class);
    private PassengerModel passengerModel;

    /**
     * Initialize servlet`s attribute - passengerModel
     */
    public void init() {
        passengerModel = ApplicationContext.getPassengerModel();
    }

    /**
     * Method proceeds both GET and POST requests. It launches passenger login process, analyses result of creation, send result to view
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
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("passDoc");
        String action = request.getParameter("loginAction");
        if (action == null) {
            response.sendRedirect("/ui/passengerLogin.jsp");
        } else if (action.equals("Back")) {
            response.sendRedirect("/ui/index.jsp");
        } else {
            PassengerBean passengerBean = new PassengerBean();
            passengerBean.setDocNumber(request.getParameter("Document_number"));
            log.info("Servlet got bean: " + passengerBean);
            ValidationBean validationBean = Validator.validate(passengerBean, "docNumber");
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("loginResult", passengerBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/passengerLogin.jsp");
                requestDispatcher.forward(request, response);
            } else {
                passengerBean = passengerModel.getPassenger(passengerBean);
                request.setAttribute("loginResult", passengerBean);
                RequestDispatcher requestDispatcher;
                if (passengerBean.isProcessingFailed()) {
                    requestDispatcher = request.getRequestDispatcher("/passengerLogin.jsp");
                } else {
                    httpSession.setAttribute("user", passengerBean.getLastName() + " " + passengerBean.getFirstName().charAt(0) + ".");
                    httpSession.setAttribute("passDoc", passengerBean.getDocNumber());
                    httpSession.setMaxInactiveInterval(30*60);
                    log.info("Passenger with docNumber=" + passengerBean.getDocNumber() + " logged in");
                    requestDispatcher = request.getRequestDispatcher("/index.jsp");
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
