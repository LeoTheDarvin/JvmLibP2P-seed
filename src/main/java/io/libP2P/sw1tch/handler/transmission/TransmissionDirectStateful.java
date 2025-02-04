package io.libP2P.sw1tch.handler.transmission;

import io.libP2P.stream.StreamInputable;
import io.libP2P.stream.StreamOutputable;

/**
 * The transmission is nothing but a synthesis of {@link TransmissionDirect} and {@link TransmissionStateful}.
 * The state should be treated and released as early as possible
 *
 * @see TransmissionDirect
 * @see TransmissionStateful
 * @param <S> is a state type
 */
public interface TransmissionDirectStateful<S> extends TransmissionStateful<S> {
    /**
     * @see TransmissionDirect#downstreamChunk(StreamInputable, StreamOutputable)
     * @see StateMediator#release()
     */
    void downstreamChunk(StreamInputable ascend, StateMediator<S> mediator, StreamOutputable descend);

    /**
     * @see TransmissionDirect#upstreamChunk(StreamOutputable, StreamInputable)
     * @see StateMediator#release()
     */
    void upstreamChunk(StreamOutputable ascend, StateMediator<S> mediator, StreamInputable descend);
}
