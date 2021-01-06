package org.acme.graph.routing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {
	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);
	
	private Map<Vertex, PathNode> nodes;
	
	public PathTree(Graph graph, Vertex destination) {
		this.nodes = new HashMap<Vertex, PathNode>();
		log.trace("initGraph({})", destination);
		for (Vertex vertex : graph.getVertices()) {
			PathNode node = new PathNode();	
			node.setCost(destination == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			node.setReachingEdge(null);
			node.setVisited(false);
			this.nodes.put(vertex, node);
		}
	}
	
	public PathNode getNode(Vertex vertex) {
		return this.nodes.get(vertex);
	}
	
	public Path getPath(Vertex destination) {
		Path result = new Path();

		Edge current = this.getNode(destination).getReachingEdge();
		do {
			result.addEdge(current);
			current = this.getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result.getEdges());
		return result;
	}

}
