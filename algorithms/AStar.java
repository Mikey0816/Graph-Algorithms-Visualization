package algorithms;

import java.util.*;
import main.*;
import state.GraphState;
import visualization.GraphPanel;

public class AStar {
	public static void findPath(Node startIn, Node endIn, ArrayList<Node> nodesIn, GraphState stateIn, GraphPanel panelIn) throws InterruptedException {
		stateIn.alg = 2; //screen displays "A*"
		for (Node x : stateIn.orangeNodes) { //all nodes and edges are set back to the normal colors
			stateIn.blackNodes.add(x);
		}
		stateIn.orangeNodes.clear();
		for (Node x : stateIn.redNodes) {
			stateIn.blackNodes.add(x);
		}
		stateIn.redNodes.clear();
		for (Node x : stateIn.greenNodes) {
			stateIn.blackNodes.add(x);
		}
		stateIn.greenNodes.clear();
		for (Edge x : stateIn.redEdges) {
			stateIn.blackEdges.add(x);
		}
		stateIn.redEdges.clear();
		for (Edge x : stateIn.greenEdges) {
			stateIn.blackEdges.add(x);
		}
		stateIn.greenEdges.clear();
		Map<Node, Double> distances = new HashMap<>();
		Map<Node, Double> estimates = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(estimates.get(a), estimates.get(b)));
		for (Node x : nodesIn) {
			distances.put(x, Double.POSITIVE_INFINITY);
			stateIn.distances1.put(x, Double.POSITIVE_INFINITY); //all nodes distance set to infinity
		}
		distances.put(startIn, 0.0);
		stateIn.distances1.put(startIn, 0.0); //starting node distance set to 0
		estimates.put(startIn, Math.floor(Main.distance(startIn, endIn)));
		stateIn.distances2.put(startIn, Math.floor(Main.distance(startIn, endIn))); //estimated distance put in for starting node
		panelIn.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		pq.add(startIn);
		stateIn.blackNodes.remove(startIn);
		stateIn.orangeNodes.add(startIn); //outline of queued node becomes orange
		panelIn.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		Set<Node> visited = new HashSet<>();
		Map<Node, Node> parents = new HashMap<>();
		boolean endReached = false;
		Node current;
		int nodesExplored = 0;
		
		outerLoop:
		while(!pq.isEmpty()) {
			current = pq.poll();
			visited.add(current);
			stateIn.orangeNodes.remove(current);
			stateIn.redNodes.add(current); //outline of current node becomes red
			panelIn.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			nodesExplored++;
			stateIn.nodesExploredVal2++; //nodes explored value for A* goes up by 1
			panelIn.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			stateIn.redNodes.remove(current);
			stateIn.greenNodes.add(current); //current node flashes green twice
			panelIn.repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {}
			stateIn.greenNodes.remove(current);
			stateIn.redNodes.add(current);
			panelIn.repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {}
			stateIn.redNodes.remove(current);
			stateIn.greenNodes.add(current);
			panelIn.repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {}
			stateIn.greenNodes.remove(current);
			stateIn.redNodes.add(current);
			panelIn.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			if (current == endIn) {
				stateIn.redNodes.remove(current); //current node becomes green
				stateIn.greenNodes.add(current);
				panelIn.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				
				while (current != startIn) {
					for (Edge x : current.edges) {
						if (x.to == parents.get(current)) {
							stateIn.redEdges.remove(x); //edge becomes green
							stateIn.greenEdges.add(x);
							panelIn.repaint();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
							stateIn.costVal2 += x.weight; //cost value for A* goes up by weight of edge
							panelIn.repaint();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
						}
					}
					
					stateIn.redNodes.remove(parents.get(current)); //node becomes green
					stateIn.greenNodes.add(parents.get(current));
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					stateIn.stepsVal2++; //steps value for A* goes up by 1
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					
					current = parents.get(current);
				}
				
				break outerLoop;
			}
			
			for (Edge x : current.edges) {
				if (!visited.contains(x.to)) {
					stateIn.blackEdges.remove(x);
					stateIn.redEdges.add(x); //current edge becomes red
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					
					stateIn.distances1.put(x.to, distances.get(current) + x.weight); //distance of neighboring node flashes the distance through the current node twice
					panelIn.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
					stateIn.distances1.put(x.to, distances.get(x.to));
					panelIn.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
					stateIn.distances1.put(x.to, distances.get(current) + x.weight);
					panelIn.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
					stateIn.distances1.put(x.to, distances.get(x.to));
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					if (distances.get(current) + x.weight < distances.get(x.to)) {
						distances.put(x.to, distances.get(current) + x.weight);
						stateIn.distances1.put(x.to, distances.get(current) + x.weight); //distance updated
						estimates.put(x.to, distances.get(x.to) + Math.floor(Main.distance(x.to, endIn)));
						stateIn.distances2.put(x.to, distances.get(x.to) + Math.floor(Main.distance(x.to, endIn)));
						panelIn.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {}
						if (!pq.contains(x.to)) {
							pq.add(x.to);
							stateIn.blackNodes.remove(x.to);
							stateIn.orangeNodes.add(x.to); //outline of neighboring node becomes orange
							panelIn.repaint();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
						}
						parents.put(x.to, current);
					}
				}
			}
		}
		
		stateIn.alg = 6; //screen displays "A* - complete"
		panelIn.repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
	}
}