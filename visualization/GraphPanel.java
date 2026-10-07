package visualization;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import main.*;
import state.*;

public class GraphPanel extends JPanel {
	GraphState state;
	Node vStart;
	Node vEnd;
	ArrayList<Node> vNodes;
	ArrayList<Edge> vEdges;
	
	public GraphPanel(GraphState stateIn, Node vStartIn, Node vEndIn, ArrayList<Node> vNodesIn, ArrayList<Edge> vEdgesIn) {
		state = stateIn;
		vStart = vStartIn;
		vEnd = vEndIn;
		vNodes = vNodesIn;
		vEdges = vEdgesIn;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		FontMetrics fm = g2.getFontMetrics();
		int r = 45;
		
		//alg
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		if (state.alg == 0) {
			//BFS
			g.drawString("BFS", 15, 35);
		} else if (state.alg == 1) {
			//Djikstra
			g.drawString("Djikstra", 15, 35);
		} else if (state.alg == 2) {
			//A*
			g.drawString("A*", 15,  35);
		} else if (state.alg == 3) {
			//no path
			g.drawString("PATH NOT FOUND", 15, 35);
		} else if (state.alg == 4) {
			//BFS - complete
			g.drawString("BFS - complete", 15, 35);
		} else if (state.alg == 5) {
			//Djikstra - complete
			g.drawString("Djikstra - complete", 15, 35);
		} else if (state.alg == 6) {
			//A* - complete
			g.drawString("A* - complete", 15, 35);
		}
		
		//edges
		for (Edge x : state.blackEdges) {
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x.from.x * 10 + r / 2 + 5, x.from.y * 10 + r / 2 + 40, x.to.x * 10 + r / 2 + 5, x.to.y * 10 + r / 2 + 40);
		}
		for (Edge x : state.redEdges) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x.from.x * 10 + r / 2 + 5, x.from.y * 10 + r / 2 + 40, x.to.x * 10 + r / 2 + 5, x.to.y * 10 + r / 2 + 40);
		}
		for (Edge x : state.greenEdges) {
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x.from.x * 10 + r / 2 + 5, x.from.y * 10 + r / 2 + 40, x.to.x * 10 + r / 2 + 5, x.to.y * 10 + r / 2 + 40);
		}
		
		//node bodies
		for (Node x : vNodes) {
			if (x == vStart) {
				g.setColor(Color.RED);
			} else if (x == vEnd) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.YELLOW);
			}
			g.fillOval(x.x * 10 + 5, x.y * 10 + 40, r, r);
		}
		
		//node outlines
		for (Node x : state.blackNodes) {
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(3));
			g2.drawOval(x.x * 10 + 5, x.y * 10 + 40, r, r);
		}
		for (Node x : state.orangeNodes) {
			g2.setColor(Color.ORANGE);
			g2.setStroke(new BasicStroke(3));
			g2.drawOval(x.x * 10 + 5, x.y * 10 + 40, r, r);
		}
		for (Node x : state.redNodes) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3));
			g2.drawOval(x.x * 10 + 5, x.y * 10 + 40, r, r);
		}
		for (Node x : state.greenNodes) {
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(3));
			g2.drawOval(x.x * 10 + 5, x.y * 10 + 40, r, r);
		}
		
		//weights
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		for (Edge x : vEdges) {
			g.drawString(String.format("%.0f", x.weight), (x.from.x + x.to.x) * 5 + r / 2 + 8 - fm.stringWidth(String.format("%.0f", x.weight)), (x.from.y + x.to.y) * 5 + r / 2  + 50);
		}
		
		//distances
		if (state.alg == 1 || state.alg == 5) {
			//Djikstra
			g.setColor(Color.BLUE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			for (Node x : vNodes) {
				if (state.distances1.get(x) == Double.POSITIVE_INFINITY) {
					g.drawString("-", x.x * 10 + r / 2 + 1, x.y * 10 + r / 2 + 46);
				} else {
					g.drawString(String.format("%.0f", state.distances1.get(x)), x.x * 10 + r / 2 + 7 - fm.stringWidth(String.format("%.0f",  state.distances1.get(x))), x.y * 10 + r / 2 + 46);
				}
			}
		} else if (state.alg == 2 || state.alg == 6) {
			//A*
			g.setColor(Color.BLUE);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			for (Node x : vNodes) {
				if (state.distances1.get(x) == Double.POSITIVE_INFINITY) {
					g.drawString("-", x.x * 10 + r / 2 + 3, x.y * 10 + r / 2 + 39);
				} else {
					g.drawString(String.format("%.0f", state.distances1.get(x)), x.x * 10 + r / 2 + 10 - fm.stringWidth(String.format("%.0f",  state.distances1.get(x))), x.y * 10 + r / 2 + 39);
				}
				if (state.distances2.get(x) == null) {
					g.drawString("-", x.x * 10 + r / 2 + 3, x.y * 10 + r / 2 + 53);
				} else {
					g.drawString(String.format("%.0f", state.distances2.get(x)) + "?", x.x * 10 + r / 2 + 15 - fm.stringWidth(String.format("%.0f",  state.distances2.get(x)) + "?"), x.y * 10 + r / 2 + 53);
				}
			}
		}
		
		//table
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(710, 30, 710, 355);
		g2.drawLine(860, 30, 860, 355);
		g2.drawLine(1010, 30, 1010, 355);
		g2.drawLine(560, 130, 1160, 130);
		g2.drawLine(560, 205, 1160, 205);
		g2.drawLine(560, 280, 1160, 280);
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Algorithm", 585, 95);
		g.drawString("Steps", 760, 95);
		g.drawString("Cost", 910, 95);
		g.drawString("Nodes", 1060, 85);
		g.drawString("Explored", 1048, 105);
		g.drawString("BFS", 610, 175);
		g.drawString("Djikstra", 595, 248);
		g.drawString("A*", 625, 320);
		
		//table values
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		if (state.stepsVal0 == 0) {
			g.drawString("-", 780, 175);
		} else {
			g.drawString(state.stepsVal0 + "", 780, 175);
		}
		if (state.costVal0 == 0) {
			g.drawString("-", 928, 175);
		} else {
			g.drawString(state.costVal0 + "", 928, 175);
		}
		if (state.nodesExploredVal0 == 0) {
			g.drawString("-", 1080, 175);
		} else {
			g.drawString(state.nodesExploredVal0 + "", 1080, 175);
		}
		
		if (state.stepsVal1 == 0) {
			g.drawString("-", 780, 250);
		} else {
			g.drawString(state.stepsVal1 + "", 780, 250);
		}
		if (state.costVal1 == 0) {
			g.drawString("-", 928, 250);
		} else {
			g.drawString(state.costVal1+ "", 928, 250);
		}
		if (state.nodesExploredVal1 == 0) {
			g.drawString("-", 1080, 250);
		} else {
			g.drawString(state.nodesExploredVal1 + "", 1080, 250);
		}
		
		if (state.stepsVal2 == 0) {
			g.drawString("-", 780, 325);
		} else {
			g.drawString(state.stepsVal2 + "", 780, 325);
		}
		if (state.costVal2 == 0) {
			g.drawString("-", 928, 325);
		} else {
			g.drawString(state.costVal2 + "", 928, 325);
		}
		if (state.nodesExploredVal2 == 0) {
			g.drawString("-", 1080, 325);
		} else {
			g.drawString(state.nodesExploredVal2 + "", 1080, 325);
		}
		
		//explanation
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("BFS finds the shortest path. Djikstra finds the cheapest path. A* also finds the cheapest path but uses physical distance to", 10, 610);
		g.drawString("estimate the total cost through a node which allows for greater accuracy when deciding which node to explore. Black nodes", 10, 630);
		g.drawString("are unvisited. Orange nodes are queued. Red nodes are visited. Green nodes show the final path. When a node flashes", 10, 650);
		g.drawString("green, the code is checking whether it is the ending node. Black edges have not been used to visit a node. Red edges have", 10, 670);
		g.drawString("been used to visit a node. Green edges show the final path. The blue numbers over edges are their weights. The current", 10, 690);
		g.drawString("shortest cost to reach a node is displayed as a blue number at the center or upper center of a node. The estimated total", 10, 710);
		g.drawString("cost through a node is displayed as a blue number with a question mark at the lower center of a node. The current", 10, 730);
		g.drawString("algorithm is displayed at the top-left.", 10, 750);
	}
}
