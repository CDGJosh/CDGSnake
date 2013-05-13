package CDG.Game.Menu;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;

public class PictureBox extends MenuItem 
{
	private Texture img;
	private String name;
	private boolean focusEffect = false;
	private Color focusColor;
	private Color normalColor = Color.white;
	private Color highlightColor = Color.blue;
	
	public Color getNormalColor() 
	{
		return normalColor;
	}

	public void setNormalColor(Color normalColor) 
	{
		this.normalColor = normalColor;
	}
	
	public Color getFocusColor() 
	{
		return focusColor;
	}

	public void setFocusColor(Color focusColor) 
	{
		this.focusColor = focusColor;
	}

	public boolean hasFocusEffect() 
	{
		return focusEffect;
	}

	public void setHasFocusEffect(boolean focusEffect) 
	{
		this.focusEffect = focusEffect;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public PictureBox(String file, String name, int x, int y)
	{
		this.name = name;
		img = loadImage(file);
		setX(x);
		setY(y);
	}
	
	public PictureBox(String name, int x, int y)
	{
		this.name = name;
		setX(x);
		setY(y);
	}
	
	private Texture loadImage(String file)
	{
		try 
		{
			Texture t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(file));
			setWidth(t.getImageWidth());
			setHeight(t.getImageHeight());
			return t;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void render()
	{
		if(focusEffect && hasFocus())
		{
			focusColor.bind();
		}
		else if(isHighlighted())
		{
			highlightColor.bind();
		}
		else
		{
			normalColor.bind();
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, img.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0.0f, 0.0f);
			GL11.glVertex2i(getX(), getY());
			
			GL11.glTexCoord2f(1.0f, 0.0f);
			GL11.glVertex2i(getX()+getWidth(), getY());
			
			GL11.glTexCoord2f(1.0f, 1.0f);
			GL11.glVertex2i(getX()+getWidth(), getY()+getHeight());
			
			GL11.glTexCoord2f(0.0f, 1.0f);
			GL11.glVertex2i(getX(), getY()+getHeight());
		GL11.glEnd();
	}
	
	public void select()
	{
		System.out.println("selecting: "+getName()+"("+getId()+")"+ " / "+isHighlighted());
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Color c = new Color(getId(),255,255,255);
		c.bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2i(getX(), getY());
			
			GL11.glVertex2i(getX()+getWidth(), getY());
			
			GL11.glVertex2i(getX()+getWidth(), getY()+getHeight());
			
			GL11.glVertex2i(getX(), getY()+getHeight());
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public Color getHighlightColor() {
		return highlightColor;
	}

	public void setHighlightColor(Color highlightColor) {
		this.highlightColor = highlightColor;
	}
}
