package io.libP2P.core.pipeline.transmission;

/**
 * The interface represents transmission with a <b>mutable</b> state.
 * </br>You need this if you want LibP2P core to sequentialize state updates for consecutive chunks.
 * </br>You don't need this:
 * </br>- for preconfigured implementations.
 * </br>- if your implementation tolerant to concurrency between transmitters handling sequential chunks.
 * </br></br>
 * This transmission guarantees:
 * </br>- release state as soon as possible
 * </br>- design state in a way enabling correct asynchronous access for one upstreaming and one downstreaming transmitter.
 * </br></br>
 * This transmission relies on:
 * </br>- only one upstreaming and one downstreaming transmitter could access the state simultaneously
 * </br>- state is securely stored externally between transmission calls
 * </br>- every access to state is sequentialized in order of data read externally
 *
 * @see Transmission to learn the entire contract of a stateful transmission
 * @param <S> is a state type
 */
public interface TransmissionStateful<S> extends Transmission {
    /**
     * @return a state
     */
    S initState();

    /**
     * Is a synchronization point of transmitters over state.
     *
     * @param <S> is a state type
     */
    interface StateMediator<S> {
        /**
         * Notify core state is up to date for current chunk transmission.
         * Implementation should forbid all further access to the state since.
         */
        void release();

        /**
         * @return the state relevant to the previous chunk progress. And relevant to the opposite streaming direction progress.
         */
        S getState();
    }
}
