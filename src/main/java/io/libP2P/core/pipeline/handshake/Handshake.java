package io.libP2P.core.pipeline.handshake;

import io.libP2P.core.multiformats.stream.StreamOutputable;
import io.libP2P.core.multiformats.stream.StreamP2P;
import io.libP2P.core.pipeline.UpgradeHandler;

/**
 * The interface is intended to generalize way how LibP2P core manages a protocol handler while handshake.
 * </br>Handshake is an initial synchronous part of peer-peer communication serving only protocol needs(no user data transmission).
 * </br>This handshake occupies a stream waiting for all round trips of flow internally.
 * </br></br>
 * Handshake handler guarantees:
 * </br>- call {@link StreamOutputable#flush() } after each replica to remote
 * </br>- complete with {@link HandshakeException } when handshake cannot be successfully completed
 * </br>- obey a decision of core if to initiate of receive handshake
 */
public interface Handshake extends UpgradeHandler {

    /**
     * Initiate handshake.
     *
     * @param remote is a stream to the remote
     */
    void initHandshake(StreamP2P remote);

    /**
     * Waiting remote to initiate handshake.
     *
     * @param remote is a stream to the remote
     */
    void receiveHandshake(StreamP2P remote);
}
