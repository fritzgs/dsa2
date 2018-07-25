package dsa2.datastructures;

import dsa2.Road;

public class PriorityQueue {
	private final int SIZE= 20;
	private Road[] queueArr;
	private int size;
	
	public PriorityQueue()
	{
		queueArr = new Road[SIZE];
		size = 0;
	}
	
	public void insert(Road item)
	{
		int i;
		
		for(i=0; i< size; i++)
			if(item.getDistance() >= queueArr[i].getDistance())
				break;
		
		for(int j= size-1; j>=i; j--)
			queueArr[j+1] = queueArr[j];
		
		queueArr[i] = item;
		size++;
	}
	
	public Road removeMin()
	{
		return queueArr[--size];
	}
	
	public void remove(int a)
	{
		for (int i = a; i<size-1; i++)
		{
			queueArr[i] = queueArr[i+1];
			size--;
		}
	}
	
	public Road peekMin()
	{
		return queueArr[size-1];
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public Road peek(int a)
	{
		return queueArr[a];
	}
	
	public int find(int findTown)
	{
		for (int i=0; i<size; i++)
		{
			if(queueArr[i].getDestTown() == findTown)
				return i;
		}
		
		return -1;
	}
	
	
}
