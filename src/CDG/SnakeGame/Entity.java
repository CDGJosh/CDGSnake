package CDG.SnakeGame;
import java.util.*;
public class Entity
{
	private int x;
	private int y;
	
	@SuppressWarnings("rawtypes")
	public List<Entity> getIntersectingObjects(java.lang.Class c)
	{
		return getWorld().getObjectsAt(x,y,c);
	}
	
	@SuppressWarnings("rawtypes")
	public Entity getOneIntersectingObject(java.lang.Class c)
	{
		List<Entity> e = getWorld().getObjectsAt(x,y,c);
		if(e.size() < 1)
			return null;		
		return getWorld().getObjectsAt(x,y,c).get(0);
	}
	
	public void act()
	{
		
	}
	
	public SnakeWorld getWorld()
	{
		return WorldManager.getWorld();
	}
	
	
	public void setLocation(int newX, int newY)
	{
		getWorld().setEntityLocation(this, newX, newY);
	}
	
	public int getX()
	{
		return x;
	}
	
	public final void setX(int newX)
	{
		x = newX;
	}
	
	public int getY()
	{
		return y;
	}
	
	public final void setY(int newY)
	{
		y = newY;
	}
}
