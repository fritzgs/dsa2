package dsa2;

import dsa2.datastructures.*;
import dsa2.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;



class PQTest {

	private PriorityQueue pq;
	private Graph g;
	
	private Road r1;
	private Road r2;
	private Road r3;
	private Road r4;
	
	private Town t1;
	private Town t2;
	private Town t3;
	private Town t4;
	
	@Before
	public void setUp()
	{
		pq = new PriorityQueue();
		g = new Graph();
		
		t1 = new Town("T1");
		t2 = new Town("T2");
		t3 = new Town("T3");
		t4 = new Town("T4");
		
		g.addTown(t1); //index 0
		g.addTown(t2); //index 1
		g.addTown(t3); //index 2
		g.addTown(t4); //index 3 --- of adjMtx.
		
		
		r1 = new Road(0, 1, 30);
		r2 = new Road(0, 2, 40);
		r3 = new Road(1, 2, 50);
		r4 = new Road(3, 2, 100);
		
		
	}
	
	@Test
	public void insertTest() {
		
		setUp();
		pq.insert(r1);
		pq.insert(r3);
		pq.insert(r2);
		
		assertEquals(3, pq.size());
		
	}
	
	@Test
	public void removeTest()
	{
		setUp();
		pq.insert(r1);
		pq.insert(r2);
		pq.insert(r3);
		
		assertEquals(3, pq.size()); //original size
		assertEquals(r1, pq.removeMin()); //remove the head
		assertEquals(2, pq.size()); //new size after removal
		
		
		
		pq.insert(r4);
		pq.remove(1); //removes r3
		
		assertEquals(2, pq.size());
		
	}
	
	
	@Test
	public void testEmpty()
	{
		setUp();
		assertTrue(pq.isEmpty());
	}
	
	@Test
	public void peekTest()
	{
		setUp();
		pq.insert(r1);
		pq.insert(r2);
		pq.insert(r3);
		
		assertEquals(r1, pq.peekMin());
		assertEquals(r3, pq.peek(0));
	}
	
	@Test
	public void findTest()
	{
		setUp();
		pq.insert(r1);
		pq.insert(r2);
		pq.insert(r3);
		
		assertEquals(2, pq.find(1)); 
		//find the index of this destination town (t2) in the queue. 
		//It is index 2 : r1 = (0, 1, 30)
	}
	
	
	
	

}
