package io.libP2P.stream.exception;

/**
 * Case when pre/postprocessing failed due to data incompliance.
 */
public class StreamExceptionBadData extends StreamExceptionProcessingFailed {
    public StreamExceptionBadData() {
        super();
    }

    public StreamExceptionBadData(String message) {
        super(message);
    }

    public StreamExceptionBadData(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamExceptionBadData(Throwable cause) {
        super(cause);
    }

    protected StreamExceptionBadData(String message, Throwable cause,
                                     boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
