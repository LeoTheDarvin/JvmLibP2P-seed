package io.libP2P.core.components.resource.transport;

import io.libP2P.core.components.Auditor;
import io.libP2P.core.components.resource.transport.TransportMediator.TransportMatcher;
import io.libP2P.core.components.resource.transport.TransportResource.TransportAuditable;

public interface TransportAuditor extends Auditor {
    void onTransportDemanded(DemandAuditable demand, TransportMatcher demanded);
    void onTransportCreated(TransportAuditable transport);
}