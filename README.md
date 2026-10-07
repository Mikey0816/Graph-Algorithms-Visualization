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
