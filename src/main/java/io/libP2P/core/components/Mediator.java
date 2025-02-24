package io.libP2P.core.components;

import io.libP2P.core.components.accessor.Accessor;
import io.libP2P.core.multiformats.Key;
import io.libP2P.core.multiformats.PeerId;

/**
 * This is a root interface with highlighting common aspects of all mediator implementations.
 * Intro:</br>
 * The foremost goal of mediator is making a LibP2P implementation <a href="https://docs.libp2p.io/">modular</a>.
 * Modular design of implementation is aimed to support simple reconfiguration of the modular protocol stack.
 * As for other important benefits of modularity, it decreases a learning curve for development of one defined feature.
 * </br></br>
 * How the mediator preserves and equips the modularity of LibP2P:
 * </br>- defines resource types, enhances those uses, according to type. See {@link Resource}
 * </br>- defines types of components, enhances those use, according to responsibility. See {@link Component}
 * </br>- defines simple contract for module compatibility with entire system. See {@link Module}
 * </br>- defines a way to embed throughout functionality alike {@link Switchable}
 * </br>- aggregates creation of protocol upgrade <a href="https://docs.libp2p.io/concepts/fundamentals/protocols/#handler-functions">handlers</a>
 * </br>- synchronizes and prioritizes access of asynchronous components to resources and/or other components
 * </br>- provide early system consistency check
 * </br></br>
 * Mediator is/does:
 * </br>- care of resources-related problems: lifecycle, access, tracking
 * </br>- care how/if a component is running asynchronously
 * </br>- care about complex components with multiple responsibilities or when they're resources too.
 * </br></br>
 * Mediator is/does not:
 * </br>- a Dependency Injection container
 * </br>- care of resources creation
 * </br>- care how resources perform, according to the primary responsibility
 * </br></br>
 * Resource types has own rules of use. To mit this core defines access individually. See packages under {@link io.libP2P.core.components.resource}
 * </br></br>
 * Component types are:
 * </br>- accessors {@link Accessor} is a type allowed to use resources
 * </br>- auditors {@link Auditor} is a type for statistics gathering and access control. Can forbid but not use.
 * </br>- upgrade handler factories {@link UpgradeProtocolHandlerFactory} are interface for underlying level of protocol implementations.
 * </br></br>
 * Few terms in accessor-related vocabulary:
 * </br>- servers - handlers, passive(handles newly created resources)
 * </br>- consumers - handlers, active(demand certain resource)
 * </br>- providers - creators, passive(create resources on demand)
 * </br>- emitters - creators, active(create resources when ever they're ready)
 * </br> These are not component types but terms to describe how a mediator manages accessors internally.
 *
 * @throws ... when trying to access from a non active component.
 *
 */
public interface Mediator {

    /**
     * Root interface for resources managed by the mediator.
     */
    interface Resource {
        interface PeerInfo {
            PeerId getId();
            Key getPublicKey();
        }
    }

    /**
     * This resource has an attached observable context allowing a holder to track events of use.
     * The context is driven by the mediator.
     * Mediator is responsible for normalization of access: deduplication of events, silence when component is stopped of resource is expired.
     * </br></br>
     * (optional)Implement the interface by created(provided or emitted) resource to customize tracking logic.
     */
    interface TrackableResource extends Resource {
        /**
         * Tracks events when the resource comes in handy.
         *
         * @param doOnUse callback from mediator on the event.
         */
        void onUse(Runnable doOnUse);

        /**
         * Tracks events when the resource cannot be used for primary purpose anymore.
         *
         * @param doWhenExpired callback from mediator on the event.
         */
        void onExpire(Runnable doWhenExpired);
    }

    /**
     * This resource suggests continuous utilization and fixed period of idle retention.
     * </br></br>
     * Implement the interface by created(provided or emitted) resource to customize retention.
     */
    interface RetainableResource extends Resource {
        /**
         * @return latest usage system time {@link System#currentTimeMillis}.
         */
        long getLatestUseInstant();

        /**
         * @return idle retention period in milliseconds.
         */
        int getIdleRetentionPeriod();
    }

    /**
     * Demand is a way for consumer to access a resource.
     * </br>This is a general and abstract access point considering:
     * </br>- retained resources
     * </br>- decisions of auditors
     * </br>- provider selection
     * </br>- luckily emitted suitable resources
     * </br>- priority of the demand against others
     * </br>- if the consumer is running
     *
     * @param <T> is the resource type
     */
    interface Demand<T extends Resource> {
        /**
         * Takes another suitable resource.
         * Retained resources are getting available immediately in non-blocking way.
         *
         * @throws ... no suitable resources found retained, when all providers gave up {@link Order#giveUp()}.
         * @throws ... when an auditor rejected this demand {@link DemandAuditable#reject()}
         * @param timeout to await for another suitable resource.
         * @return resource of null on timeout.
         */
        T get(long timeout);

        /**
         * When the demand gets irrelevant
         */
        void close() throws Exception;
    }

    /**
     * Order is how a provider releases a demanded resource
     *
     * @param <T> is the resource type
     */
    interface Order<T> {
        /**
         * Releases a demanded resource.
         * Provider should correctly close an unneeded resource.
         * Order could be limited - check if it's open after each resource.
         *
         * @throws ... when trying to provide something by a closed order
         */
        void provide(T resource);

        /**
         * @return true if order is open still.
         */
        boolean isOpen();

        /**
         * Closes the order
         */
        void giveUp();
    }
}
