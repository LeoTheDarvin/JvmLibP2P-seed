package io.libP2P.core.components.resource.multiaddress;

import io.libP2P.core.components.Mediator.Resource;
import io.libP2P.core.multiformats.Multiaddress;
import io.libP2P.core.components.Auditor.Auditable;
import io.libP2P.core.components.Mediator.TrackableResource;
import io.libP2P.core.components.Auditor.Auditable.Reportable;

public interface MultiaddressResource extends Multiaddress, Resource {
    interface MultiaddressUsable extends Reportable, MultiaddressResource {
        void informUse();
        void markExpired();
    }

    interface MultiaddressCreatable extends MultiaddressResource {
        PeerInfo getSource();
    }

    interface MultiaddressAuditable extends Auditable, MultiaddressCreatable, TrackableResource { }
}
