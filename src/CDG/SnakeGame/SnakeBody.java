package CDG.SnakeGame;

public class SnakeBody extends Entity 
{
	private boolean isLast;
	private int direction;
	
	public SnakeBody(int dir)
	{
		isLast = true;
		direction = dir;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int dir)
	{
		direction = dir;
	}
	
	public boolean isLast()
	{
		return isLast;
	}
	
	public void setIsLast(boolean last)
	{
		isLast = last;
	}
}
