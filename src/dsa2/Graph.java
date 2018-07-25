package dsa2;

import dsa2.stack.Stack;


public class Graph {
	private final int MAX_TOWNS = 1000;
	private Town townList[];
	private int adjacentMtx[][];
	private int currentNum;
	private Stack<Integer> stack;
	
	public Graph()
	{
		townList = new Town[100];
		adjacentMtx = new int[MAX_TOWNS][MAX_TOWNS];
		currentNum = 0;
		for(int i = 0; i < MAX_TOWNS; i++)
			for(int j =0; j < MAX_TOWNS; j++)
				adjacentMtx[i][j] = 0;
		
		stack = new Stack<>();
	}
	
	public void addRoad(String town1, String town2)
	{
		
		int index1 = 0;
		int index2 = 0;
		
		for (int i = 0; i < townList.length; i++)
		{
			if(townList[i] != null)
			{
				if (townList[i].getName().equals(town1))
				{
					index1 = i;
				}
				
				if (townList[i].getName().equals(town2))
				{
					index2 = i;
				}
			}
			
		}
		
		adjacentMtx[index1][index2] = 1;
		adjacentMtx[index2][index1] = 1;
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
		townList[0].setCheck(true);
		displayTown(0);
		stack.push(0);
		
		while(!stack.isEmpty()) //loop while stack is isn't empty
		{
			//get an unvisited town adjacent to stack top.
			int v = getAdjacentUnvisitedTown(stack.peek());
			if(v==-1)
			{
				stack.pop();
			}
			else
			{
				townList[v].setCheck(true);
				stack.push(v);
				displayTown(v);
//				System.out.println(" ");
			}
		}
		
		for(int i = 0; i < currentNum; i++)
		{
			townList[i].setCheck(false);
		}
		
	}
	
	public void mstree() //based on DFS
	{
		townList[0].setCheck(true);
		
		stack.push(0);
		
		while(!stack.isEmpty()) //loop while stack is isn't empty
		{
			int currentTown = stack.peek();
			//get an unvisited town adjacent to stack top.
			int v = getAdjacentUnvisitedTown(currentTown);
			if(v==-1)
			{
				stack.pop();
			}
			else
			{
				townList[v].setCheck(true);
				stack.push(v);
				displayTown(currentTown);
				displayTown(v);
				System.out.println(" ");
			}
		}
		
		for(int i = 0; i < currentNum; i++)
		{
			townList[i].setCheck(false);
		}
		
	}
	
	public Town[] getlist()
	{
		return townList;
	}

}
