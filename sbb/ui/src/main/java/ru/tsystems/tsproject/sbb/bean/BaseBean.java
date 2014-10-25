package ru.tsystems.tsproject.sbb.bean;

import org.apache.log4j.Logger;

/**
 *
 * Base class for other view beans. It contents processing error message and processing error flag
 * @author  Nikita Efremov
 * @since   1.0
 */
public abstract class BaseBean {
    private static final Logger log = Logger.getLogger(BaseBean.class);

    /**
     * Text of error, which occurred during processing on service layer
     */
    private String processingErrorMessage = "";

    /**
     * Flag of having error during processing on service layer
     */
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
