package ru.tsystems.tsproject.sbb.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.tsystems.tsproject.sbb.dao.DAOException;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.exception.StationNotExistsException;
import ru.tsystems.tsproject.sbb.service.api.CommonService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Class produces soap web-service for station search
 * @author  Nikita Efremov
 * @since   2.0
 */
@Component
@WebService
public class CommonSOAPWebService extends SpringBeanAutowiringSupport {

    @Autowired
    @Qualifier(value = "commonServiceImpl")
    private CommonService commonService;

    @WebMethod
    public Station findStation(String stationName) throws StationNotExistsException, DAOException {
        return commonService.findStation(stationName);
    }

    @WebMethod(exclude = true)
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }
}
