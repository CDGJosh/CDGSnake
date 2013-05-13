package CDG.SnakeGame;

public abstract class WorldManager 
{
	private static SnakeWorld world;
	private static char lastKey;
	private static boolean[] keys = new boolean[4];
	
	public static void setWorld(SnakeWorld w)
	{
		world = w;
	}
	
	public static SnakeWorld getWorld()
	{
		return world;
	}
	
	public static void setLastKeyPressed(char key)
	{
		lastKey = key;
	}
	
	public static char getLastKeyPressed()
	{
		return lastKey;
	}
	
	public static void setKey(char c, boolean status)
	{
		int i = 0;
		switch(c)
		{
			case 'w':
				i = 0;
				break;
			case 'd':
				i = 1;
				break;
			case 's':
				i = 2;
				break;
			case 'a':
				i = 3;
				break;
		}
		keys[i] = status;
	}
	
	public static void resetKeys()
	{
		for(int i = 0; i < 4; i ++)
		{
			keys[i] = false;
		}
	}
	
	public static boolean getKey(char c)
	{
		int i = 0;
		switch(c)
		{
			case 'w':
				i = 0;
				break;
			case 'd':
				i = 1;
				break;
			case 's':
				i = 2;
				break;
			case 'a':
				i = 3;
				break;
		}
		return keys[i];
	}
}
