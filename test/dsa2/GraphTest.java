package dsa2;

import dsa2.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class GraphTest {

	Graph g;
	Town wat;
	Town dun;
	Town mid;
	Town cor;
	
	@Before
	public void setUp() 
	{
		g = new Graph();
		wat = new Town("Waterford");
		dun = new Town("Dungarvan");
		mid = new Town("Middleton");
		cor = new Town("Cork");
	
		g.addTown(wat);
		g.addTown(dun);
		g.addTown(cor);
		g.addTown(mid);
		
		g.addRoad(wat, dun, 60);
		g.addRoad(dun, mid, 60);
		g.addRoad(mid, cor, 20);
		
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
	
	@Test
	public void getMinTest()
	{
		setUp();
//		g.path();
		
	}
	

}
