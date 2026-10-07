package main;

import algorithms.*;
import java.util.*;
import javax.swing.*;
import state.*;
import visualization.*;

public class Main {
	public static void main(String args[]) throws InterruptedException {
		//creating arraylists for all nodes and edges
		ArrayList<Node> nodes = new ArrayList<>();
		ArrayList<Edge> allEdges = new ArrayList<>();
		
		//visualization stuff
		GraphState myState = new GraphState();
		GraphPanel myPanel = new GraphPanel(myState, new Node("poop", 67, 67), new Node("poop", 67, 67), nodes, allEdges);
		
		//yeah
		myState.stage = 0;
		
		//creating nodes and edges and adding them to respective arraylists
		Node a = new Node("A", 0, 10);
		Node b = new Node("B", 13, 15);
		Node c = new Node("C", 27, 5);
		Node d = new Node("D", 40, 10);
		Node e = new Node("E", 20, 40);
		
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		nodes.add(e);
		
		addEdge(a, b, allEdges);
		addEdge(b, c, allEdges);
		addEdge(c, d, allEdges);
		addEdge(a, e, allEdges);
		addEdge(e, d, allEdges);
		
		//setting start and end nodes
		Node startNode = a;
		Node endNode = d;
		
		JFrame myFrame = new JFrame("Algorithm Comparison Visualization");
		myFrame.setSize(1200, 800);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
		//setup stuff
		for (Node x : nodes) {
			myState.blackNodes.add(x);
		}
		for (Edge x : allEdges) {
			myState.blackEdges.add(x);
		}
		myPanel.repaint(); 
		
		//running algorithms
		new Thread(() -> {
			try {
				BFS.findPath(startNode, endNode, myState, myPanel);
				Djikstra.findPath(startNode, endNode, nodes, myState, myPanel);
				AStar.findPath(startNode, endNode, nodes, myState, myPanel);
			} catch (InterruptedException e1) {}
		}).start();
	}

	public static void addEdge(Node nodeAIn, Node nodeBIn, ArrayList<Edge> edgesIn) {
		edgesIn.add(new Edge(nodeAIn, nodeBIn, Math.ceil(distance(nodeAIn, nodeBIn))));
		nodeAIn.edges.add(new Edge(nodeAIn, nodeBIn, Math.ceil(distance(nodeAIn, nodeBIn))));
		nodeBIn.edges.add(new Edge(nodeBIn, nodeAIn, Math.ceil(distance(nodeAIn, nodeBIn))));
	}

	public static double distance(Node nodeOne, Node nodeTwo) {
		return Math.sqrt(Math.pow(nodeTwo.x - nodeOne.x, 2) + Math.pow(nodeTwo.y - nodeOne.y, 2));
	}
}