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
	
	public Edge(Vertex source, Vertex target) {
		assert(source != null);
		assert(target != null);
		this.source = source;
		this.target = target;
		this.source.addOutEdge(this);
		this.target.addInEdge(this);
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
		Coordinate[] c = new Coordinate[2];
		c[0] = this.source.getCoordinate();
		c[1] = this.target.getCoordinate();
		GeometryFactory geometryFactory = new GeometryFactory();
		return geometryFactory.createLineString(c);
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
		return source.getCoordinate().distance(target.getCoordinate());
	}

	@Override
	public String toString() {
		return id + " (" + source + "->" + target + ")";
	}

}
