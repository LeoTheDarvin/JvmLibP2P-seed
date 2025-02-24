# LibP2P seed for JVM
###### [**learn more about concept of a seed**](https://github.com/LeoTheDarvin/ForLibP2PCommunity/blob/root/proposals.md#early-decoupling)
## What should be added here?
Just a minimum of essentials for developer-community-wide acceptance declarations and contracts.</br>
Compliance to interfaces of **seed** must become sufficient to integrate an implementation with entire LibP2P.

## How to achieve common acceptance?
Shortly - it's impossible. But wide acceptance is achievable and must be taken for priority within seed. 
Here's an approach to [manage Acceptance Rating](https://github.com/LeoTheDarvin/ForLibP2PCommunity/blob/root/proposals.md#git-branch-based-conflicts-negotiation-and-acceptance-rating)

## Considerations for implementors
###### Content of the topic is highly disputable inherently. Think critically and consider AR taking something of here as a contract
- keep compatibility with both Android and OpenJDK

## Understanding of architecture
###### To meet whole diversity of LibP2P mechanics preserving extensibility LibP2P should be layered with strictly defined relations between layers
### Domain primitives
Multiformats and abstractions(like stream) described in official docs should be taken as primitives available throughout all layers above.
### Pipeline
Pipeline layer consists of pipes and handlers. Handlers are up to be concentrated on protocol complexities entirely. Pipes should drive handlers serving auxilary functionalities as running in parallel, buffering. Pipes should care of requirements of [the following layer](#components)(as transmission trackability).
### [Components](./components-layer.md)
Components layer is a layer concentrated on access to _**the resources**_ and tying the host instance together. 
</br>_**The resources**_ are following: dialable multiaddresses, open transports, connections to peers, upgraded application streams. 

Components layer: 
- is presenting basic end-user mechanics as dialing by peerId
- defines modules for running local instance with
- runs components building pipelines
- presents single access object to leverage whole the host
- introduces master-switch
### Services
Services is a layer suggested on top of LibP2P core.
Introducing modules with components services could implement advanced mechanics as: 
- application protocols(PubSub), 
- smart peers scoring, 
- serving/consumption balance.

## How to?
###### This section is aimed to narrow a learning curve for individuals who want to implement a custom module.
### Run application 
### Implement a module
### Implement a transport
### Implement an upgrade protocol
### Implement a multiplexer
### Implement an application protocol
### Implement a traffic relay
### Implement an address book
### Embed NAT-traversal
### Embed system of control over traffic and malicious behavior

## Attachment
#### Compatibility with android
###### TODO: make a complete guide, please
- Android supports native features up to Java 7. Kotlin is supported completely

