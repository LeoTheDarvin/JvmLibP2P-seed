package io.libP2P.core.pipeline.transmission;

import io.libP2P.core.multiformats.stream.StreamP2P;
import io.libP2P.core.multiformats.stream.exception.StreamException;
import io.libP2P.core.multiformats.stream.exception.StreamExceptionProcessingFailed;
import io.libP2P.core.pipeline.UpgradeHandler;

/**
 * Transmission is a basic marker interface for all implementations capable to transmit {@link StreamP2P} upgrading upstream and downgrading downstream.
 * Extensions of the interface are intended to contract a way how LibP2P core manages a protocol handler in data transmission stage.
 * </br></br>
 * Let call streams before&after upgrade <b>descend</b>&<b>ascend</b>.
 * </br>Let call descend, ascend and state <b>resources</b>
 * </br>Let call data processing for upstreaming/downstreaming <b>transmission</b>.
 * </br>Let call all routines/workers/threads running data processing <b>transmitters</b>.
 * </br></br>
 * Several transmitters could run the same transmission in parallel.
 * </br></br>
 * Ascend/descend handled by transmission is a synthetic stream with a virtual source/drain.
 * </br>Any data read/written sources/drains directly with no preprocessing/postprocessing.
 * </br>Closing source/drain you just inform core that the current chunk is read/written completely.
 * </br></br>
 * Transmission handler guarantees:
 * </br>- make transmission handling minimal reasonable amount of data(call it <b>chunk</b>) per call.
 * </br>- make transmission-methods signature allowing synchronization of concurrent transmitters on parameters.
 * </br>- release/close all resources as soon as possible.
 * </br>- throw {@link StreamExceptionProcessingFailed } and subclassed for appropriate cases.
 * </br></br>
 * Transmission handler relies on:
 * </br>- core to handle all {@link StreamException} from current transmission
 * </br>- synthetic ascend&descend won't throw a {@link StreamException}
 */
public interface Transmission extends UpgradeHandler { }
