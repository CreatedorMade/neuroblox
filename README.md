# neuroblox
Data for the voxel-based neural networking API, Neuroblox.

## What is it?
Neuroblox is a Java API that is meant to be used for neural networking. Nets, or "brains" as they're called by the API, are three-dimensional structures containing a voxel grid of neurons. Neurons can be easily added to the API - the root neuron class is abstract and all neuron generation is to be handled by the end user (don't worry though, there is a function to help with generation).

## How does it work?
To start off, you'll need to make a neuron type.

Neurons are VERY easy to make. Just extend the AbstractNeuron class to make one. The variables you'll need to access are `super.dataIn`, `super.dataOut` and `super.facing`, and you'll need to override `tick(int t)`.

`super.dataIn` and `super.dataOut` are arrays containing six double values each - each element in them corresponds to the data coming in or going out on any side of the neuron (e.g. access `super.dataIn[Brain.TOP]` to get the data coming from the neuron above, if there is one). Sending data out is just as easy - just assign a value into `super.dataOut` during `tick()`. Just like that, you've made a neuron! The `super.facing` variable is mostly just there for convenience - it also corresponds to one of six direction values, but is randomly generated and really only affects anything if you use it. It's good for directional affects/inputs.

To place neurons into a brain, simply use the `placeRandomly()` method. Give it any subclass of AbstractNeuron and it'll return true if it manages to fit the neuron in, or false if the brain is full and it can't. I recommend you don't do any "live editing" - add all the neurons to the brain BEFORE simulating it. (Of course, I can't tell you what to do, but you'll get some strange results if you live edit.) You can also manually access the brain's neurons using `Brain.neurons`.

Once all that's done, you'll need an I/O system and simulation. To set the value of a single tile in the brain's wall, effectively making that tile an input into the brain, use `setInputAt(double, x, y, side)` where `double` is the data you want to set it to, `x` and `y` are the location on the wall (0-indexed) and `side` is which wall you want to set it on (e.g. `Brain.TOP`). Using the similar function `getInputAt()` you can get data out of the brain. After setting all that up, just set/read that data and call `Brain.tick()` repeatedly (to actually simulate everything) and you should have some fully functioning brains!
