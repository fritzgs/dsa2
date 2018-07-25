package dsa2;

import dsa2.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class GraphTest {

	Graph g = new Graph();
	
	
	@Test
	public void test() {
		g = new Graph();
		g.addTown("Waterford");
		g.addTown("Dungarvan");
		g.addTown("Cork");
		g.addTown("Middleton");
		
//		for (int i =0; i < g.getlist().length; i++)
//		{
//			if (g.getlist()[i] != null)
//				System.out.println(g.getlist()[i].getName());
//		}
		
		g.addRoad("Waterford", "Dungarvan", 60);
		g.addRoad("Dungarvan", "Middleton", 60);
		g.addRoad("Middleton", "Cork", 60);
		
		
		g.dfsearch();
		
		System.out.println("-----");
		
		g.mstree();
		
	}

}
