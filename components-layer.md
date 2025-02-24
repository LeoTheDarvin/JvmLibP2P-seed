### Accessors
###### Let divide them by access to resource type. Servers and consumers are handling. Providers and emitters are creating. Servers and providers are passive - work on demand. Emitters and consumers are active - work whenever they're ready.
- create and handler resources
- handlers access to resources: use, expose violations, initiator
- determine retention of a produced resource
- type-determined start/stop behavior
- provide other accessors
### Resources
| type\aspect  	         | traffic trackable | initiator 	            | access                                 | use criteria                                 | expiration criteria 	                                     | matching with 	                                     | matchers conflict                                                                                           |
|------------------------|-------------------|------------------------|:---------------------------------------|----------------------------------------------|-----------------------------------------------------------|-----------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| multiaddress         	 | -                 | emmitted or produced   | continuous simultanious use            | transport is successfully created            | closed by auditor or handler                              | optional path matcher expression              	     | never - extend retention rule when identical exists on creation                                             |
| transprot            	 | +                 | own peer id or absent  | one creator - one handler, no reuse    | traffic goes through or there's a connection | stream is exhausted                                       | optional own and other's path matching expressions 	 | - when matching with identical expressions<br/>- when creating transport with identical to existing address |
| peer connection      	 | +                 | own or remote peer id  | one creator - multiple serial handlers | there's an open stream                       | -closed by auditor<br/>-cannot access an active transport | optional PeerId or PeerKey	                         | handlers - never<br/>creators - same peer key                                                               |
| upgraded stream      	 | +                 | own or remote peer id  | one creator - one handler, no reuse    | when traffic goes through                    | exhausted                                                 | essential Protocol, optioinal PeerId	               | never - every demand or emission acts with another intended stream instance                                 |

Common facts:
- are reportable contexts to expose and observe violations
- could be traffic trackable
- traffic trackable should provide tracking and throughput control
- traffic trackable has initiator(PeerId)
- type-determined matching parameters
- type-determined access way(reusability, parallel usability, or creation on demand)
- type-determined use or expiration criteria
- use and expiration should be trackable
- could be closed externally - makes it expired, not usable, not trackable
- could have up to 4 representations - for creators, for handlers, for auditors, for retention control
### Protocol handlers
- created on demand by a protocol or multiaddress
- managed by creator
### Auditors
- checks newly-created resources before it gets to an accessor
- should be informed of a ?creator(emitter or provider), handler(server, consumer)
- process resources alltogether one-after-another
- access to creator and handler: stop()
- access to resource: close(), properties essential for matching, ?initiator, traffic tracking, violations observing
### Modules provide all kids of components
###### component is something with a common internal state requiring synchronous access
- ready to init accessors
- protocol handler factories
- auditors
### Mediator
- gets created with modules
- keeps resources based on usage and expiration events, retention type
- initializes accessors
- synchronizes asynchronous accessors to common resources
- aggregates creation of protocol handlers
- determines priority of handlers matching the same resource
- reports to auditors
- simplifies resources creation providing default implementation for auxilary functions(close(), violations mediator, expiration criteria, use criteria, initiator, retention type)
- implement master-switch
### Approach to multithreading
#### Single-threaded core
Is a way to synchronize access of async components to common state
#### Common access in parallel(and requirement to immutability/stateless/fast synchronization)
- multiaddresses usage
- retained resources lookup
- protocol handler factories
#### To run by mediator
- resources retention control including handling of usage and expiration events
- resources presentation to auditors
- initialization of accessors
- matching of creating with handling access actions
- switch on/off
- components and traffic trackables optionally
#### Synchronization points
- server binding is bridged from emitter
- consumer demand is bridging consumers to creators
- traffic tracker bridges a trackable to auditor
- reportable bridges a handler to auditor