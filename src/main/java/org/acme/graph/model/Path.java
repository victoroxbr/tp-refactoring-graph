package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Path {
	
	/**
	 * Liste d'arcs du chemin
	 */
	private List<Edge> edges;
	
	public Path() {
		this.edges = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}
}
