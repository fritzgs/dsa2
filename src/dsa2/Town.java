package dsa2;

public class Town {
	private String name;
	private boolean wasChecked;
	private boolean isInTree;
	
	public Town(String name)
	{
		this.name = name;
		this.wasChecked = false;
		this.isInTree = false;
		
	}

	public boolean getIsInTree()
	{
		return this.isInTree;
	}
	
	public void setIsInTree(boolean b)
	{
		this.isInTree = b;
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
