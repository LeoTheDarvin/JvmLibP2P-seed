package io.libP2P.core.components;

import io.libP2P.core.components.accessor.AccessibleMediator;
import io.libP2P.core.multiformats.Protocol;
import io.libP2P.core.pipeline.UpgradeHandler;
import io.libP2P.core.pipeline.handshake.Handshake;
import io.libP2P.core.pipeline.transmission.Transmission;

/**
 * Factory component creates a handler for one defined protocol.
 * </br></br>
 * </br>The factory's methods are synchronous and non-blocking for compatibility with the aggregator {@link AccessibleMediator}.
 */
public interface UpgradeProtocolHandlerFactory extends Component {
    /**
     * @return the protocol supported by handler created.
     */
    Protocol getSupported();

    /**
     * Creates the protocol handler.
     * Handler might implement a handshake {@link Handshake} and one transmission {@link Transmission}.
     *
     * @return protocol handler.
     */
    UpgradeHandler createHandler();
}
