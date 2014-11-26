package ru.tsystems.tsproject.sbb.exception;

/**
 * Methods throws this exception, if they try to purchase ticket on train,
 * but ticket has already purchased
 * @author  Nikita Efremov
 * @since   1.0
 */
public class TicketAlreadyExistsException extends BusinessLogicException {

    public TicketAlreadyExistsException(String message) {
        super(message);
    }
}
