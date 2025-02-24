package io.libP2P.core.components;

/**
 * A switchable entity is something involved in mechanic of <b>master-switch</b> in LibP2P.
 * The Only essential aspect in a switchable implementation is any networked data processing must stop/start on invocation.
 * Exact behavior is dependent on an entity type.
 */
public interface Switchable {
    /**
     * Restore all previously stopped data-related processes.
     */
    void start();

    /**
     * Interrupts data processing. Leaves a way to restore it after a while.
     */
    void stop();
}
