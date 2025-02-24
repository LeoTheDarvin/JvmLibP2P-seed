package io.libP2P.core.components.resource.connection;

import io.libP2P.core.components.Mediator;
import io.libP2P.core.components.resource.connection.ConnectionResource.ConnectionCreatable;
import io.libP2P.core.components.resource.connection.ConnectionResource.ConnectionUsable;
import io.libP2P.core.multiformats.PeerId;
import java.util.function.Consumer;

public interface ConnectionMediator extends Mediator {
    Demand<ConnectionUsable> demandConnection(PeerId peerId);
    void serveConnection(Consumer<ConnectionUsable> handler);

    void emmitConnection(ConnectionCreatable connection);
    void provideConnection(OrderHandler handler);

    interface OrderHandler {
        void handle(PeerId peerId, Order<ConnectionCreatable> order);
    }
}
