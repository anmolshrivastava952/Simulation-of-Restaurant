# Simulation-of-Restaurant

Event driven simulation of a working of a restaurant.

How does this event driven simulation work?
-----------------------------------------

An event can be anything like taking an order, preparing the food and handing the complete order back to customer. In every event we have to perform certain function. To complete these function, I have used the event object. An event object has two important component i) eventId which tells what kind of functions needs to be performed to complete the event & ii) eventTime which tells at what time the event is to be performed.

A global clock is implemented using heap datastructure. This heap takes event as input and sort them on the basis of the eventTime. When a particular time is given as input all the events which are to be processed before this time are processed out of the queue and implemented. That's how simulation progresses.