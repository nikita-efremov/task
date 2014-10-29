package ru.tsystems.tsproject.sbb.servlet.passenger;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.TicketBean;
import ru.tsystems.tsproject.sbb.model.PassengerModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet, which gets and proceeds requests of ticket purchasing
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PurchaseTicketServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(PurchaseTicketServlet.class);
    private PassengerModel passengerModel;

    /**
     * Initialize servlet`s attribute - passengerModel
     */
    public void init() {
        passengerModel = CustomApplicationContext.getPassengerModel();
    }

    /**
     * Method proceeds both GET and POST requests. It proceeds request of ticket purchasing
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
        String action = request.getParameter("purchaseAction");
        if (action == null) {
            response.sendRedirect("/ui/passenger/purchase.jsp");
        } else if (action.equals("Back")) {
            response.sendRedirect("/ui/index.jsp");
        } else {
            TicketBean ticketBean = new TicketBean();
            ticketBean.setTrainNumber(request.getParameter("Train_number"));
            ticketBean.setPassengerDocNumber((String)request.getSession().getAttribute("passDoc"));
            log.info("Servlet got bean: " + ticketBean);
            ValidationBean validationBean = Validator.validate(ticketBean, "trainNumber");
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("purchaseResult", ticketBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/passenger/purchase.jsp");
                requestDispatcher.forward(request, response);
            } else {
                ticketBean = passengerModel.purchaseTicket(ticketBean);
                request.setAttribute("purchaseResult", ticketBean);
                RequestDispatcher requestDispatcher;
                if (ticketBean.isProcessingFailed()) {
                    requestDispatcher = request.getRequestDispatcher("/passenger/purchase.jsp");
                } else {
                    requestDispatcher = request.getRequestDispatcher("/passenger/purchaseSuccess.jsp");
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