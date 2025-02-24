package io.libP2P.core.components.resource.stream;

import io.libP2P.core.multiformats.stream.StreamP2P;
import io.libP2P.core.multiformats.Protocol;
import io.libP2P.core.components.Auditor.Auditable;
import io.libP2P.core.components.Auditor.Trackable;
import io.libP2P.core.components.Mediator.Resource;
import io.libP2P.core.components.Auditor.Auditable.Reportable;
import io.libP2P.core.components.resource.connection.ConnectionResource;

public interface StreamResource extends Resource {
    Protocol getProtocol();
    ConnectionResource getConnection();

    interface StreamCreatable extends StreamP2P, StreamResource { }

    interface StreamUsable extends StreamP2P, Reportable, StreamResource { }

    interface StreamAuditable extends Auditable, Trackable, StreamResource { }
}
