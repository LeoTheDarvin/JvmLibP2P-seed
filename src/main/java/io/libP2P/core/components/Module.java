package io.libP2P.core.components;

/**
 * Module is a named bundle of components serving common goal
 * Components of a module starting transitively(all or none)
 * Name of module defines a namespace of underlying components
 */
public interface Module {
    /**
     * @return name of module
     */
    String getName();

    /**
     * @return components to embed into the system
     */
    Component[] getComponents();
}
