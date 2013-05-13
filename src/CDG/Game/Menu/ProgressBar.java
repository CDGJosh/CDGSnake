package CDG.Game.Menu;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;

import CDG.SnakeGame.StaticManager;
public class ProgressBar extends MenuItem 
{
	private int value;
	private String name;
	private Texture[] parts;
	private String[] names = {"head_texture_3","body_texture_3","end_texture_3"};
	
	public ProgressBar(String fontFolder, String name, int x, int y)
	{
		this.name = name;
		setX(x);
		setY(y);
		loadParts(fontFolder);
	}
	
	private void loadParts(String fontFolder)
	{
		parts = new Texture[names.length];
		for(int i = 0; i < names.length; i++)
		{
			try 
			{
				parts[i] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fontFolder+"/"+names[i]+".png"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
			
	}
	
	public void render()
	{
		System.out.println("SoundVal: "+StaticManager.getSoundVolume());
		if(name.equals("sound_pb"))
			value = StaticManager.getSoundVolume();
		int xoff = 0;
		GL11.glColor3f(1.0f,1.0f,1.0f);
		for(int i = 0; i < value;i++)
		{
			if(i == 0)
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, parts[0].getTextureID());
			}
			else if(i == value-1)
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, parts[2].getTextureID());
			}
			else
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, parts[1].getTextureID());
			}
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0.0f, 0.0f);
				GL11.glVertex2i(getX()+xoff, getY());
				
				GL11.glTexCoord2f(1.0f, 0.0f);
				GL11.glVertex2i(getX()+xoff+20, getY());
				
				GL11.glTexCoord2f(1.0f, 1.0f);
				GL11.glVertex2i(getX()+xoff+20, getY()+20);
				
				GL11.glTexCoord2f(0.0f, 1.0f);
				GL11.glVertex2i(getX()+xoff, getY()+20);
			GL11.glEnd();
			xoff+= 20;
		}
	}
	
	public void select()
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Color c = new Color(getId(),255,255,255);
		c.bind();
		int xoff = 0;
		for(int i = 0; i < value;i++)
		{
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2i(getX()+xoff, getY());
				
				GL11.glVertex2i(getX()+xoff+20, getY());
				
				GL11.glVertex2i(getX()+xoff+20, getY()+20);
				
				GL11.glVertex2i(getX()+xoff, getY()+20);
			GL11.glEnd();
			xoff+= 20;
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
