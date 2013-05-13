
package CDG.SnakeGame;

import java.util.*;

public class SnakeWorld 
{
	private int width;
	private int height;
	private int tilesize;
	
	private Tile[][] tiles;
	
	public SnakeWorld(int w, int h, int t)
	{
		width = w;
		height = h;
		tilesize = t;
		tiles = new Tile[w][h];
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				tiles[x][y] = new Tile();
			}
		}
	}
	
	public void spawnFood()
	{
		Food f = new Food();
		int x = new Random().nextInt(WorldManager.getWorld().getWidth()-2)+1;
		int y = new Random().nextInt(WorldManager.getWorld().getHeight()-2)+1;
		
		while(!getObjectsAt(x,y).isEmpty())
		{
			x = new Random().nextInt(WorldManager.getWorld().getWidth()-2)+1;
			y = new Random().nextInt(WorldManager.getWorld().getHeight()-2)+1;
		}
		
		addObject(f,x,y);
	}
	
	public void addObject(Entity e, int x, int y)
	{
		tiles[x][y].addObject(e);
		e.setX(x);
		e.setY(y);
	}
	
	public void removeObject(Entity e)
	{
		tiles[e.getX()][e.getY()].removeObject(e);
	}
	
	public void setEntityLocation(Entity e, int newX, int newY)
	{
		if(newX >= 0 && newX < width && newY >= 0 && newY < height)
		{
			tiles[e.getX()][e.getY()].removeObject(e);
			tiles[newX][newY].addObject(e);
			e.setX(newX);
			e.setY(newY);
		}
	}
	
	public List<Entity> getObjectsAt(int x, int y)
	{
		return tiles[x][y].getObjects();
	}
	
	@SuppressWarnings("rawtypes")
	public List<Entity> getObjectsAt(int x, int y, java.lang.Class c)
	{
		List<Entity> ret = new ArrayList<Entity>();
		
		if(c == null)
			return tiles[x][y].getObjects();
		
		Tile t = tiles[x][y];
		
		for(int i = 0; i < t.getObjects().size(); i++)
		{
			if(t.getObjects().get(i).getClass() == c)
			{
				ret.add(t.getObjects().get(i));
			}
		}
		
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Entity> getObjects(java.lang.Class c)
	{
		List<Entity> ret = new ArrayList<Entity>();
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				Tile t = tiles[x][y];
				if(c!=null)
				{
					for(int i = 0; i < t.getObjects().size(); i++)
					{
						if(t.getObjects().get(i).getClass() == c)
						{
							ret.add(t.getObjects().get(i));
						}
					}
				}
				else
				{
					ret.addAll(t.getObjects());
				}
			}
		}
		return ret;
	}
	
	public int getTileSize()
	{
		return tilesize;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWindowWidth()
	{
		return width * tilesize;
	}
	
	public int getWindowHeight()
	{
		return height * tilesize;
	}
}
