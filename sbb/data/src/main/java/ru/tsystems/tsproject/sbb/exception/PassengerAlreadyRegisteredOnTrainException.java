package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to register on train passenger with last name, first name and birth date,
 * which match with another registered on train passenger
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerAlreadyRegisteredOnTrainException extends BusinessLogicException {

    public PassengerAlreadyRegisteredOnTrainException(String message) {
        super(message);
    }
}
