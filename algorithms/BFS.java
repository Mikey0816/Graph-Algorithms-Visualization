package algorithms;

import java.util.*;
import main.*;
import state.*;
import visualization.*;

public class BFS {
	public static void findPath(Node startIn, Node endIn, GraphState stateIn, GraphPanel panelIn) throws InterruptedException {
		stateIn.alg = 0; //screen displays "BFS"
		panelIn.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		Queue<Node> queue = new LinkedList<>();
		Set<Node> visited = new HashSet<>();
		Map<Node, Node> parents = new HashMap<>();
		queue.add(startIn);
		stateIn.blackNodes.remove(startIn);
		stateIn.orangeNodes.add(startIn); //outline of queued node becomes orange
		panelIn.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		stateIn.nodesExploredVal0++; //nodes explored value for BFS goes up by 1
		panelIn.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		visited.add(startIn);
		boolean endReached = false;
		Node current;
		
		outerLoop:
		while (!queue.isEmpty()) {
			current = queue.remove();
			stateIn.orangeNodes.remove(current);
			stateIn.redNodes.add(current); //outline of current node becomes red
			panelIn.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			stateIn.nodesExploredVal0++; //nodes explored value for BFS goes up by 1
			panelIn.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			for (Edge x : current.edges) {
				if (!visited.contains(x.to)) {
					queue.add(x.to);
					visited.add(x.to);
					parents.put(x.to, current);
					stateIn.blackEdges.remove(x);
					stateIn.redEdges.add(x); //current edge becomes red
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					stateIn.blackNodes.remove(x.to);
					stateIn.orangeNodes.add(x.to); //outline of queued node becomes orange
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					
					stateIn.orangeNodes.remove(x.to);
					stateIn.greenNodes.add(x.to); //neighboring node flashes green twice
					panelIn.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
					stateIn.greenNodes.remove(x.to);
					stateIn.orangeNodes.add(x.to);
					panelIn.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
					stateIn.orangeNodes.remove(x.to);
					stateIn.greenNodes.add(x.to);
					panelIn.repaint();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {}
					stateIn.greenNodes.remove(x.to);
					stateIn.orangeNodes.add(x.to);
					panelIn.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					if (x.to == endIn) {
						stateIn.orangeNodes.remove(x.to); //neighboring node becomes green
						stateIn.greenNodes.add(x.to);
						panelIn.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {}
						
						current = endIn;
						while (current != startIn) {
							for (Edge y : current.edges) {
								if (y.to == parents.get(current)) {
									stateIn.redEdges.remove(y); //edge becomes green
									stateIn.greenEdges.add(y);
									panelIn.repaint();
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {}
									stateIn.costVal0 += y.weight; //cost value for BFS goes up by weight of edge
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
							stateIn.stepsVal0++; //steps value for BFS goes up by 1
							panelIn.repaint();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
							
							current = parents.get(current);
						}
						
						endReached = true;
						break outerLoop;
					}
				}
			}
		}
		
		if (!endReached) {
			stateIn.alg = 3; //screen displays "PATH NOT FOUND"
			panelIn.repaint();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			System.exit(0);
		}
		stateIn.alg = 4; //screen displays "BFS - complete"
		panelIn.repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
	}
}
