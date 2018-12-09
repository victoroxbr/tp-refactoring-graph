package org.acme.graph.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;

/**
 * 
 * Utilitaire pour le calcul du plus court chemin dans un graphe
 * 
 * @author MBorne
 *
 */
public class DijkstraPathFinder {
	
	private Graph graph ;
	
	public DijkstraPathFinder(Graph graph){
		this.graph  = graph;
	}
	
	/**
	 * Recherche d'un sommet entre deux sommets
	 * @param source
	 * @param target
	 * @return
	 */
	public List<Edge> findPath(Vertex source, Vertex target){
		initGraph(source);
		Vertex current ;
		while ( ( current = findNextVertex() ) != null ){
			visit(current);
			if ( target.getReachingEdge() != null ){
				return buildPath(target);
			}
		}
		return null;
	}
	
	/**
	 * Parcours les arcs sortants pour atteindre les sommets
	 * @param vertex
	 */
	private void visit(Vertex vertex) {
		List<Edge> outEdges = findOutEdges(vertex);
		for (Edge outEdge : outEdges) {
			Vertex reachedVertex = outEdge.getTarget();
			double newCost = vertex.getCost() + outEdge.getCost();
			if ( newCost < reachedVertex.getCost() ){
				reachedVertex.setCost(newCost);
				reachedVertex.setReachingEdge(outEdge);
			}
		}
		vertex.setVisited(true);
	}
	
	
	/**
	 * Recherche des arcs sortant d'un sommet
	 * @param vertex
	 * @return
	 */
	private List<Edge> findOutEdges(Vertex vertex) {
		List<Edge> result = new ArrayList<Edge>();
		for ( Edge candidate : graph.getEdges() ){
			if ( candidate.getSource() != vertex ){
				continue;
			}
			result.add(candidate);
		}
		return result;
	}

	/**
	 * Construit le chemin en remontant les relations incoming edge
	 * @param target
	 * @return
	 */
	private List<Edge> buildPath(Vertex target){
		List<Edge> result = new ArrayList<Edge>();

		Edge current = target.getReachingEdge();
		do {
			result.add(current);
			current = current.getSource().getReachingEdge();
		} while ( current != null );
		
		Collections.reverse(result);
		return result;
	}
	
	
	/**
	 * Prépare le graphe pour le calcul du plus court chemin
	 * @param source
	 */
	private void initGraph(Vertex source) {
		for (Vertex vertex : graph.getVertices()) {
			vertex.setCost(source == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			vertex.setReachingEdge(null);
			vertex.setVisited(false);
		}
	}

	/**
	 * Recherche le prochain sommet à visiter
	 * @return
	 */
	private Vertex findNextVertex(){
		double minCost = Double.POSITIVE_INFINITY;
		Vertex result = null;
		for (Vertex vertex : graph.getVertices()) {
			// sommet déjà visité?
			if ( vertex.isVisited() ){
				continue;
			}
			// sommet non atteint?
			if ( vertex.getCost() == Double.POSITIVE_INFINITY ){
				continue;
			}
			// sommet le plus proche de la source?
			if ( vertex.getCost() < minCost ){
				result = vertex;
			}
		}
		return result;
	}
	
}
