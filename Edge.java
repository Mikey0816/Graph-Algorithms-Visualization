package main;

public class Edge {
	public Node from;
	public Node to;
	public double weight;
	
	public Edge(Node fromIn, Node toIn, double weightIn) {
		from = fromIn;
		to = toIn;
		weight = weightIn;
	}
}