package io.libP2P.core.components.resource.transport;

import io.libP2P.core.components.Auditor.Auditable;
import io.libP2P.core.components.Auditor.Auditable.Reportable;
import io.libP2P.core.components.Auditor.Trackable;
import io.libP2P.core.components.Mediator.Resource;
import io.libP2P.core.multiformats.Multiaddress;
import io.libP2P.core.multiformats.stream.StreamP2P;
import io.libP2P.core.pipeline.Pipe;

public interface TransportResource extends Resource {

    interface TransportUsable extends TransportResource {
        Pipe.Ascend getAscend();
    }

    interface TransportHandleable extends TransportUsable, Reportable { }

    interface TransportCreatable extends TransportUsable, Trackable { }

    interface TransportAuditable extends Trackable, Auditable, TransportResource {
        Multiaddress getOwnAddress();
        Multiaddress getOthersAddress();
    }
}
