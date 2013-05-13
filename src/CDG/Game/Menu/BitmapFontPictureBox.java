package CDG.Game.Menu;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;

import CDG.SnakeGame.StaticManager;

public class BitmapFontPictureBox extends PictureBox 
{
	private String text;
	private Texture[] font;
	String[] names = new String[10];
	
	public BitmapFontPictureBox(String fontFolder, String name, int x, int y, boolean numericOnly)
	{
		super(name, x, y);
		loadFont(numericOnly, fontFolder);
	}
	
	private void loadFont(boolean numericOnly, String fontFolder)
	{
		if(numericOnly)
		{
			String[] tmp = {"0","1","2","3","4","5","6","7","8","9"};
			names = tmp;
		}
		else
		{
			
		}
		font = new Texture[names.length];
		for(int i = 0; i < names.length; i++)
		{
			try 
			{
				font[i] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(fontFolder+"/"+names[i]+".png"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
			
	}
	
	public void render()
	{
		text = ""+StaticManager.getScore();
		if(hasFocusEffect() && hasFocus())
		{
			getFocusColor().bind();
		}
		else
		{
			getNormalColor().bind();
		}
		int xoff = 0;
		System.out.println("SCORE:"+text+"/"+StaticManager.getScore());
		for(int i = 0; i < text.length();i++)
		{
			String subs = text.substring(i, i+1);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, font[getIndex(subs)].getTextureID());
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0.0f, 0.0f);
				GL11.glVertex2i(getX()+xoff, getY());
				
				GL11.glTexCoord2f(1.0f, 0.0f);
				GL11.glVertex2i(getX()+xoff+font[getIndex(subs)].getImageWidth(), getY());
				
				GL11.glTexCoord2f(1.0f, 1.0f);
				GL11.glVertex2i(getX()+xoff+font[getIndex(subs)].getImageWidth(), getY()+font[getIndex(subs)].getImageHeight());
				
				GL11.glTexCoord2f(0.0f, 1.0f);
				GL11.glVertex2i(getX()+xoff, getY()+font[getIndex(subs)].getImageHeight());
			GL11.glEnd();
			xoff+= font[getIndex(subs)].getImageWidth();
		}
	}
	private int getIndex(String s)
	{
		for(int i = 0; i < font.length; i++)
		{
			if(names[i].equals(s))
				return i;
		}
		return -1;
	}
	
	public void select()
	{
		text = ""+StaticManager.getScore();
		System.out.println("selecting: "+getId());
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Color c = new Color(getId(),255,255,255);
		c.bind();
		int xoff = 0;
		for(int i = 0; i < text.length();i++)
		{
			String subs = text.substring(i, i+1);
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2i(getX()+xoff, getY());
				
				GL11.glVertex2i(getX()+xoff+font[getIndex(subs)].getImageWidth(), getY());
				
				GL11.glVertex2i(getX()+xoff+font[getIndex(subs)].getImageWidth(), getY()+font[getIndex(subs)].getImageHeight());
				
				GL11.glVertex2i(getX()+xoff, getY()+font[getIndex(subs)].getImageHeight());
			GL11.glEnd();
			xoff+= font[getIndex(subs)].getImageWidth();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
