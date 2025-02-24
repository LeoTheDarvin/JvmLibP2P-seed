package io.libP2P.core.components.resource.stream;

import io.libP2P.core.components.Mediator;
import io.libP2P.core.multiformats.PeerId;
import io.libP2P.core.multiformats.Protocol;
import io.libP2P.core.components.resource.stream.StreamResource.StreamUsable;
import io.libP2P.core.components.resource.stream.StreamResource.StreamCreatable;
import java.util.function.Consumer;

public interface StreamMediator extends Mediator {
    Demand<StreamUsable> demandStream(StreamMatcher demand);
    void serveStream(Protocol protocol, Consumer<StreamUsable> handler);

    void emmitStream(StreamCreatable stream);
    void provideStream(OrderHandler handler);

    interface StreamMatcher {
        PeerId getPeerId(); //opt
        Protocol getProtocol();  //opt
    }

    interface OrderHandler extends StreamMatcher {
        void handle(StreamMatcher demand, Order<StreamCreatable> order);
    }
}
