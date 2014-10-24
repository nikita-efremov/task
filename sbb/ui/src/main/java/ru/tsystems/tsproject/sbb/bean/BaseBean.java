package ru.tsystems.tsproject.sbb.bean;

import org.apache.log4j.Logger;

/**
 *
 * Abstract bean class which contents validation and processing error messages and flags
 * @author  Nikita Efremov
 * @since   1.0
 */
public abstract class BaseBean {
    private static final Logger log = Logger.getLogger(BaseBean.class);

    private String processingErrorMessage = "";
    private boolean processingFailed = Boolean.FALSE;

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
