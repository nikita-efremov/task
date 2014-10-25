package ru.tsystems.tsproject.sbb.servlet.passenger;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.ApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;
import ru.tsystems.tsproject.sbb.model.TrainModel;

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
 * Servlet, which gets and proceeds requests of passenger registering
 * @author  Nikita Efremov
 * @since   1.0
 */
public class RegisterPassengerServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegisterPassengerServlet.class);
    private PassengerModel passengerModel;

    /**
     * Initialize servlet`s attribute - passengerModel
     */
    public void init() {
        passengerModel = ApplicationContext.getPassengerModel();
    }

    /**
     * Method proceeds both GET and POST requests. It proceeds request of passenger registering
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
        String action = request.getParameter("passengerRegisterAction");
        if (action == null) {
            response.sendRedirect("/ui/register.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/index.jsp");
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthDateString = request.getParameter("Birth_date");
            Date birthDate;
            try {
                birthDate = simpleDateFormat.parse(birthDateString);
            } catch (ParseException e) {
                birthDate = null;
            }

            PassengerBean passengerBean = new PassengerBean();
            passengerBean.setLastName(request.getParameter("Last_name"));
            passengerBean.setFirstName(request.getParameter("First_name"));
            passengerBean.setDocNumber(request.getParameter("Document_number"));
            passengerBean.setBirthDate(birthDate);
            log.info("Servlet got bean: " + passengerBean);
            ValidationBean validationBean = Validator.validate(passengerBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("createResult", passengerBean);
                request.setAttribute("validationBean", validationBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
                requestDispatcher.forward(request, response);
            } else {
                passengerBean = passengerModel.addPassenger(passengerBean);
                request.setAttribute("createResult", passengerBean);
                RequestDispatcher requestDispatcher;
                if (passengerBean.isProcessingFailed()) {
                    requestDispatcher = request.getRequestDispatcher("/register.jsp");
                } else {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("user", passengerBean.getLastName() + " " + passengerBean.getFirstName().charAt(0) + ".");
                    httpSession.setAttribute("passDoc", passengerBean.getDocNumber());
                    httpSession.setMaxInactiveInterval(30*60);
                    requestDispatcher = request.getRequestDispatcher("/passenger/registerSuccess.jsp");
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