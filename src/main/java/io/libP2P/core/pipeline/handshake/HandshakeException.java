package io.libP2P.core.pipeline.handshake;

/**
 * General case when something goes wrong while handshake.
 */
public class HandshakeException extends RuntimeException {

    public HandshakeException() {
        super();
    }

    public HandshakeException(String message) {
        super(message);
    }

    public HandshakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandshakeException(Throwable cause) {
        super(cause);
    }

    protected HandshakeException(String message, Throwable cause,
                                     boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace); }
}
