
package dsa2;

import java.util.ArrayList;
import java.util.Collections;


import dsa2.datastructures.*;

/**
 * @author fritz
 * 
 * 
 */
public class Graph {
	private final int MAX_TOWNS = 1000;
	private final int INFINITY = 1000000;
	
	private Town townList[];
	
	private int adjacentMtx[][];
	private int currentNum;	
	private int currentTown;
		
	/**
	 *Constructor
	 *Creates the townList array and matrix of the distances between connected towns
	 */
	public Graph()
	{
		townList = new Town[100];
		adjacentMtx = new int[MAX_TOWNS][MAX_TOWNS];
		currentNum = 0;
		
		for(int i = 0; i < MAX_TOWNS; i++)
			for(int j =0; j < MAX_TOWNS; j++)
				adjacentMtx[i][j] = INFINITY;
	}
	
	/**
	 * Adds connections between towns.
	 * Saves the distances of the connected towns into the matrix.
	 * @param town1
	 * @param town2
	 * @param distance
	 */
	public void addRoad(String town1, String town2, int distance)
	{
		boolean town1ex = false;
		boolean town2ex = false;
		for(int j=0; j <townList.length; j++)
		{
			if(townList[j] != null)
			{
				if(townList[j].getName().toLowerCase().equals(town1.toLowerCase()))
				{
					town1ex = true;
				}
				if(townList[j].getName().toLowerCase().equals(town2.toLowerCase()))
				{
					town2ex = true;
				}
			}
		}
		
		if(town1ex == true && town2ex == true)
		{
			int index1 = 0;
			int index2 = 0;
			
			//gets the real index of two towns inserted
			for (int i = 0; i < townList.length; i++)
			{
				if(townList[i] != null)
				{
					if (townList[i].getName().toLowerCase().equals(town1.toLowerCase()))
					{
						index1 = i;
					}
					
					if (townList[i].getName().toLowerCase().equals(town2.toLowerCase()))
					{
						index2 = i;
					}
				}

			}
			
			//adds the distance of the two towns into matrix.
			adjacentMtx[index1][index2] = distance;
			adjacentMtx[index2][index1] = distance;
		}
		else
		{
			System.out.println("One or two of the town names are incorrect - does not exist in list");
		}
		
	}
	
	/**
	 * Add the town to the town list.
	 * @param Town object t
	 */
	public void addTown(Town t)
	{
		townList[currentNum++] = t;
		addRoad(t.getName(), t.getName(), 0);
	}
	
	
	/**
	 * Returns the first town that's adjacent to the index specified and that is has not been checked before.
	 * @param t
	 * @return int index of adjacent town or -1 if none.
	 */
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
	

	/**
	 *  Method of traversing the graph: Depth first search. 
	 *  Going through tree data structure by accessing the root node first.
	 *  
	 *  ---
	 *  This method will print out all the nodes/towns that exist in the graph.
	 */
	public void getAllTowns()
	{
		Stack<Integer> stack = new Stack<>();
		if(townList[0] == null)
		{
			System.out.println("No Towns");
		}
		else 
		{
			//Include first node (root node)
			townList[0].setCheck(true);
			System.out.println(townList[0]);
			stack.push(0);
			
			while(!stack.isEmpty()) //loop while stack is isn't empty
			{
				//get an unvisited town adjacent to stack top.
				int v = getAdjacentUnvisitedTown(stack.peek());
				if(v==-1)
				{
					stack.pop(); //no connections (single node in graph)
				}
				else
				{
					//set the adjacent as checked and add into stack of nodes in graph.
					townList[v].setCheck(true);
					stack.push(v);
					System.out.println(townList[v]);
				}
			}
			
			//reset the flag to false.
			for(int i = 0; i < currentNum; i++)
			{
				townList[i].setCheck(false);
			}
		}
	}
	
