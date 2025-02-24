package io.libP2P.core.components.resource.stream;

import io.libP2P.core.components.Auditor;
import io.libP2P.core.components.resource.stream.StreamMediator.StreamMatcher;
import io.libP2P.core.components.resource.stream.StreamResource.StreamAuditable;

public interface StreamAuditor extends Auditor {
    void onStreamDemanded(DemandAuditable demand, StreamMatcher demanded);
    void onStreamCreated(StreamAuditable stream);
}