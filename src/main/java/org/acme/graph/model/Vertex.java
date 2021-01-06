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
