package io.libP2P.stream.exception;

import io.libP2P.stream.StreamInputable;
import io.libP2P.stream.StreamOutputable;

/**
 * General case when something goes wrong while reading/writing to/from a {@link StreamInputable}/{@link StreamOutputable}.
 */
public class StreamException extends RuntimeException {
    public StreamException() {
        super();
    }

    public StreamException(String message) {
        super(message);
    }

    public StreamException(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamException(Throwable cause) {
        super(cause);
    }

    protected StreamException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
