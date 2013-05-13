package CDG.Game.Menu;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import CDG.SnakeGame.StaticManager;
public class MenuFrame
{
	private String name;
	private List<MenuItem> items;
	
	public MenuFrame(String name)
	{
		this.name = name;
		items = new ArrayList<MenuItem>();
	}

	public String getName() 
	{
		return name;
	}
	
	public void render(boolean select)
	{
		if(select)
		{
			GL11.glRenderMode(GL11.GL_RENDER);
			for(int i = 0; i < items.size(); i++)
			{
				items.get(i).select();
			}
			processHits();
		}
		else
		{
			GL11.glRenderMode(GL11.GL_RENDER);
			for(int i = 0; i < items.size(); i++)
			{
				items.get(i).render();
			}
		}
		
	}
	
	private void processHits()
	{
		if(!Mouse.isButtonDown(0))
		{
			StaticManager.setMouse_buf(false);
		}
		ByteBuffer pixel = ByteBuffer.allocateDirect(4);
		GL11.glReadPixels(Mouse.getX(), Mouse.getY(), 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixel);
		System.out.println(Mouse.getX()+"/"+Mouse.getY()+"/"+((int)pixel.get(0)& 0xFF));
		for(int i = 0; i < items.size(); i++)
		{
			items.get(i).setHasFocus(false);
			if(items.get(i).getId() == pixel.get(0))
			{
				items.get(i).setHasFocus(true);
				if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("start") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setInMenu(false);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setActiveFrame(StaticManager.MENU_DIF);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().startsWith("dif_") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					if(((PictureBox)items.get(i)).getName().equals("dif_asian"))
						StaticManager.setDifficulty(StaticManager.DIF_ASIAN);
					else if(((PictureBox)items.get(i)).getName().equals("dif_hard"))
						StaticManager.setDifficulty(StaticManager.DIF_HARD);
					else if(((PictureBox)items.get(i)).getName().equals("dif_classic"))
						StaticManager.setDifficulty(StaticManager.DIF_CLASSIC);
					else if(((PictureBox)items.get(i)).getName().equals("dif_normal"))
						StaticManager.setDifficulty(StaticManager.DIF_NORMAL);
					else if(((PictureBox)items.get(i)).getName().equals("dif_easy"))
						StaticManager.setDifficulty(StaticManager.DIF_EASY);
					else if(((PictureBox)items.get(i)).getName().equals("dif_lame"))
						StaticManager.setDifficulty(StaticManager.DIF_LAME);
					else if(((PictureBox)items.get(i)).getName().equals("dif_sleep"))
						StaticManager.setDifficulty(StaticManager.DIF_SLEEP);
					StaticManager.setActiveFrame(StaticManager.MENU_MAIN);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("quit") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					System.exit(1337);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("back") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setRestartGame(true);
					StaticManager.setActiveFrame(StaticManager.MENU_MAIN);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("logo") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setEasterEgg(true);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("options") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setActiveFrame(StaticManager.MENU_OPTIONS);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("sound_inc") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setSoundVolume(StaticManager.getSoundVolume()+1);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("sound_dec") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setSoundVolume(StaticManager.getSoundVolume()-1);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("keys_wasd") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setWasd(true);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("keys_arrow") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setWasd(false);
					StaticManager.setMouse_buf(true);
				}
				else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("restart") && Mouse.isButtonDown(0) && !StaticManager.isMouse_buf())
				{
					StaticManager.setRestartGame(true);
					StaticManager.setInMenu(false);
					StaticManager.setMouse_buf(true);
				}
				
			}
			optionsHighlight();
			difHighlight();
		}
		
	}
	
	private void difHighlight()
	{
		for(int i = 0; i < this.items.size(); i++)
		{
			if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_asian") && StaticManager.getDifficulty() == StaticManager.DIF_ASIAN)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_hard") && StaticManager.getDifficulty() == StaticManager.DIF_HARD)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_classic") && StaticManager.getDifficulty() == StaticManager.DIF_CLASSIC)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_normal") && StaticManager.getDifficulty() == StaticManager.DIF_NORMAL)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_easy") && StaticManager.getDifficulty() == StaticManager.DIF_EASY)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_lame") && StaticManager.getDifficulty() == StaticManager.DIF_LAME)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().equals("dif_sleep") && StaticManager.getDifficulty() == StaticManager.DIF_SLEEP)
			{
				items.get(i).setHighlighted(true);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName().startsWith("dif_"))
			{
				items.get(i).setHighlighted(false);
			}
		}
	}
	
	private void optionsHighlight()
	{
		for(int i = 0; i < this.items.size(); i++)
		{
			if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName() == "keys_wasd")
			{
				if(StaticManager.isWasd())
					items.get(i).setHighlighted(true);
				else
					items.get(i).setHighlighted(false);
			}
			else if(items.get(i).getClass() == PictureBox.class && ((PictureBox)items.get(i)).getName() == "keys_arrow")
			{
				if(!StaticManager.isWasd())
					items.get(i).setHighlighted(true);
				else
					items.get(i).setHighlighted(false);
			}
		}
	}
	
	public void add(MenuItem i)
	{
		i.setId(items.size()+1);
		items.add(i);
	}
	
	public int select()
	{
		render(true);
		return 1;
	}
	
	
}
