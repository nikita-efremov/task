package ru.tsystems.tsproject.sbb.validation;

/**
 * Class, which contains validation messages and validation flag.
 * It is using for representing validation errors on view layer
 * @author  Nikita Efremov
 * @since   1.0
 */
public class ValidationBean {

    public static final String DEFAULT_NAME = "validationBean";

    /**
     * Text of validation errors
     */
    private String validationMessage = "";

    /**
     * Flag which shows, failed validation or not
     */
    private boolean validationFailed = Boolean.FALSE;

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public boolean isValidationFailed() {
        return validationFailed;
    }

    public void setValidationFailed(boolean validationFailed) {
        this.validationFailed = validationFailed;
    }
}
