package io.libP2P.core.pipeline;

import io.libP2P.core.pipeline.handshake.Handshake;
import io.libP2P.core.pipeline.transmission.Transmission;

/**
 * Upgrade handler is a marker interface for {@link Transmission} and {@link Handshake}.
 * Handler interface is intended to segregate protocol implementations from auxilary logic implemented by pipes {@link Pipe}:
 */
public interface UpgradeHandler { }
