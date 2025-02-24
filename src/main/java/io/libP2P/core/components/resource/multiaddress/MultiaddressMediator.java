package io.libP2P.core.components.resource.multiaddress;

import io.libP2P.core.components.Mediator;
import java.util.function.Consumer;
import io.libP2P.core.multiformats.Multiaddress;
import io.libP2P.core.components.resource.multiaddress.MultiaddressResource.MultiaddressCreatable;
import io.libP2P.core.components.resource.multiaddress.MultiaddressResource.MultiaddressUsable;

public interface MultiaddressMediator extends Mediator {
    Demand<MultiaddressUsable> demandMultiaddresses(MultiaddressMatcher matcher);
    void serveMultiaddresses(Consumer<MultiaddressUsable> handler);

    void emmitMultiaddresses(MultiaddressCreatable multiaddresses);
    void provideMultiaddresses(OrderHandler handler);

    interface MultiaddressMatcher {
        boolean isMatches(Multiaddress multiaddress);
        boolean isCompatibleWith(MultiaddressMatcher another);
        String toString();
    }

    interface OrderHandler extends MultiaddressMatcher {
        void handle(MultiaddressMatcher demand, Order<MultiaddressCreatable> order);
    }
}
