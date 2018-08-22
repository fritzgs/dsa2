package dsa2;

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
		adjacentMtx[index1][index2] = 1;
		adjacentMtx[index2][index1] = 1;
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
			if(adjacentMtx[t][i] == 1 && townList[i].getWasChecked()==false)
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
	
	public void getDistance(Town town1, Town town2)
	{
		
	}
	
	
	public void path()
	{
		int startTree = 0;
		
		townList[startTree].setIsInTree(true);
		
		nTree = 1;
		
		//transfer row of distances from adjacentMtx to sPath
		for(int i=0; i<currentNum; i++)
		{
			int tempDist = adjacentMtx[startTree][i];
			sPath[i] = new DistanceParent(startTree, tempDist);
		}
		
		//until all towns are in the tree.
		while(nTree < currentNum)
		{
			int indexMin = getMin();
			int minDist = sPath[indexMin].getDistance();
			
			if(minDist == INFINITY) //if all infinite or in tree
			{
				System.out.println("Unreachable town");
				break;
			}
			else
			{
				currentTown = indexMin;
				startToCurrent = sPath[indexMin].getDistance();
			}
			
			//put current town in tree
			townList[currentTown].setIsInTree(true);
			nTree++;
			adjustSPath();
		}
		
		displayPaths();
		
		nTree = 0;
		
		for(int i=0; i < currentNum; i++)
		{
			townList[i].setIsInTree(false);
		}
		
	}
	
	public int getMin()
	{
		int minDist = INFINITY;
		
		int indexMin = 0;
		
		for(int i = 1; i < currentNum; i++)
		{
			if(!townList[i].getIsInTree() && sPath[i].getDistance() < minDist)
			{
				minDist = sPath[i].getDistance();
				indexMin = i;
			}
		}
		
		return indexMin;
	}
	
	public void adjustSPath()
	{
		int column = 1;
		while(column < currentNum)
		{
			//if this column's town is already in the tree, skip it
			if(townList[column].getIsInTree())
			{
				column++;
				continue;
			}
			
			//cqlculate distance for one sPath entry
			//get road from currentTown to column.
			int currentToFringe = adjacentMtx[currentTown][column];
			
			//add distance from start
			int startToFringe = startToCurrent + currentToFringe;
			
			//get distance of current sPath entry.
			int sPathDist = sPath[column].getDistance();
			
			//compare distance from start with sPath entry
			if(startToFringe < sPathDist)
			{
				sPath[column].setParentTown(currentTown);
				sPath[column].setDistance(startToFringe);	
			}
			
			column++;
		}
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
