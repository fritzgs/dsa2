package dsa2;

public class Town {
	private String name;
	private boolean wasChecked;
	
	public Town(String name)
	{
		this.name = name;
		this.wasChecked = false;
	}

	public String getName()
	{
		return this.name;
	}
	
	public boolean getWasChecked()
	{
		return this.wasChecked;
	}
	
	public void setCheck(boolean b)
	{
		this.wasChecked = b;
	}

}
