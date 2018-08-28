package dsa2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import dsa2.datastructures.*;


public class Graph {
	private final int MAX_TOWNS = 1000;
	private Town townList[];
	private int adjacentMtx[][];
	private int currentNum;
	private Stack<Integer> stack;
	
	private int currentTown;
	private int nTree;
	private final int INFINITY = 1000000;
	private PriorityQueue priorityQueue;
	
	private DistanceParent sPath[];
	private int startToCurrent;
	
	
	public Graph()
	{
		townList = new Town[100];
		adjacentMtx = new int[MAX_TOWNS][MAX_TOWNS];
		currentNum = 0;
		nTree = 0;
		
		for(int i = 0; i < MAX_TOWNS; i++)
			for(int j =0; j < MAX_TOWNS; j++)
				adjacentMtx[i][j] = INFINITY;
		
		stack = new Stack<>();
		
		priorityQueue = new PriorityQueue();
		
		sPath = new DistanceParent[MAX_TOWNS];
		
	}
	
	public void addRoad(Town town1, Town town2, int distance)
	{
		
		int index1 = 0;
		int index2 = 0;
		
		for (int i = 0; i < townList.length; i++)
		{
			if(townList[i] != null)
			{
				if (townList[i].equals(town1))
				{
//					System.out.println(i);
					index1 = i;
				}
				
				if (townList[i].equals(town2))
				{
//					System.out.println(i);

					index2 = i;
				}
			}
			
		}
		
//		System.out.println("----");
		adjacentMtx[index1][index2] = distance;
		adjacentMtx[index2][index1] = distance;
	}
	
	public void addTown(Town t)
	{
		townList[currentNum++] = t;
		addRoad(t, t, 0);
	}
	
	public void displayTown(int index)
	{
		System.out.println(townList[index].getName());
	}
	
