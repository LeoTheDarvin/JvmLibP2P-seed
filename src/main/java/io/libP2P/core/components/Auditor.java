package io.libP2P.core.components;

import io.libP2P.core.components.accessor.AccessibleMediator;
import io.libP2P.core.components.accessor.Accessor;
import io.libP2P.core.multiformats.PeerId;
import io.libP2P.core.multiformats.stream.StreamP2P;
import java.util.function.Consumer;

/**
 * Auditor is a component having wide access for supervision of resource lifecycle and usage.
 * Auditors could be used to gather information and restrict operations over resources.
 * Only utilization of resource's primary value stays out of auditor's scope.
 * */
public interface Auditor extends Component {

    /**
     * Representation of a {@link Mediator.Demand} for auditor.
     */
    interface DemandAuditable {
        /**
         * Handle allowing to stop or start a harmful consumer.
         *
         * @return a reference to consumer.
         */
        Accessor getAccessor();

        /**
         * Terminates the demand with exception for consumer.
         * Closes the appropriate order for provided if it's started already.
         */
        void reject();
    }

    /**
     * Auditable is an essential contract for accessible {@link AccessibleMediator} resources {@link io.libP2P.core.components.resource}.
     * </br>Auditable resource defines:
     * </br>- initiator - a peer to blame when a resource violates something
     * </br>- creator - accessor introduced resource in. Might be vulnerable
     * </br>Auditable includes observable context for violations. Useful to gather info of primitive violations which handler can spot on own. Violations reveled creation-time are up to be tolerated by creator.
     */
    interface Auditable extends AutoCloseable {
        /**
         * @return PeerId of the resource resources. Could be own id. Or absent when resource is created within a not authenticated session.
         */
        PeerId getInitiator();

        /**
         * @return an auditable accessor handle with name and leverage to eliminate it from the system.
         */
        Accessor getCreator();

        /**
         * Listens for violations reported by handlers using the resource.
         *
         * @param registerViolation to register a violation.
         */
        void onViolation(Consumer<Violation> registerViolation);

        /**
         * Reportable contracts how user reports resource-related violations.
         */
        interface Reportable {
            void report(Violation violation);
        }

        /**
         * This is a general violation.
         */
        interface Violation {
            String getDescription();
            ViolationSeverity getSeverity();
        }

        /**
         * Standard for violations severity algorithmic scoring.
         */
        enum ViolationSeverity {
            /**
             * This violation makes the resource not usable. Protocol implementation inconsistency or unexpected loss of synchronization. Mustn't take much resources to handle.
             */
            STRANGE,

            /**
             * Harmful for whole local. Consider breaking out with initiator or stopping creator.
             */
            ABUSE,

            /**
             * Complex violation capable to affect other peers in network.
             */
            DANGER
        }
    }

    /**
     * Trackable resource is a resource handling data transmission inside.
     * Trackable interface allows observing and limiting throughput through the resource.
     * In terms of pipeline trackable is a valve with probe mounted aside whereas {@link StreamP2P} is a conjunction to another pipe.
     */
    interface Trackable {

        /**
         * Mounts tracker to the upstream direction.
         */
        void adviseUpstream(TrafficTracker tracker);

        /**
         * Mounts tracker to the downstream direction.
         */
        void adviceDownstream(TrafficTracker tracker);

        /**
         * Tracker is a mounting to the resource allowing to probe data flow and limit throughput.
         * </br></br>
         * The first tracked measurement comes with 0 bytes immediately after mounting to set a reference point on timeline.
         */
        interface TrafficTracker {
            /**
             * @return number of bytes to track another span. Zero or negative to stop tracking
             */
            int onSpanTransmitted(Gateway gateway, Measurement probe);
        }

        /**
         * Gateway allows limiting throughput of a transmitting resource.
         */
        interface Gateway {
            void limitThroughputBy(long bytesPerSec);
        }

        /**
         * Measurement is a point on timeline bond to transmitted data amount since the probe mounting.
         */
        class Measurement {
            public final long spanFinishedUtcMills;
            public final long finalByteInSession;

            public Measurement(long spanFinishedUtcMills, long bytesInSession) {
                this.spanFinishedUtcMills = spanFinishedUtcMills;
                this.finalByteInSession = bytesInSession;
            }
        }
    }
}
