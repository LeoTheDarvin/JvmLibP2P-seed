package io.libP2P.core.components;

import io.libP2P.core.components.accessor.Accessor;

/**
 * Component is a fundamental building block in LibP2P's architecture.
 * Components have types and might have access to resources according to the role.
 * Components have unrestricted retention.
 *
 * @see Accessor
 * @see Auditor
 * @see UpgradeProtocolHandlerFactory
 */
public interface Component extends AutoCloseable {
    /**
     * @return name of the component to identify it within mediator.
     */
    String getName();
}
