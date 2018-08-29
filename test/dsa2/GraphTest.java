package dsa2;

import dsa2.*;

import static org.junit.jupiter.api.Assertions.*;
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
		
		g.addRoad(wat, dun, 40);
		g.addRoad(mid, dun, 50);
		g.addRoad(mid, cork, 20);
		g.addRoad(mid, cobh, 30);
		g.addRoad(wat, caros, 50);
		g.addRoad(dun, caros, 25);
		g.addRoad(caros, cork, 20);
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
		assertEquals(40, g.getMatrix()[0][1]);
		assertEquals(20, g.getMatrix()[2][3]);
		assertEquals(1000000, g.getMatrix()[2][1]); //direct road from Dungarvan to Cork does not exist.	
	}
	
	//The following are just for checking whether the void methods are running as expected.
	@Test
	public void testGetAllTowns()
	{
		System.out.println("---GET ALL TOWNS---");
		setUp();
		g.getAllTowns();
		System.out.println();
		System.out.println();
	}
	
	@Test
	public void testGetAllConnected()
	{
		System.out.println("---GET ALL CONNECTED TOWNS---");
		setUp();
		g.getAllConnected();
		System.out.println();
		System.out.println();	
	}
	
	@Test
	public void testShortestRoute()
	{
		System.out.println("---SHORTEST ROUTE---");
		setUp();
		g.getShortestRoute("waterford", "Cork");;
		System.out.println();
		System.out.println();	
	}
	
	@Test
	public void testShortestRouteAvoid()
	{
		System.out.println("---SHORTEST ROUTE WITH AVOIDING TOWNS---");
		setUp();
		String[] avoid = {"carrick-on-suir"};
		g.getShortestRouteAvoid("waterford", "Cork", avoid);;
		System.out.println();
		System.out.println();	
	}
}
