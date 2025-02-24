package io.libP2P.core.pipeline;

import io.libP2P.core.components.Auditor;
import io.libP2P.core.multiformats.stream.StreamP2P;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Pipe is an elementary unit of a pipeline.
 * </br>Pipe extensions are intended to drive protocol handlers {@link UpgradeHandler} enhancing it for compatibility with components layer {@link io.libP2P.core.components}.
 * </br>Here are some of the possible responsibilities for pipes:
 * </br>- buffering
 * </br>- correct asynchronous running
 * </br>- data prepull(to reduce latency)
 * </br>- exception handling {@link Auditor.Auditable#onViolation(Consumer)}
 * </br>- transmission tracking {@link Auditor.Trackable}
 * </br>- applying default NOOP implementations
 */
public abstract class Pipe {
    public abstract Set<UpgradeGoals> getPipelineGoalsServed();

    public abstract class Ascend {
        private final Pipe thisPipe = Pipe.this;
        private Descend attachment;
        
        abstract protected StreamP2P getStream();
    }

    public abstract class Descend {
        protected abstract void tryAttach(StreamP2P downstreamAscend, Set<UpgradeGoals> downstreamGoalsServed);

        public final void attach(Ascend ascend) {
            if (ascend.attachment != null)
                throw new PipelineException("Cannot attach something to ascend. Already attached");
            if (ascend.thisPipe == Pipe.this)
                throw new PipelineException("Pipe cannot be attached to self");

            tryAttach(ascend.getStream(), ascend.thisPipe.getPipelineGoalsServed());
            ascend.attachment = this;
        }
    }
}
