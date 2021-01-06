package org.acme.graph.model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * 
 * Un arc matérialisé par un sommet source et un sommet cible
 * 
 * @author MBorne
 *
 */
public class Edge {
	/**
	 * Identifiant de l'arc
	 */
	private String id;

	/**
	 * Sommet initial
	 */
	private Vertex source;

	/**
	 * Sommet final
	 */
	private Vertex target;
	
	/**
	 * Géométrie de l'arc
	 */
	private LineString geometry;
	
	public Edge(Vertex source, Vertex target) {
		this.source = source;
		this.target = target;
		this.source.addOutEdge(this);
		this.target.addInEdge(this);
		
		Coordinate[] c = {this.source.getCoordinate(), this.target.getCoordinate()};
		GeometryFactory geometryFactory = new GeometryFactory();
		this.geometry = geometryFactory.createLineString(c);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIdentityInfo(
	        generator=ObjectIdGenerators.PropertyGenerator.class, 
	        property="id"
	    )

	@JsonIdentityReference(alwaysAsId=true)
	public Vertex getSource() {
		return source;
	}
	
	@JsonSerialize(using = GeometrySerializer.class)
	public LineString getGeometry() {
		return geometry;
	}

	public Vertex getTarget() {
		return target;
	}

	/**
	 * dijkstra - coût de parcours de l'arc (distance géométrique)
	 * 
	 * @return
	 */
	public double getCost() {
		return geometry.getLength();
	}

	@Override
	public String toString() {
		return id + " (" + source + "->" + target + ")";
	}

	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(LineString geometry) {
		this.geometry = geometry;
	}

}
