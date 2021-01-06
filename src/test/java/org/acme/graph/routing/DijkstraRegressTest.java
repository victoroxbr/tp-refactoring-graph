package org.acme.graph.routing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.acme.graph.TestGraphFactory;
import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests fonctionnels sur DijkstraPathFinder
 * 
 * @author MBorne
 *
 */
public class DijkstraRegressTest {

	private Graph graph;

	private DijkstraPathFinder finder;

	@Before
	public void setUp() throws Exception {
		this.graph = TestGraphFactory.createGraph01();
		this.finder = new DijkstraPathFinder(graph);
	}

	@Test
	public void testABFound() {
		Path path = finder.findPath(graph.findVertex("a"), graph.findVertex("b"));
		assertNotNull(path);
		assertEquals(1, path.getEdges().size());
	}

	@Test
	public void testBANotFound() {
		Path path = finder.findPath(graph.findVertex("b"), graph.findVertex("a"));
		assertEquals(0, path.getEdges().size());
	}

	@Test
	public void testACFoundWithCorrectOrder() {
		Path path = finder.findPath(graph.findVertex("a"), graph.findVertex("c"));
		assertNotNull(path);
		assertEquals(2, path.getEdges().size());

		int index = 0;
		{
			Edge edge = path.getEdges().get(index++);
			assertEquals("a", edge.getSource().getId());
			assertEquals("b", edge.getTarget().getId());
		}
		{
			Edge edge = path.getEdges().get(index++);
			assertEquals("b", edge.getSource().getId());
			assertEquals("c", edge.getTarget().getId());
		}
	}
}
