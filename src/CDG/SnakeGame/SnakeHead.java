package CDG.SnakeGame;
import java.util.*;
public class SnakeHead extends Entity 
{
	private int direction = 0;
	private boolean dead = false;
	private boolean addBody = false;
	private List<SnakeBody> body;
	
	public SnakeHead()
	{
		body = new ArrayList<SnakeBody>();
	}
	
	public void act()
	{
		checkDead();
		if(!dead)
		{
			int olddir = direction;
			checkDirectionChange();
			int oldx = getX();
			int oldy = getY();
			
			switch(direction)
			{
				case 0:
					setLocation(getX(),getY()-1);
					break;
				case 1:
					setLocation(getX()+1,getY());
					break;
				case 2:
					setLocation(getX(),getY()+1);
					break;
				case 3:
					setLocation(getX()-1,getY());
			}
			checkApple();
			for(int i = body.size()-1; i >= 0; i--)
			{
				if(i == -1)
				{
					break;
				}
				if(addBody)
				{
					addBody = false;
					SnakeBody b = new SnakeBody(body.get(i).getDirection());
					getWorld().addObject(b, body.get(i).getX(), body.get(i).getY());
					body.add(b);
					body.get(i).setIsLast(false);
				}
				if(i == 0)
				{
					if(body.get(i).isLast())
					{
						body.get(i).setDirection(direction);
					}
					else
					{
						body.get(i).setDirection(olddir);
					}
					body.get(i).setLocation(oldx, oldy);
					
				}
				else
				{
					body.get(i).setDirection(body.get(i-1).getDirection());
					body.get(i).setLocation(body.get(i-1).getX(), body.get(i-1).getY());					
				}
				//t++;
			}
			
			if(body.size() >= 2)
				body.get(body.size()-1).setDirection(getDir(body.get(body.size()-2),body.get(body.size()-1)));
			
			if(addBody)
			{
				addBody = false;
				SnakeBody b = new SnakeBody(direction);
				getWorld().addObject(b, oldx, oldy);
				body.add(b);
			}
		}
	}
	
	private int getDir(Entity next, Entity actual)
	{
		int xOffset = next.getX()-actual.getX();
		int yOffset = next.getY()-actual.getY();
		
		if(xOffset == -1)
		{
			return 3;
		}
		else if(xOffset == 1)
		{
			return 1;
		}
		else if(yOffset == -1)
		{
			return 0;
		}
		else if(yOffset == 1)
		{
			return 2;
		}
		else
		{
			return 1337;
		}
	}
	
	private void checkDirectionChange()
	{
		int newDir = -1;
		
		if(WorldManager.getKey('w'))
		{
			newDir = 0;
		}
		else if(WorldManager.getKey('d'))
		{
			newDir = 1;
		}
		else if(WorldManager.getKey('s'))
		{
			newDir = 2;
		}
		else if(WorldManager.getKey('a'))
		{
			newDir = 3;
		}
		WorldManager.resetKeys();
		
		if(newDir != -1)
		{
			int offset = direction - newDir;
			if(offset != 2 && offset != -2 && offset != 0)
			{
				direction = newDir;
			}
		}
	}
	
	private void checkDead()
	{
		Entity w = getOneIntersectingObject(Wall.class);
		Entity b = getOneIntersectingObject(SnakeBody.class);
		dead = (w!=null) || (b!=null);
		if(dead)
		{
			StaticManager.setScore(body.size());
			StaticManager.setActiveFrame(StaticManager.MENU_GAMEOMVER);
		}
	}
	
	private void checkApple()
	{
		Entity a = getOneIntersectingObject(Food.class);
		if(a != null)
		{
			addBody = true;
			getWorld().removeObject(a);
			getWorld().spawnFood();
		}
	}
	
	public int getDirection()
	{
		return direction;
	}

}
