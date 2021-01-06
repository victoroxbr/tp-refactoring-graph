package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * Un sommet dans un graphe
 * 
 * @author MBorne
 *
 */
public class Vertex {

	/**
	 * Identifiant du sommet
	 */
	private String id;

	/**
	 * Position du sommet
	 */
	private Coordinate coordinate;

	/**
	 * dijkstra - coût pour atteindre le sommet
	 */
	private double cost;
	/**
	 * dijkstra - arc entrant avec le meilleur coût
	 */
	private Edge reachingEdge;
	/**
	 * dijkstra - indique si le sommet est visité
	 */
	private boolean visited;
	/**
	 * Arc entrant vers le sommet
	 */
	private List<Edge> inEdges = new ArrayList<Edge>();
	/**
	 * Arc sortant du sommet
	 */
	private List<Edge> outEdges = new ArrayList<Edge>();

	public Vertex() {

	}
	
	/**
	 * Ajout arc dans arc entrant
	 * 
	 * @param inEdge
	 */
	protected void addInEdge(Edge inEdge) {
		this.inEdges.add(inEdge);
	}
	
	/**
	 * Ajout arc dans arc sortant
	 * 
	 * @param outEdge
	 */
	protected void addOutEdge(Edge outEdge) {
		this.outEdges.add(outEdge);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@JsonIgnore
	public Edge getReachingEdge() {
		return reachingEdge;
	}

	public void setReachingEdge(Edge reachingEdge) {
		this.reachingEdge = reachingEdge;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return id;
	}

	/**
	 * @return the inEdges
	 */
	@JsonIgnore
	public Collection<Edge> getInEdges() {
		return inEdges;
	}

	/**
	 * @return the outEdges
	 */
	@JsonIgnore
	public Collection<Edge> getOutEdges() {
		return outEdges;
	}

}
