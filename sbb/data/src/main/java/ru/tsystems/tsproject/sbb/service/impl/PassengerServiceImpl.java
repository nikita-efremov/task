package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.service.api.PassengerService;

/**
 *
 * PassengerService interface implementation
 * @author  Nikita Efremov
 * @since   1.0
 */
public abstract class PassengerServiceImpl extends AbstractServiceImpl implements PassengerService {

    public PassengerServiceImpl(StationDAO stationDAO) {
        super(stationDAO);
    }
}
