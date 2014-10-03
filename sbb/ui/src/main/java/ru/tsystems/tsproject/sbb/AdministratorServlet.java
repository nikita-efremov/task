package ru.tsystems.tsproject.sbb; /**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 03.10.14
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


public class AdministratorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=====================this is servlet " + this.getClass().getSimpleName());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/administratorMain.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }



}
