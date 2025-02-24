package io.libP2P.core.components.accessor;

import io.libP2P.core.components.UpgradeProtocolHandlerFactory;
import io.libP2P.core.components.resource.connection.ConnectionMediator;
import io.libP2P.core.components.resource.multiaddress.MultiaddressMediator;
import io.libP2P.core.components.resource.stream.StreamMediator;
import io.libP2P.core.components.resource.transport.TransportMediator;
import io.libP2P.core.multiformats.Multiaddress;
import io.libP2P.core.multiformats.Protocol;
import io.libP2P.core.multiformats.ProtocolAddressable;
import io.libP2P.core.pipeline.UpgradeHandler;
import io.libP2P.core.pipeline.handshake.Handshake;
import io.libP2P.core.pipeline.transmission.Transmission;
import java.util.Set;

/**
 * Mediator manages access of an accessor on initialization {@link Accessor#initialize(AccessibleMediator)}.
 * </br></br>
 * All protocol handler-related methods declared hereby are synchronous and non-blocking since operating with immutable state.
 */
public interface AccessibleMediator
        extends MultiaddressMediator, TransportMediator, ConnectionMediator, StreamMediator {

    /**
     * Creates an upgrade handler with appropriate {@link UpgradeProtocolHandlerFactory}.
     * Handler might implement a handshake {@link Handshake} and one transmission {@link Transmission} according to {@link UpgradeHandler}.
     * Consider default implementations when a handshake or transmission are not defined for the protocol.
     *
     * @param protocol wanted protocol.
     * @throws ... when wanted protocol is out of {@link #getSupportedUpgradeProtocols()} set.
     * @return handler.
     */
    UpgradeHandler createUpgradeHandler(Protocol protocol);

    /**
     * @return set of protocols supported.
     */
    Set<Protocol> getSupportedUpgradeProtocols();

    /**
     * @return subset of {@link #getSupportedUpgradeProtocols()} for protocols compatible with {@link Multiaddress}.
     */
    Set<ProtocolAddressable> getSupportedUpgradeProtocolsAddressable();
}
