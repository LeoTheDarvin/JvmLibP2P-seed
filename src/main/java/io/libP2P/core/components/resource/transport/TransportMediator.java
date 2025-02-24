package io.libP2P.core.components.resource.transport;

import io.libP2P.core.components.Mediator;
import io.libP2P.core.components.resource.transport.TransportResource.TransportHandleable;
import java.util.function.Consumer;
import io.libP2P.core.components.resource.transport.TransportResource.TransportCreatable;
import io.libP2P.core.components.resource.multiaddress.MultiaddressMediator.MultiaddressMatcher;

public interface TransportMediator extends Mediator {
    Demand<TransportHandleable> demandTransport(TransportMatcher demand);
    void serveTransport(Consumer<TransportHandleable> handler);

    void emmitTransport(TransportCreatable transport);
    void provideTransport(OrderHandler handler);

    interface TransportMatcher {
        MultiaddressMatcher getOwn();
        MultiaddressMatcher getOther();
    }

    interface OrderHandler extends TransportMatcher {
        void handle(TransportMatcher demand, Order<TransportCreatable> order);
    }
}
