package ru.tsystems.tsproject.sbb.controller.controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller, which get all occurred exceptions and forwards ro exception page
 * @author  Nikita Efremov
 * @since   2.0
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger log = Logger.getLogger(ExceptionHandlerController.class);
    private static final String ERROR_TEXT = "errorText";

    /**
     * If the exception is annotated with @ResponseStatus rethrow it and let the framework handle it.
     * Otherwise setup and send the user to a default error-view.
     * @param exception
     *        Exception which have occurred
     * @return ModelAndView
     *         Exception view page
     * @throws Exception
     *         Throws then exception is annotated with @ResponseStatus
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception exception) throws Exception {
        log.error("Exception occurred: " + exception + " " + ExceptionUtils.getStackTrace(exception));
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
            throw exception;
        }
        return new ModelAndView("/error500");
    }
}
