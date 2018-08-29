package dsa2;

public class Town {
	private String name;
	private boolean wasChecked;
	private int value;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Town(String name)
	{
		this.name = name;
		this.wasChecked = false;
		this.value = 100000; //for dijkstra's algorithm
		
	}
	
	/**
	 * Get the distance value from source to destination - used in shortest path
	 * @return int
	 */
	public int getValue()
	{
		return this.value;
	}

	/**
	 * Get the name of this town
	 * @return String
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Get whether it was checked or not
	 * @return boolean
	 */
	public boolean getWasChecked()
	{
		return this.wasChecked;
	}
	
	/**
	 * Set the check value
	 * @param b
	 */
	public void setCheck(boolean b)
	{
		this.wasChecked = b;
	}
	
	/**
	 * Set the value
	 * @param v
	 */
	public void setValue(int v)
	{
		this.value = v;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}

}
