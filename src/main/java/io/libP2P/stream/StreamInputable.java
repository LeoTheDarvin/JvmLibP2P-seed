package io.libP2P.stream;

/**
 * Let call concurrent actors <b>readers</b>.
 * The stream is an access point to a binary data <b>source</b> with fixed throughput.
 * Overflown source block reader trying to read too much.
 * The stream suggests data prefetch for buffering or some other data preprocessing dependently on implementation.
 *
 * @see StreamOutputable as a complimentary input approach.
 */
public interface StreamInputable extends Stream {
    /**
     * Blocking read with timeout.
     *
     * @param limit is desired amount of data to read. The only reason for partial reading is timeout.
     * @param timeoutMills limit for a writer to stay blocked. Non-positive timeout makes read time unrestricted.
     * @return data read. Immediate empty when the source is closed and there's no data prefetched.
     */
    byte[] read(int limit, int timeoutMills);

    /**
     * Non-blocking read.
     *
     * @param limit is desired amount of data to read.
     * @return data read. Empty when the source is closed and there's no data prefetched.
     */
    byte[] readReady(int limit);

    /**
     * Closes the source. Leave read-methods available for reading buffered data.
     */
    void closeSourceSoft();

    /**
     * Closes the source. Drops unprocessed data.
     */
    void closeSourceHard();

    /**
     * @return whether the source is open
     */
    boolean isSourceOpen();
}
