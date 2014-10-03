package ru.tsystems.tsproject.sbb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class AdminActionResolver extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=====================this is servlet " + this.getClass().getSimpleName());

        String chosenAction = request.getParameter("adminAction");
        System.out.println("chosenAction=" + chosenAction);

        if (chosenAction.equals("Add new Station")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/createNewStation.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/administratorMain.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
