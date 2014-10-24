package ru.tsystems.tsproject.sbb;

public class ValidationBean {

    private String validationMessage = "";
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
