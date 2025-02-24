package io.libP2P.core.multiformats.stream;

/**
 * Represents a general mediator for protocol implementations interoperability.
 * Streams must:
 * - operate in a concurrent environment but provide serial synchronized access FIFO
 * - be compliant with <a href="https://docs.libp2p.io/concepts/fundamentals/protocols/">documentation</a>
 */
public interface StreamP2P extends StreamOutputable, StreamInputable { }
