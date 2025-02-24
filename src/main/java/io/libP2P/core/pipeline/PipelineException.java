package io.libP2P.core.pipeline;

/**
 * General case when something goes wrong while assembling pipes {@link Pipe} into a pipeline.
 */
public class PipelineException extends RuntimeException {
    public PipelineException() {
        super();
    }

    public PipelineException(String message) {
        super(message);
    }

    public PipelineException(String message, Throwable cause) {
        super(message, cause);
    }

    public PipelineException(Throwable cause) {
        super(cause);
    }

    protected PipelineException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
