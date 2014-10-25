package ru.tsystems.tsproject.sbb;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Class which makes validation of view beans, which comes from view layers
 * @author  Nikita Efremov
 * @since   1.0
 */
public class Validator {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * Method makes validation for object, specified in param.
     * During validation it sets validation message and validation flag in output parameter
     * @param objectForValidation
     *        Object, which must be validated
     * @return ValidationBean
     *         Object, which contains validation flag and validation message
     */
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
     * Method makes validation for specified properties of object, specified in param.
     * During validation it sets validation message and validation flag in output parameter
     * @param objectForValidation
     *        Object, which must be validated
     * @param propertyNames
     *        Array of objects properties, which must be validated
     * @return ValidationBean
     *         Object, which contains validation flag and validation message
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
