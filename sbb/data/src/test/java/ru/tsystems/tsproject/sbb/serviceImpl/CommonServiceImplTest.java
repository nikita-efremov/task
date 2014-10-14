package ru.tsystems.tsproject.sbb.serviceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tsproject.sbb.daoImpl.StationDAOImpl;
import ru.tsystems.tsproject.sbb.entity.Station;

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

    @InjectMocks
    private CommonServiceImpl commonServiceImpl = new CommonServiceImpl();

    @Test
    public void testGetStationInfoPart1() throws Exception {
        int stationID = 44044;
        String stationName = "TestStation";
        String anotherStationName = "AnotherTestStation";

        Station stationSource = new Station();
        stationSource.setId(stationID);
        stationSource.setName(anotherStationName);

        Station stationOutput = new Station();
        stationOutput.setId(stationID);
        stationOutput.setName(stationName);

        when(stationDAO.getStationById(stationID)).thenReturn(stationOutput);

        Station stationFromMethod = commonServiceImpl.getStationInfo(stationSource);
        Assert.assertEquals(stationOutput, stationFromMethod);
    }

    @Test
    public void testGetStationInfoPart2() throws Exception {
        int stationID = 44044;
        int anotherStationID = 0;
        String stationName = "TestStation";

        Station stationSource = new Station();
        stationSource.setId(anotherStationID);
        stationSource.setName(stationName);

        Station stationOutput = new Station();
        stationOutput.setId(stationID);
        stationOutput.setName(stationName);

        when(stationDAO.getStationByName(stationName)).thenReturn(stationOutput);

        Station stationFromMethod = commonServiceImpl.getStationInfo(stationSource);
        Assert.assertEquals(stationOutput, stationFromMethod);
    }
}
