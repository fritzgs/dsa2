package dsa2;

import dsa2.stack.Stack;


public class Graph {
	private final int MAX_TOWNS = 1000;
	private Town townList[];
	private int adjacentMtx[][];
	private int currentNum;
	
	public Graph()
	{
		townList = new Town[1000];
		adjacentMtx = new int[MAX_TOWNS][MAX_TOWNS];
		currentNum = 0;
		for(int i = 0; i < MAX_TOWNS; i++)
			for(int j =0; j < MAX_TOWNS; j++)
				adjacentMtx[i][j] = 0;
	}
	
	public void addRoad(int indexTown1, int indexTown2)
	{
		adjacentMtx[indexTown1][indexTown2] = 1;
		adjacentMtx[indexTown2][indexTown1] = 1;
	}
	
	public void addTown(String name)
	{
		townList[currentNum++] = new Town(name);
	}
	
	public void displayTown(int index)
	{
		System.out.println(townList[index].getName());
	}
	
	public int getAdjacentUnvisitedTown(int t)
	{
		for(int i=0; i < currentNum; i++)
		{
			if(adjacentMtx[t][i] == 1 && townList[i].getWasChecked()==false)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	
	public void dfsearch()
	{
		Stack<Integer> stack = new Stack<>();
		townList[0].setCheck(true);
		
		stack.push(0);
		
		while(!stack.isEmpty()) //loop while stack is isn't empty
		{
			//get an unvisited town adjacent to stack top.
			int unvisited = getAdjacentUnvisitedTown(stack.peek());
			if(unvisited==-1)
			{
				stack.pop();
			}
			else
			{
				townList[unvisited].setCheck(false);
			}
		}
		
	}
	

}
