package io.libP2P.sw1tch.handler.transmission;

import io.libP2P.stream.StreamInputable;
import io.libP2P.stream.StreamOutputable;

/**
 * The transmission has one ascend(where stream is upgraded) and one descend(where stream in downgraded).
 *
 * @see StreamOutputable
 * @see StreamInputable
 * @see Transmission Transmission to learn the entire contract of a direct transmission
 */
public interface TransmissionDirect extends Transmission {
    /**
     * The method reads a chunk of upgraded data from ascend, downgrades it and writes to descend.
     */
    void downstreamChunk(StreamInputable ascend, StreamOutputable descend);

    /**
     * The method reads a chunk of downgraded data from descend, upgrades it and writes to ascend.
     */
    void upstreamChunk(StreamOutputable ascend, StreamInputable descend);
}
