package dsa2;

import dsa2.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class GraphTest {

	private Graph g;
	private Town wat;
	private Town dun;
	private Town mid;
	private Town cork;
	private Town cobh;
	private Town clon;
	private Town caros;
	
	
	@Before
	public void setUp() 
	{
		g = new Graph();
		wat = new Town("Waterford");
		dun = new Town("Dungarvan");
		mid = new Town("Middleton");
		cork = new Town("Cork");
		cobh = new Town("Cobh");
		clon = new Town("Clonmel");
		caros = new Town("Carrick-on-Suir");
	
		g.addTown(wat); //0
		g.addTown(dun); //1
		g.addTown(cork); //2
		g.addTown(mid); //3
		g.addTown(cobh); //4
		g.addTown(caros); //5
		
		g.addRoad(wat, dun, 60);
		g.addRoad(mid, dun, 50);
		g.addRoad(mid, cork, 20);
		g.addRoad(mid, cobh, 20);
		g.addRoad(wat, caros, 30);
		g.addRoad(dun, caros, 25);
		
		g.dfsearch();
		
	}
	
	@Test
	public void addTownTest()
	{
		setUp();
		
				
		assertEquals("Waterford", g.getlist()[0].getName());
		assertFalse(g.getlist()[1].getName()  == "Cork");
		
	}
	
	
	@Test
	public void addRoadTest() {
		
		setUp();
		
				
		assertEquals(60, g.getMatrix()[0][1]);
		assertEquals(20, g.getMatrix()[2][3]);
		assertEquals(1000000, g.getMatrix()[2][1]); //direct road from Dungarvan to Cork does not exist.
		
		
	}
	

}
