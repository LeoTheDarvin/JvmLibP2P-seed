package io.libP2P.stream;

/**
 * Let call concurrent actors <b>writers</b>.
 * The stream is an access point to a binary data <b>drain</b> with fixed throughput.
 * Overflown drain block writer trying to write too much.
 * The stream could postpone written data draining for buffering of other postprocessing.
 *
 * @see StreamInputable as a complimentary input approach.
 */
public interface StreamOutputable extends Stream {
    /**
     * Blocking write with timeout.
     *
     * @param data to write. The only reason for partial writing is timeout.
     * @param timeoutMills time limit for a writer to stay blocked. Non-positive timeout makes write time unrestricted.
     * @return amount of data actually written. Immediate zero when the drain is closed.
     */
    int write(byte[] data, int timeoutMills);

    /**
     * Non-blocking write.
     *
     * @param data to write.
     * @return amount of data actually written. Always zero when the drain is closed.
     */
    int writeNow(byte[] data);

    /**
     * Triggers all written data draining. Blocks current writer until.
     */
    void flush();

    /**
     * Closes the drain. Leaves all buffered data unprocessed.
     * Forbids all further writes.
     */
    void closeDrain();

    /**
     * @return whether the drain is open
     */
    boolean isDrainOpen();
}
