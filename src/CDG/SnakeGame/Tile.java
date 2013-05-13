package CDG.SnakeGame;
import java.util.*;
public class Tile 
{
	private List<Entity> objects;

	public Tile()
	{
		objects = new ArrayList<Entity>();
	}
	
	public List<Entity> getObjects() 
	{
		return objects;
	}
	
	public void addObject(Entity e)
	{
		objects.add(e);
	}
	
	public void removeObject(Entity e)
	{
		objects.remove(e);
	}
}
