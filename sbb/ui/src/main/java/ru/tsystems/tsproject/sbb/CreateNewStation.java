package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.service.AdministratorService;
import ru.tsystems.tsproject.sbb.serviceImpl.AdministratorServiceImpl;

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
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class CreateNewStation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=====================this is servlet " + this.getClass().getSimpleName());

        String action = request.getParameter("stationCreateAction");
        if (action.equals("back")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/administratorMain.jsp");
            requestDispatcher.forward(request, response);
        } else {
            String stationName = request.getParameter("Station name");
            if (!stationName.equals("")) {
                Station station = new Station();
                station.setName(stationName);
                AdministratorService administratorService = new AdministratorServiceImpl();
                administratorService.addStation(station);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/createStationSuccess.jsp");
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
