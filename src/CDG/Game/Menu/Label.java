package CDG.Game.Menu;
import org.newdawn.slick.Color;

public class Label extends MenuItem 
{
	private String text;
	
	
	public Label(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public void setText(String newText)
	{
		text = newText;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void render()
	{
		//Color.white.bind();
		
	}
	
	public void select()
	{
		Color c = new Color(getId(),255,255,255);
		c.bind();
	}

	
}