	public int getAdjacentUnvisitedTown(int t)
	{
//		System.out.println(t);
//		System.out.println(adjacentMtx[0][0]);
		for(int i=0; i < currentNum; i++)
		{
			if(adjacentMtx[t][i] != INFINITY && townList[i].getWasChecked()==false)
			{
				return i;
			}
		}
		
		return -1;
	}
	

/*
 * Method of traversing the graph: Depth first search - going through tree data structure by accessing the root node first.
 */
	public void dfsearch()
	{
		townList[0].setCheck(true);
		displayTown(0);
		stack.push(0);
		
		while(!stack.isEmpty()) //loop while stack is isn't empty
		{
			//get an unvisited town adjacent to stack top.
			int v = getAdjacentUnvisitedTown(stack.peek());
//			System.out.println(v);
			if(v==-1)
			{
				stack.pop();
			}
			else
			{
				townList[v].setCheck(true);
				stack.push(v);
				displayTown(v);
			}
		}
		
		//reset the flag to false.
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
			int v = getAdjacentUnvisitedTown(stack.peek());
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
	
	public int getDistance(String town1, String town2)
	{
		int row = 0;
		int col = 0;
		for(int i = 0; i < currentNum; i++)
		{
			if(townList[i].getName().toLowerCase().equals(town1.toLowerCase()))
			{
				row = i;
			}
			else if (townList[i].getName().toLowerCase().equals(town2.toLowerCase()))
			{

				col = i;
			}
		}
		
		return adjacentMtx[row][col];
	}
	
	
	public int[] getShortestRoute(String start, String dest)
	{
		//Dijstra's Algorithm
		//Put all unvisited towns into a set
		//set start town to have value = 0
		//use getMin() to find the lowest value that's unvisited
		//use return of getMin() as the currentTown
		//find adjacent of currentTown and give new values
		//Remove currentTown from unvisited 
		//Repeat 3,4,5,6
		
		
		ArrayList<Integer> unvisited = new ArrayList<>();
		int startIndex = 0;
		int endIndex = 0;
		int prev[] = new int[currentNum];
		for(int i=0; i < currentNum; i++)
		{
			unvisited.add(i);
			prev[i] = -1;
			if(townList[i].getName().toLowerCase().equals(start.toLowerCase()))
				startIndex = i;
			else if(townList[i].getName().toLowerCase().equals(dest.toLowerCase()))
				endIndex = i;
		}
		
		
		townList[startIndex].setValue(0);
		
		
		while(townList[endIndex].getWasChecked() == false)
		{
			currentTown = getMin(unvisited);
			ArrayList<Integer> adj = getAdjacent(currentTown);
			for (int town : adj)
			{
				if(townList[town].getValue() > townList[currentTown].getValue() + adjacentMtx[currentTown][town])
				{
					townList[town].setValue(townList[currentTown].getValue() + adjacentMtx[currentTown][town]);
					prev[town] = currentTown;
				}
			}
			
			townList[currentTown].setCheck(true);
			unvisited.remove(unvisited.indexOf(currentTown));
		
		}
		
		
		

		while(prev[endIndex] != -1)
		{
			stack.addSize();
			stack.push(endIndex);
			endIndex = prev[endIndex];
		}

		stack.push(startIndex);

		
		int[] path = new int[stack.size()];
		for(int j =0; j < stack.size(); j++)
		{
			path[j] = stack.pop();
			
		}
		
//		System.out.println(x);

		return path;
	}
	
	public void getShortestRouteAvoid(String start, String dest, String[] avoid)
	{
		//Dijstra's Algorithm
		//Put all unvisited towns into a set
		//set start town to have value = 0
		//use getMin() to find the lowest value that's unvisited
		//use return of getMin() as the currentTown
		//find adjacent of currentTown and give new values
		//Remove currentTown from unvisited 
		//Repeat 3,4,5,6
		
		
		ArrayList<Integer> unvisited = new ArrayList<>();
		int startIndex = 0;
		int endIndex = 0;
		int prev[] = new int[currentNum];
		for(int i=0; i < currentNum; i++)
		{
			unvisited.add(i);
			prev[i] = -1;
			if(townList[i].getName().toLowerCase().equals(start.toLowerCase()))
				startIndex = i;
			else if(townList[i].getName().toLowerCase().equals(dest.toLowerCase()))
				endIndex = i;
		}

		townList[startIndex].setValue(0);
		
		while(townList[endIndex].getWasChecked() == false)
		{
			currentTown = getMin(unvisited);
			ArrayList<Integer> adj = getAdjacent(currentTown);
			for (int town : adj)
			{

				for(String av : avoid)
				{
					if(!townList[town].getName().toLowerCase().equals(av.toLowerCase()))
					{
						if(townList[town].getValue() > townList[currentTown].getValue() + adjacentMtx[currentTown][town])
						{
//							System.out.println(town);
//							System.out.println("");
							townList[town].setValue(townList[currentTown].getValue() + adjacentMtx[currentTown][town]);
							prev[town] = currentTown;
						}
					}
					else
					{
						townList[town].setValue(INFINITY);
					}
				}
			}
			
			townList[currentTown].setCheck(true);
			unvisited.remove(unvisited.indexOf(currentTown));
		
		}

		while(prev[endIndex] != -1)
		{
			stack.addSize();
			stack.push(endIndex);
			endIndex = prev[endIndex];
		}

		stack.push(startIndex);

		int[] path = new int[stack.size()];
		for(int j =0; j < stack.size(); j++)
		{
			path[j] = stack.pop();
			
		}
		
		System.out.println(townList[path[0]].getName() + " to " + townList[path[path.length-1]].getName());
		
		if(path.length > 2)
			System.out.println("\nvia the following towns: \n");
		
		for (int t: path)
		{
			if(t != path[0] && t != path[path.length-1])
				System.out.println(townList[t].getName());
//			if(if path[path.length-1] )
		}
		
		System.out.println("\nTotal Distance = "+ townList[path[path.length-1]].getValue() + " kilometers");
		
	}
	
	public int getMin(ArrayList<Integer> arr)
	{
		int min = 0;
		ArrayList<Integer> values = new ArrayList<>();
		for (int i : arr)
		{
			values.add(townList[i].getValue());
			
		}
		
		Collections.sort(values);
		
		for (int j : arr)
		{
			if(values.get(0) == townList[j].getValue())
			{
				min = j;
			}
		}
		
		return min;
		
	}
	
	
	public ArrayList<Integer> getAdjacent(int t)
	{
		ArrayList<Integer> adj = new ArrayList<>();
		for(int i = 0; i < currentNum;i++)
		{

			if(adjacentMtx[t][i] != INFINITY)
			{
				adj.add(i);
			}
		}
		
		return adj;
	}
	
	
	public void displayPaths()
	{
		for (int i=0; i < currentNum; i++)
		{
			System.out.println(townList[i].getName() + "= ");
			if (sPath[i].getDistance() == INFINITY)
			{
				System.out.println("infinite");
			}
			else
			{
				System.out.println(sPath[i].getDistance());
			}
			
			String parent = townList[sPath[i].getParentTown()].getName();
			System.out.println("(" + parent + ")");
		}
	}
	
	public Town[] getlist()
	{
		return townList;
	}
	
	public int[][] getMatrix()
	{
		return adjacentMtx;
	}
	
	
	

}
