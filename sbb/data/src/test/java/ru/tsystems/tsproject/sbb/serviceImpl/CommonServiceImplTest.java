package ru.tsystems.tsproject.sbb.serviceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tsproject.sbb.dao.impl.*;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.service.impl.CommonServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 06.10.14
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class CommonServiceImplTest {

    @Mock
    private StationDAOImpl stationDAO = new StationDAOImpl(null);

    @Mock
    private TrainDAOImpl trainDAO = new TrainDAOImpl(null);

    @Mock
    private PassengerDAOImpl passengerDAO = new PassengerDAOImpl(null);

    @InjectMocks
    private CommonServiceImpl commonServiceImpl = new CommonServiceImpl(stationDAO, trainDAO, passengerDAO);

    @Test
    public void testFindStation() throws Exception {
        int stationID = 44044;
        String stationName = "TestStation";

        Station stationOutput = new Station();
        stationOutput.setId(stationID);
        stationOutput.setName(stationName);

        when(stationDAO.getStationByName(stationName)).thenReturn(stationOutput);

        Station stationFromMethod = commonServiceImpl.findStation(stationName);
        Assert.assertEquals(stationOutput, stationFromMethod);
    }
}
