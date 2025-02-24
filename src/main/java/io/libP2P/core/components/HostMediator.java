package io.libP2P.core.components;

import io.libP2P.core.components.accessor.AccessibleMediator;

/**
 * Host mediator provides root access to libP2P instance after creation.
 * </br></br>
 * Access to resources by this will have precedence over module-sourced accessors.
 */
public interface HostMediator extends AccessibleMediator, Switchable, AutoCloseable { }
