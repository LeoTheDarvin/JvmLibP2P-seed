package io.libP2P.core.components.accessor;

import io.libP2P.core.components.Component;
import io.libP2P.core.components.Switchable;


public interface Accessor extends Switchable, Component {
    void initialize(AccessibleMediator context);
}
