package io.libP2P.core.pipeline.transmission;

import io.libP2P.core.multiformats.stream.StreamInputable;
import io.libP2P.core.multiformats.stream.StreamOutputable;
import io.libP2P.core.pipeline.transmission.TransmissionMultiplexing.MultistreamInputableMediator;
import io.libP2P.core.pipeline.transmission.TransmissionMultiplexing.MultistreamOutputableMediator;

/**
 * The transmission is nothing but a synthesis of {@link TransmissionMultiplexing} and {@link TransmissionStateful}.
 * The state should be treated and released as early as possible.
 *
 * @see TransmissionMultiplexing
 * @see TransmissionStateful
 * @param <S> is a state type
 */
public interface TransmissionMultiplexingStateful<S> extends TransmissionStateful<S> {
    /**
     * @see TransmissionMultiplexing#downstreamChunk(MultistreamInputableMediator, StreamOutputable)
     * @see StateMediator#release()
     */
    void downstreamChunk(MultistreamInputableMediator ascend, StateMediator<S> mediator, StreamOutputable descend);

    /**
     * @see TransmissionMultiplexing#upstreamChunk(MultistreamOutputableMediator, StreamInputable)
     * @see StateMediator#release()
     */
    void upstreamChunk(MultistreamOutputableMediator ascend, StateMediator<S> mediator, StreamInputable descend);
}
