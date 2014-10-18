package ru.tsystems.tsproject.sbb.bean;

import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 *
 * Abstract bean class which contents validation and processing error messages and flags
 * @author  Nikita Efremov
 * @since   1.0
 */
public abstract class BaseBean {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Logger log = Logger.getLogger(BaseBean.class);

    private String validationMessage;
    private String processingErrorMessage;
    private boolean validationFailed;
    private boolean processingFailed;

    public BaseBean() {
        validationMessage = "";
        processingErrorMessage = "";
        validationFailed = Boolean.FALSE;
        processingFailed = Boolean.FALSE;
    }

    /**
     * Method makes validation of bean, and if validation failed it sets validationFailed flag to false
     * and fills validationMessage
     */
    public void validate() {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate((Object)this);

        StringBuilder errorMessageBuilder = new StringBuilder();

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            errorMessageBuilder.append(" - ").append(constraintViolation.getMessage()).append("\r\n");
        }
        validationMessage = errorMessageBuilder.toString();
        if (validationMessage.isEmpty()) {
            validationFailed = Boolean.FALSE;
        } else {
            validationFailed = Boolean.TRUE;
        }
    }

    /**
     * Method makes validation of specified property of bean, and if validation failed it sets validationFailed flag to false
     * and fills validationMessage
     */
    public void validate(String propertyName) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty((Object)this, propertyName);

        StringBuilder errorMessageBuilder = new StringBuilder();

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            errorMessageBuilder.append(" - ").append(constraintViolation.getMessage()).append("\r\n");
        }
        validationMessage += errorMessageBuilder.toString();
        if (validationMessage.isEmpty()) {
            validationFailed = Boolean.FALSE;
        } else {
            validationFailed = Boolean.TRUE;
        }
    }

    public boolean isValidationFailed() {
        return validationFailed;
    }

    public void setValidationFailed(boolean validationFailed) {
        this.validationFailed= validationFailed;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public String getProcessingErrorMessage() {
        return processingErrorMessage;
    }

    public void setProcessingErrorMessage(String processingErrorMessage) {
        this.processingErrorMessage = processingErrorMessage;
        setProcessingFailed(Boolean.TRUE);
    }

    public boolean isProcessingFailed() {
        return processingFailed;
    }

    public void setProcessingFailed(boolean processingFailed) {
        this.processingFailed = processingFailed;
    }
}
