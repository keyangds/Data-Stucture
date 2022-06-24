### Graphs
A graph is a data structure that is composed of nodes and edges. The nodes store a data point (such as an integer) and the edges describe how the nodes are connected. Typical functionality of a graph data structure includes the ability to add and remove nodes, add and remove edges between two nodes, and get the number of nodes and edges. Depending on the purpose of the graph, more functionality may be added.


### The MoneyGame class
The "Money Game" is a simple game that is used in graph theory. The graph nodes can have an integer value, and that value can be negative, positive, or zero. This value represents an amount of money. The goal of the game is to distribute the money (though the graph edges) such that each node ends up with a value greather than or equal to zero (all nodes have no "debt"). To distribute money, you select a node and that node gives 1 "money" to each of the nodes that it is connected to (increasing their value by 1). If the node has less money than the number of  nodes it is connected to, then that node will go into "debt" (it will have a negative value). [Here is a good video](https://youtu.be/U33dsEcKgeQ) describing the game (it was this video that was the inspiration for this project).

Creating an algorithm to solve the game can actually get fairly complicated, and combined with the `Project3Graph` implementation would be too much for this project. As such, **you are not required to write a method that solves this game, or even make a play.** What you are required to do is provide a method that returns a boolean value representing whether the game is winnable or not. This is described in the YouTube video, but essentially if the total amount of "money" in the graph (the sum of all the node's values) is greater than or equal to the genus of the graph, the game can be won. It is technically possible for a game to be winnable if this not the case, but for our purposes, if this condition is not true than we will say that the game is not winnable.




