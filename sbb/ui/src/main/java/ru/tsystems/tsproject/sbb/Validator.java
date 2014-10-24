package ru.tsystems.tsproject.sbb;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Validator {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static ValidationBean validate(Object objectForValidation) {
        Boolean validationFailed;
        javax.validation.Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(objectForValidation);

        StringBuilder errorMessageBuilder = new StringBuilder();

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            errorMessageBuilder.append(" - ").append(constraintViolation.getMessage()).append("\r\n");
        }
        String validationMessage = errorMessageBuilder.toString();
        if (validationMessage.isEmpty()) {
            validationFailed = Boolean.FALSE;
        } else {
            validationFailed = Boolean.TRUE;
        }
        ValidationBean validationBean = new ValidationBean();
        validationBean.setValidationFailed(validationFailed);
        validationBean.setValidationMessage(validationMessage);
        return validationBean;
    }

    /**
     * Method makes validation of specified property of bean, and if validation failed it sets validationFailed flag to false
     * and fills validationMessage
     */
    public static ValidationBean validate(Object objectForValidation, String ... propertyNames) {
        Boolean validationFailed;
        String validationMessage = "";
        javax.validation.Validator validator = validatorFactory.getValidator();
        for (String propertyName: propertyNames) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(objectForValidation, propertyName);
            StringBuilder errorMessageBuilder = new StringBuilder();
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                errorMessageBuilder.append(" - ").append(constraintViolation.getMessage()).append("\r\n");
            }
            validationMessage += errorMessageBuilder.toString();
        }

        if (validationMessage.isEmpty()) {
            validationFailed = Boolean.FALSE;
        } else {
            validationFailed = Boolean.TRUE;
        }

        ValidationBean validationBean = new ValidationBean();
        validationBean.setValidationFailed(validationFailed);
        validationBean.setValidationMessage(validationMessage);
        return validationBean;
    }
}
