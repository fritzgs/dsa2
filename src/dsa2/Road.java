package dsa2;

public class Road {
	private int srcTown;
	private int destTown;
	private int distance;
	
	public Road(int src, int dest, int dist)
	{
		this.srcTown = src;
		this.destTown = dest;
		this.distance = dist;
	}
	
	public int getSrcTown()
	{
		return srcTown;
	}
	
	public int getDestTown()
	{
		return destTown;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	

}
