package main;

import java.util.*;

public class Node {
	String name;
	public int x;
	public int y;
	public ArrayList<Edge> edges = new ArrayList<>();
	
	public Node(String nameIn, int xIn, int yIn) {
		name = nameIn;
		x = xIn;
		y = yIn;
	}
}