	/**
	 * Minimal Spanning Tree (unweighted).
	 * This method will print out all the connected towns.
	 */
	public void getAllConnected() //based on DFS
	{
		Stack<Integer> stack = new Stack<>();
		if(townList[0] != null)
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
					System.out.println(townList[currentTown] + " <-> " + townList[v]);
					System.out.println("Distance: " + adjacentMtx[currentTown][v] + " km");
					System.out.println(" ");
				}
			}
			
			for(int i = 0; i < currentNum; i++)
			{
				townList[i].setCheck(false);
			}
		}
		else
		{
			System.out.println("No Towns");
		}
		
	}
	
	/**
	 * returns the distance of two towns specified.
	 * @param town1
	 * @param town2
	 * @return
	 */
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
	
	/**
	 * Dijkstra's Algorithm - shortest path from source to destination.
	 * Put all unvisited towns into a set - 
	 * Set start town to have value = 0 - 
	 * Use getMin() to find the lowest value that's unvisited -
	 * Use return of getMin() as the currentTown - 
	 * Find adjacent of currentTown and give new values - 
	 * Remove currentTown from unvisited - 
	 * Repeat 3,4,5,6
	 * 
	 * @param start
	 * @param dest
	 */
	public void getShortestRoute(String start, String dest)
	{
		Stack<Integer> stack = new Stack<>();
		//the data set of unvisited - this arraylist is used to find the min.
		ArrayList<Integer> unvisited = new ArrayList<>();
		int startIndex = 0;
		int endIndex = 0;
		//int array to keep track of the towns in route of the shorted path.
		int prev[] = new int[currentNum];
		//get the actual index of the source and destination towns in the town list.
		for(int i=0; i < currentNum; i++)
		{
			unvisited.add(i); //adds all the towns in array to unvisited arraylist (all towns initially unvisited).
			prev[i] = -1;
			if(townList[i].getName().toLowerCase().equals(start.toLowerCase()))
				startIndex = i;
			else if(townList[i].getName().toLowerCase().equals(dest.toLowerCase()))
				endIndex = i;
		}
		
		
		//set the value of the source town to 0
		townList[startIndex].setValue(0);
		
		//loop until the destination has been checked.
		while(townList[endIndex].getWasChecked() == false)
		{
			currentTown = getMin(unvisited); //the lowest value adjacent town
			ArrayList<Integer> adj = getAdjacent(currentTown); //all the adjacent towns of the variable above
			for (int town : adj) //loop through each int object in arraylist.
			{
				//change the value of town if the route from currentTown is less than what has been previously stated.
				if(townList[town].getValue() > townList[currentTown].getValue() + adjacentMtx[currentTown][town])
				{
					townList[town].setValue(townList[currentTown].getValue() + adjacentMtx[currentTown][town]);
					prev[town] = currentTown; //make note that currentTown is in path.
				}
			}
			
			townList[currentTown].setCheck(true);
			unvisited.remove(unvisited.indexOf(currentTown)); //remove town from unvisited array.
		
		}
		
		//pushes all the towns that are in shortest path to stack.
		while(prev[endIndex] != -1) //will loop until the source index (prev[source] == -1)
		{
			stack.addSize();
			stack.push(endIndex);
			endIndex = prev[endIndex]; //assigns the next to check as the previous index in array.

		}

		stack.push(startIndex);
		
		//add the indexes into int array.
		int[] path = new int[stack.size()];
		for(int j =0; j < stack.size(); j++)
		{
			path[j] = stack.pop();
			
		}	
		
		System.out.println(townList[path[0]].getName() + " to " + townList[path[path.length-1]].getName());
		
		if(path.length > 2)
			System.out.println("\nvia the following towns: \n");
		
		//print all the towns that will have to be visited on the way to destination
		for (int t: path)
		{
			if(t != path[0] && t != path[path.length-1])
				System.out.println(townList[t].getName());
		}
		System.out.println("\nTotal Distance = "+ townList[path[path.length-1]].getValue() + " kilometers");
		
		
		for(Town t : townList)
		{
			if(t != null)
			{
				t.setCheck(false);
				t.setValue(INFINITY);
			}
		}
	}
	
	/**
	 * Dijkstra's Algorithm - shortest path from source to destination with choice of avoiding towns along the way
	 * Put all unvisited towns into a set - 
	 * Set start town to have value = 0 - 
	 * Use getMin() to find the lowest value that's unvisited -
	 * Use return of getMin() as the currentTown - 
	 * Find adjacent of currentTown and give new values - 
	 * Remove currentTown from unvisited - 
	 * Repeat 3,4,5,6
	 * 
	 * @param start
	 * @param dest
	 */
	public void getShortestRouteAvoid(String start, String dest, String[] avoid)
	{			
		Stack<Integer> stack = new Stack<>();
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
					//continue as normal if the current town isn't one of the towns trying to avoid
					if(!townList[town].getName().toLowerCase().equals(av.toLowerCase()))
					{
						if(townList[town].getValue() > townList[currentTown].getValue() + adjacentMtx[currentTown][town])
						{
							townList[town].setValue(townList[currentTown].getValue() + adjacentMtx[currentTown][town]);
							prev[town] = currentTown;
						}
					}
					else //keep the value to infinity so that it will be longer path.
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
		}
		
		System.out.println("\nTotal Distance = "+ townList[path[path.length-1]].getValue() + " kilometers");
		
		
		for(Town t : townList)
		{
			if(t != null)
			{
				t.setCheck(false);
				t.setValue(INFINITY);
			}
		}
	}
	
	/**
	 * Get the unvisited town adjacent that has the lowest value
	 * @param arr
	 * @return
	 */
	public int getMin(ArrayList<Integer> arr)
	{
		int min = 0;
		ArrayList<Integer> values = new ArrayList<>();
		for (int i : arr)
		{
			values.add(townList[i].getValue());
			
		}
		
		//sort the indexes values from lowest to highest.
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
	
	/**
	 * Get all the towns that are adjacent to the specified index.
	 * @param t
	 * @return arraylist of all the towns indexes that are adjacent.
	 */
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
	
	/**
	 * Get the list of all the towns
	 * @return Town array
	 */
	public Town[] getlist()
	{
		return townList;
	}
	
	/**
	 * Gets the matrix of connected towns' distances.
	 * @return int[][]
	 */
	public int[][] getMatrix()
	{
		return adjacentMtx;
	}

}
