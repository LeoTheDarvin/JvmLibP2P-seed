package io.libP2P.core.components.resource.multiaddress;

import io.libP2P.core.components.Auditor;
import io.libP2P.core.components.resource.multiaddress.MultiaddressMediator.MultiaddressMatcher;
import io.libP2P.core.components.resource.multiaddress.MultiaddressResource.MultiaddressAuditable;

public interface MultiaddressAuditor extends Auditor {
    void onMultiaddressDemanded(DemandAuditable demand, MultiaddressMatcher demanded);
    void onMultiaddressCreated(MultiaddressAuditable multiaddress);
}
