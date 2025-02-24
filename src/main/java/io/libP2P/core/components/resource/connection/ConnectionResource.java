package io.libP2P.core.components.resource.connection;

import io.libP2P.core.components.Auditor.Auditable;
import io.libP2P.core.components.Auditor.Auditable.Reportable;
import io.libP2P.core.components.Auditor.Trackable;
import io.libP2P.core.components.Mediator.Resource;
import io.libP2P.core.components.resource.stream.StreamResource.StreamCreatable;
import io.libP2P.core.components.resource.stream.StreamResource.StreamUsable;
import io.libP2P.core.multiformats.Protocol;
import java.util.function.Consumer;

public interface ConnectionResource extends Resource {
    PeerInfo getInfo();

    interface ConnectionUsable extends Reportable, ConnectionResource {
        StreamUsable dialOut(Protocol protocol);
        void onDialIn(Protocol protocol, Consumer<StreamUsable> dialHandler);
    }

    interface ConnectionCreatable extends Trackable, ConnectionResource {
        StreamCreatable dialOut(Protocol protocol);
        void onDialIn(Protocol protocol, Consumer<StreamCreatable> dialHandler);
    }

    interface ConnectionAuditable extends Auditable, Trackable, ConnectionResource { }
}
