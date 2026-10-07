package state;

import java.util.*;
import main.*;

public class GraphState {
	//alg
	public int alg;
	
	//nodes
	public Set<Node> blackNodes = new HashSet<>();
	public Set<Node> orangeNodes = new HashSet<>();
	public Set<Node> redNodes = new HashSet<>();
	public Set<Node> greenNodes = new HashSet<>();
	
	//edges
	public Set<Edge> blackEdges = new HashSet<>();
	public Set<Edge> redEdges = new HashSet<>();
	public Set<Edge> greenEdges = new HashSet<>();
	
	//distances
	public Map<Node, Double> distances1 = new HashMap<>();
	public Map<Node, Double> distances2 = new HashMap<>();
	
	//table stuff
	public int stepsVal0;
	public int costVal0;
	public int nodesExploredVal0;
	public int stepsVal1;
	public int costVal1;
	public int nodesExploredVal1;
	public int stepsVal2;
	public int costVal2;
	public int nodesExploredVal2;
	
	//stage
	public int stage;
}