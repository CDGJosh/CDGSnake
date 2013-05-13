package CDG.Game.Menu;

public class MenuItem 
{
	private int x;
	private int y;
	private boolean hasFocus;
	private boolean highlighted;
	private int id;
	private int height;
	private int width;
	
	public boolean hasFocus() 
	{
		return hasFocus;
	}

	public void setHasFocus(boolean hasFocus) 
	{
		this.hasFocus = hasFocus;
	}
	
	public void setX(int newX)
	{
		x = newX;
	}
	
	public void render()
	{
		
	}
	
	public void select()
	{
		
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setY(int newY)
	{
		y = newY;
	}
	
	public int getY()
	{
		return y;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) 
	{
		this.highlighted = highlighted;
	}
}
