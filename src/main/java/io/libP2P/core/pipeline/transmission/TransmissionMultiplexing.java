package io.libP2P.core.pipeline.transmission;

import io.libP2P.core.multiformats.stream.StreamInputable;
import io.libP2P.core.multiformats.stream.StreamOutputable;
import java.util.Set;

/**
 * The transmission has multiple ascends(where stream is upgraded) and one descend(where stream in downgraded).
 *
 * @see StreamOutputable
 * @see StreamInputable
 * @see Transmission
 */
public interface TransmissionMultiplexing extends Transmission {

    /**
     * The method reads some ready upgraded data from ascend, downgrades it and writes to descend a chunk.
     * How much data to read and how to balance read between streams of the ascend stays up to implementation.
     */
    void downstreamChunk(MultistreamInputableMediator ascend, StreamOutputable descend);

    /**
     * The method reads a chunk of downgraded data from descend and shares it over streams of the ascend.
     * Implementation stays allowed to create or close streams of ascend.
     */
    void upstreamChunk(MultistreamOutputableMediator ascend, StreamInputable descend);

    /**
     * Is a synchronization point for transmitters over a set of streams ascending from a multiplexer.
     * All streams with exhausted source are up to be removed by core.
     */
    interface MultistreamMediator {
        /**
         * @return immutable set with identifiers of active streams
         */
        Set<String> getIds();

        /**
         * Notify core multistream is handled for current chunk.
         * Implementation should forbid all further access to streams and entire set since.
         */
        void release();
    }

    interface MultistreamInputableMediator extends MultistreamMediator {
        /**
         * Gets stream when id is one of {@link #getIds()}
         * @param id to get a stream
         *
         * @return a stream by id or null
         */
        StreamInputable find(String id);
    }

    interface MultistreamOutputableMediator extends MultistreamMediator {
        /**
         * Gets streams when id is one of {@link #getIds()} or creates new.
         * @param id to get a stream or create new
         *
         * @return a stream by id
         */
        StreamOutputable getOrCreate(String id);

        /**
         * Closes source of appropriate direction(upstream/downstream).
         * Use this instead of stream's close methods as they just release a stream within a transmission according to {@link Transmission}.
         * Core will drop stream from multistream for further transmitters.
         * Appropriate upstream will stay soft-closed for user till prefetched data exhaustion. Then close.
         *
         * @param id to close drain/source softly.
         * @return true when id was one of {@link #getIds()}
         */
        boolean close(String id);
    }
}
