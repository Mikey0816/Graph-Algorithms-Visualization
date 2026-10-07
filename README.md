# Graph-Algorithms-Comparison
This is an independent java project I made over the course of a few months. Given a graph of nodes and edges, weights are automatically assigned based on distance. Then, various algorithms are ran on the graph. These algorithms are visualized in real-time. They are also compared through various statistics in a table. An explanation is included at the bottom of the screen.

Quick Explanation:

BFS finds the shortest path. Djikstra finds the cheapest path. A* also finds the cheapest path but uses physical distance to estimate the total cost through a node which allows for greater accuracy when deciding which node to explore. Black nodes are unvisited. Orange nodes are queued. Red nodes are visited. Green nodes show the final path. When a node flashes green, the code is checking whether it is the ending node. Black edges have not been used to visit a node. Red edges have been used to visit a node. Green edges show the final path. The blue numbers over edges are their weights. The current shortest cost to reach a node is displayed as a blue number at the center or upper center of a node. The estimated total cost through a node is displayed as a blue number with a question mark at the lower center of a node. The current algorithm is displayed at the top-left.

Basic Structure:

The algorithms update the GraphState file as it runs. This file contains several variables that control the visual state of the project. The algorithms then repaint the PaintComponent in the JPanel, updating the screen as the algorithms run. The algorithms are ran chronologically through a thread in the main file.

Packages:

Main - main file and node and edge data structures
Algorithms - the 3 algorithms
State - graphstate file
Visualization - graphpanel file

Files:

Main - Main - sets everything up to run the algorithms and then runs the algorithms
Main - Node - node data structure: name, position x, and position y
Main - Edge - edge data structure: one node it’s connected to, the other node it’s connected to, weight

Algorithms - BFS - bfs algorithm, takes in starting node, ending node, graphstate object, and graphpanel object
Algorithms - Djikstra - djikstra algorithm, takes in same inputs as bfs along with arraylist of nodes, which is necessary for assigning tentative distances to each node unlike bfs
Algorithms - A* - a* algorithm, takes in same inputs as djikstra

State - GraphState - contains several variables that indirectly control the visual state of the project

Visualization - GraphPanel - converts variables in graphstate to what we see on the screen
