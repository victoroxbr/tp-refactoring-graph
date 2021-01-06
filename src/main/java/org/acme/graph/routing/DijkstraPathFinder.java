package org.acme.graph.routing;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Utilitaire pour le calcul du plus court chemin dans un graphe
 * 
 * @author MBorne
 *
 */
public class DijkstraPathFinder {

	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);

	private Graph graph;
	
	private Map<Vertex, PathNode> nodes;

	public DijkstraPathFinder(Graph graph) {
		this.graph = graph;
		this.nodes = new HashMap<Vertex, PathNode>();
	}
	
	public PathNode getNode(Vertex vertex) {
		return this.nodes.get(vertex);
	}

	/**
	 * Calcul du plus court chemin entre une origine et une destination
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public Path findPath(Vertex origin, Vertex destination) {
		log.info("findPath({},{})...",origin,destination);
		initGraph(origin);
		Vertex current;
		while ((current = findNextVertex()) != null) {
			visit(current);
			if (this.getNode(destination).getReachingEdge() != null) {
				log.info("findPath({},{}) : path found",origin,destination);
				return buildPath(destination);
			}
		}
		log.info("findPath({},{}) : path not found",origin,destination);
		return new Path();
	}

	/**
	 * Parcourt les arcs sortants pour atteindre les sommets avec le meilleur coût
	 * 
	 * @param vertex
	 */
	private void visit(Vertex vertex) {
		log.trace("visit({})", vertex);
		Collection<Edge> outEdges = vertex.getOutEdges();
		/*
		 * On étudie chacun des arcs sortant pour atteindre de nouveaux sommets ou
		 * mettre à jour des sommets déjà atteint si on trouve un meilleur coût
		 */
		for (Edge outEdge : outEdges) {
			Vertex reachedVertex = outEdge.getTarget();
			/*
			 * Convervation de arc permettant d'atteindre le sommet avec un meilleur coût
			 * sachant que les sommets non atteint ont pour coût "POSITIVE_INFINITY"
			 */
			PathNode node = this.getNode(reachedVertex);
			double newCost = this.getNode(vertex).getCost() + outEdge.getCost();
			if (newCost < node.getCost()) {
				node.setCost(newCost);
				node.setReachingEdge(outEdge);
			}
		}
		/*
		 * On marque le sommet comme visité
		 */
		this.getNode(vertex).setVisited(true);
	}

	/**
	 * Construit le chemin en remontant les relations incoming edge
	 * 
	 * @param target
	 * @return
	 */
	private Path buildPath(Vertex target) {
		Path result = new Path();

		Edge current = this.getNode(target).getReachingEdge();
		do {
			result.addEdge(current);
			current = this.getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result.getEdges());
		return result;
	}

	/**
	 * Prépare le graphe pour le calcul du plus court chemin
	 * 
	 * @param source
	 */
	private void initGraph(Vertex source) {
		log.trace("initGraph({})", source);
		for (Vertex vertex : graph.getVertices()) {
			PathNode node = new PathNode();	
			node.setCost(source == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			node.setReachingEdge(null);
			node.setVisited(false);
			this.nodes.put(vertex, node);
		}
	}

	/**
	 * Recherche le prochain sommet à visiter. Dans l'algorithme de Dijkstra, ce
	 * sommet est le sommet non visité le plus proche de l'origine du calcul de plus
	 * court chemin.
	 * 
	 * @return
	 */
	private Vertex findNextVertex() {
		double minCost = Double.POSITIVE_INFINITY;
		Vertex result = null;
		for (Vertex vertex : graph.getVertices()) {
			PathNode node = this.getNode(vertex);
			// sommet déjà visité?
			if (node.isVisited()) {
				continue;
			}
			// sommet non atteint?
			if (node.getCost() == Double.POSITIVE_INFINITY) {
				continue;
			}
			// sommet le plus proche de la source?
			if (node.getCost() < minCost) {
				result = vertex;
			}
		}
		return result;
	}

}
