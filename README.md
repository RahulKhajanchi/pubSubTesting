# pubSubTesting
Testing of pubSub

Pub-Sub

Every communication like publishing a message or retrieving a msg from a subscriber is done via message channel.

While we publish any message to a topic, we send the message to the channel through gateway which posts the message
in that channel.

After that the subscribers which are subscribed to that topic reciever the messages.
The messages are kept in the queue until they are acknowleged. Once acknowledged they get removed from the queue.

How to acknowledge a message from the queue?

1) We create one PubSubInboundChannelAdapter which links the pubSubtempelate to the subscriber and puts the message
into a channel. 
2) After that the message is recieved from the channel and we can do whatever is required.

