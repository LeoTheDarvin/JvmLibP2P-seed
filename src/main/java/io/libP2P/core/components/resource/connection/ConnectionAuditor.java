package io.libP2P.core.components.resource.connection;

import io.libP2P.core.components.Auditor;
import io.libP2P.core.components.resource.connection.ConnectionResource.ConnectionAuditable;
import io.libP2P.core.multiformats.PeerId;

public interface ConnectionAuditor extends Auditor {
    void onConnectionDemanded(DemandAuditable demand, PeerId demanded);
    void onConnectionCreated(ConnectionAuditable connection);
}