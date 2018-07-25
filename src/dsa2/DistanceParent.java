package dsa2;

public class DistanceParent {
	private int distance;
	private int parentTown;
	
	public DistanceParent(int parentTown, int distance)
	{
		this.distance = distance;
		this.parentTown = parentTown;
	}
	
	public int getDistance()
	{
		return this.distance;
	}
	
	public int getParentTown()
	{
		return this.parentTown;
	}

	public void setParentTown(int t)
	{
		this.parentTown = t;
	}
	
	public void setDistance(int d)
	{
		this.distance = d;
	}
	
}
