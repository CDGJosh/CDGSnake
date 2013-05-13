package CDG.SnakeGame;
import java.io.*;
import java.util.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import CDG.Game.Menu.Menu;

@SuppressWarnings("unused")
public class Main 
{
	public final int DIF_ASIAN = 2;
	public final int DIF_HARD = 4;
	public final int DIF_CLASSIC = 6;
	public final int DIF_NORMAL = 8;
	public final int DIF_EASY = 10;
	public final int DIF_LAME = 12;
	public final int DIF_SLEEP = 14;
	private boolean interrupted = false;
	private boolean pause = false;
	private int actCount = 0;
	private boolean p_down = false;
	private boolean esc_down = false;
	Menu gameMenu;
	//private int[] textures;
	private int[] lkeys = new int[2];
	private Texture[] textures;
	private String[] textureFiles = {"body_texture_0.png","body_texture_1.png","body_texture_2.png","body_texture_3.png",
									 "end_texture_0.png","end_texture_1.png","end_texture_2.png","end_texture_3.png",
									 "head_texture_0.png","head_texture_1.png","head_texture_2.png","head_texture_3.png",
									 "apple.png", "cake.png"};
	
	private void init()
	{
		WorldManager.setWorld(new SnakeWorld(40,30,20));
		try
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Display.setTitle("CDGSnake");
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		try
		{
			textures = new Texture[textureFiles.length];
			loadTextures();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		gameMenu = new Menu();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glOrtho(0,800,600,0,-1,1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		initWorldObjects();
	}
	
	private void initWorldObjects()
	{
		for(int y = 0; y < WorldManager.getWorld().getHeight(); y++)
		{
			for(int x = 0; x < WorldManager.getWorld().getWidth(); x++)
			{
				if(y == 0 || y == WorldManager.getWorld().getHeight()-1 || x == 0 || x == WorldManager.getWorld().getWidth()-1)
				{
					Wall w = new Wall();
					WorldManager.getWorld().addObject(w, x, y);
					System.out.println("Creating wall @"+x+"/"+y);
				}
			}
		}
		SnakeHead h = new SnakeHead();
		WorldManager.getWorld().addObject(h,new Random().nextInt(WorldManager.getWorld().getWidth()-11)+10,new Random().nextInt(WorldManager.getWorld().getHeight()-11)+10);
		WorldManager.getWorld().spawnFood();
	}
	
	private void restartGame()
	{
		WorldManager.resetKeys();
		WorldManager.setWorld(new SnakeWorld(40,30,20));
		initWorldObjects();
	}
	
	private void renderGameFrame()
	{
		
		List<Entity> objects = WorldManager.getWorld().getObjects(null);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glOrtho(0,800,600,0,-1,1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		
		
		for(int i = 0; i < objects.size(); i++)
		{
			if(objects.get(i).getClass() == Wall.class)
			{
				GL11.glColor3f(0.65f, 0.65f, 0.65f);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			}
			else if(objects.get(i).getClass() == SnakeHead.class)
			{
				int dir = ((SnakeHead)objects.get(i)).getDirection();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[dir+8].getTextureID());
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			else if(objects.get(i).getClass() == SnakeBody.class)
			{
				boolean last = ((SnakeBody)objects.get(i)).isLast();
				int dir = ((SnakeBody)objects.get(i)).getDirection();
				if(last)
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[dir+4].getTextureID());
				else
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[dir].getTextureID());
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			else
			{
				if(StaticManager.isEasterEgg())
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[13].getTextureID());
				else
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[12].getTextureID());
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0.0f, 0.0f);
				GL11.glVertex2i(((objects.get(i).getX())*WorldManager.getWorld().getTileSize()), ((objects.get(i).getY())*WorldManager.getWorld().getTileSize()));
				GL11.glTexCoord2f(1.0f, 0.0f);
				GL11.glVertex2i(((objects.get(i).getX())*WorldManager.getWorld().getTileSize())+WorldManager.getWorld().getTileSize(), ((objects.get(i).getY())*WorldManager.getWorld().getTileSize()));
				GL11.glTexCoord2f(1.0f, 1.0f);
				GL11.glVertex2i(((objects.get(i).getX())*WorldManager.getWorld().getTileSize())+WorldManager.getWorld().getTileSize(), ((objects.get(i).getY())*WorldManager.getWorld().getTileSize())+WorldManager.getWorld().getTileSize());
				GL11.glTexCoord2f(0.0f, 1.0f);
				GL11.glVertex2i(((objects.get(i).getX())*WorldManager.getWorld().getTileSize()), ((objects.get(i).getY())*WorldManager.getWorld().getTileSize())+WorldManager.getWorld().getTileSize());
				
			GL11.glEnd();
			
		}
		
	}
	
	private void loadTextures() throws IOException
	{
		
		for(int i = 0; i < textureFiles.length; i++)
		{
			Texture t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/"+textureFiles[i]));
			textures[i] = t;
		}
	}
	
	private boolean checkExitKeys()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
	}
	
	private void checkPause()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_P) && !p_down)
		{
			pause = !pause;
			p_down = true;
		}
		else if(!Keyboard.isKeyDown(Keyboard.KEY_P))
		{
			p_down = false;
		}
	}
	
	private void loop()
	{
		init();
		Keyboard.enableRepeatEvents(true);
		while(!interrupted)
		{
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			
			if(StaticManager.getRestartGame())
			{
				StaticManager.setRestartGame(false);
				restartGame();
			}
			
			if(!StaticManager.isInMenu())
			{
				checkPause();
				if(Keyboard.getNumKeyboardEvents() > 0)
				{
					
					while(Keyboard.next())
					{
						if(StaticManager.isWasd())
						{
							char key = Keyboard.getEventCharacter();
							if(key == 'w')
								WorldManager.setKey('w',true);
							else if(key == 'd')
								WorldManager.setKey('d',true);
							else if(key == 's')
								WorldManager.setKey('s',true);
							else if(key == 'a')
								WorldManager.setKey('a',true);
						}
						else
						{
							int key = Keyboard.getEventKey();
							boolean done = false;
							if((key != lkeys[0] && key != lkeys[1]) || (lkeys[0] == lkeys[1] && lkeys[0] == key))
							{
								if(key == Keyboard.KEY_UP)
									WorldManager.setKey('w',true);
								else if(key == Keyboard.KEY_RIGHT)
									WorldManager.setKey('d',true);
								else if(key == Keyboard.KEY_DOWN)
									WorldManager.setKey('s',true);
								else if(key == Keyboard.KEY_LEFT)
									WorldManager.setKey('a',true);
								done = true;
							}
							System.out.println(key+"/"+lkeys[0]+"/"+lkeys[1]+"/"+done);
							lkeys[0] = lkeys[1];
							if(done)
							{
								lkeys[1] = key;
							}
							else
							{
								lkeys[1] = 0;
							}
						}
					}
					
				}
				
				
				
				
				if(actCount == StaticManager.getDifficulty())
				{
					if(!pause)
					{
						List<Entity> objects = WorldManager.getWorld().getObjects(null);		
						for(int i = 0; i < objects.size(); i++)
						{
							objects.get(i).act();
						}
					}
					actCount = 0;
				}
				actCount++;
				renderGameFrame();
			}
			else
			{
				System.out.println("LOOP");
				gameMenu.renderMenu();
			}
			
			Display.sync(60);			
			interrupted = Display.isCloseRequested();
			Display.update();
			
		}
	}
	
	public static final void main(String[] argv)
	{
		new Main().loop();
	}
}
