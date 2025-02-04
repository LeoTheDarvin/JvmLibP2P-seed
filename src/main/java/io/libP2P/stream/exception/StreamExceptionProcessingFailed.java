package io.libP2P.stream.exception;

/**
 * Case when preprocessing data for read or postprocessing of written data has failed.
 */
public class StreamExceptionProcessingFailed extends RuntimeException {
    public StreamExceptionProcessingFailed() {
        super();
    }

    public StreamExceptionProcessingFailed(String message) {
        super(message);
    }

    public StreamExceptionProcessingFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamExceptionProcessingFailed(Throwable cause) {
        super(cause);
    }

    protected StreamExceptionProcessingFailed(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
