package CDG.Game.Menu;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import CDG.SnakeGame.StaticManager;

@SuppressWarnings("unused")
public class Menu 
{
	public final int MAIN = 0;
	//public final int DIFFICULTY = 1;
	public final int PAUSE = 2;
	
	private List<MenuFrame> frames;	
	//private Texture background;
	
	public Menu()
	{
		frames = new ArrayList<MenuFrame>();
		createMainFrame();
		createDifficultyFrame();
		createGameOverFrame();
		createOptionFrame();
	}
	
	private void createOptionFrame()
	{
		MenuFrame opt = new MenuFrame("Options");
				
		//Head PictureBox
		PictureBox head = new PictureBox("textures/options.png", "head",50,50);
		head.setNormalColor(new Color(255,255,255));
		opt.add(head);
		
		//SoundVol PictureBox
		PictureBox sound_vol = new PictureBox("textures/volume.png", "sound_vol",46,110);
		sound_vol.setNormalColor(new Color(255,255,255));
		opt.add(sound_vol);
		
		//Minus PictureBox
		PictureBox sound_dec = new PictureBox("textures/minus.png", "sound_dec",340,110);
		sound_dec.setHasFocusEffect(true);
		sound_dec.setFocusColor(new Color(136,228,48));
		sound_dec.setNormalColor(new Color(255,255,255));
		opt.add(sound_dec);
		
		//SoundVol ProgressBar
		ProgressBar sound_pb = new ProgressBar("textures", "sound_pb",380,128);
		opt.add(sound_pb);
		
		//Plus PictureBox
		PictureBox sound_inc = new PictureBox("textures/plus.png", "sound_inc",590,110);
		sound_inc.setHasFocusEffect(true);
		sound_inc.setFocusColor(new Color(136,228,48));
		sound_inc.setNormalColor(new Color(255,255,255));
		opt.add(sound_inc);		
		
		//Plus PictureBox
		PictureBox keys = new PictureBox("textures/keys.png", "keys",50,170);
		keys.setNormalColor(new Color(255,255,255));
		opt.add(keys);	
		
		//Plus PictureBox
		PictureBox keys_wasd = new PictureBox("textures/wasd.png", "keys_wasd",340,170);
		keys_wasd.setHasFocusEffect(true);
		keys_wasd.setFocusColor(new Color(136,228,48));
		keys_wasd.setNormalColor(new Color(255,255,255));
		opt.add(keys_wasd);	
				
		//Plus PictureBox
		PictureBox keys_slash = new PictureBox("textures/slash.png", "keys_slash",480,170);
		keys_slash.setNormalColor(new Color(255,255,255));
		opt.add(keys_slash);	
				
		//Plus PictureBox
		PictureBox keys_arrow = new PictureBox("textures/arrow.png", "keys_arrow",510,170);
		keys_arrow.setHasFocusEffect(true);
		keys_arrow.setFocusColor(new Color(136,228,48));
		keys_arrow.setNormalColor(new Color(255,255,255));
		opt.add(keys_arrow);	

		//Back PictureBox
		PictureBox back = new PictureBox("textures/back.png", "back",55,490);
		back.setHasFocusEffect(true);
		back.setFocusColor(new Color(136,228,48));
		back.setNormalColor(new Color(255,255,255));
		opt.add(back);	
		
		//Add frame to others
		frames.add(opt);
		
		
	}
	
	private void createGameOverFrame()
	{
		MenuFrame gameOver = new MenuFrame("Gameover");
		//go_gameover PictureBox
		PictureBox go_gameover = new PictureBox("textures/gameover.png", "gameover",200,50);
		go_gameover.setHasFocusEffect(false);
		go_gameover.setNormalColor(new Color(255,255,255));
		gameOver.add(go_gameover);
		
		//Score PictureBox
		PictureBox score = new PictureBox("textures/score.png", "score",210,350);
		score.setHasFocusEffect(false);
		score.setNormalColor(new Color(255,255,255));
		gameOver.add(score);
		
		//Score PictureBox
		BitmapFontPictureBox s_count = new BitmapFontPictureBox("textures", "s_count", 400,337, true);
		s_count.setHasFocusEffect(false);
		s_count.setNormalColor(new Color(255,255,255));
		gameOver.add(s_count);
		
		//RR PictureBox
		PictureBox rr = new PictureBox("textures/restart.png", "restart",210,410);
		rr.setHasFocusEffect(true);
		rr.setFocusColor(new Color(136,228,48));
		rr.setNormalColor(new Color(255,255,255));
		gameOver.add(rr);
		
		//Back PictureBox
		PictureBox back = new PictureBox("textures/back.png", "back",210,470);
		back.setHasFocusEffect(true);
		back.setFocusColor(new Color(136,228,48));
		back.setNormalColor(new Color(255,255,255));
		gameOver.add(back);
		
		//Quit PictureBox
		PictureBox quit = new PictureBox("textures/quit.png", "quit",214,530);
		quit.setHasFocusEffect(true);
		quit.setFocusColor(new Color(136,228,48));
		quit.setNormalColor(new Color(255,255,255));
		gameOver.add(quit);
		
		frames.add(gameOver);
		
		
	}
	
	private void createDifficultyFrame()
	{
		MenuFrame dif = new MenuFrame("Difficulty");
		//dif_asian PictureBox
		PictureBox dif_asian = new PictureBox("textures/dif_asian.png", "dif_asian",300,100);
		dif_asian.setHasFocusEffect(true);
		dif_asian.setFocusColor(new Color(136,228,48));
		dif_asian.setNormalColor(new Color(255,255,255));
		dif.add(dif_asian);
		
		//dif_hard PictureBox
		PictureBox dif_hard = new PictureBox("textures/dif_hard.png", "dif_hard",300,160);
		dif_hard.setHasFocusEffect(true);
		dif_hard.setFocusColor(new Color(136,228,48));
		dif_hard.setNormalColor(new Color(255,255,255));
		dif.add(dif_hard);
		
		//dif_classic PictureBox
		PictureBox dif_classic = new PictureBox("textures/dif_classic.png", "dif_classic",300,220);
		dif_classic.setHasFocusEffect(true);
		dif_classic.setFocusColor(new Color(136,228,48));
		dif_classic.setNormalColor(new Color(255,255,255));
		dif.add(dif_classic);
		
		//dif_normal PictureBox
		PictureBox dif_normal = new PictureBox("textures/dif_normal.png", "dif_normal",300,280);
		dif_normal.setHasFocusEffect(true);
		dif_normal.setFocusColor(new Color(136,228,48));
		dif_normal.setNormalColor(new Color(255,255,255));
		dif.add(dif_normal);
		
		//dif_easy PictureBox
		PictureBox dif_easy = new PictureBox("textures/dif_easy.png", "dif_easy",300,340);
		dif_easy.setHasFocusEffect(true);
		dif_easy.setFocusColor(new Color(136,228,48));
		dif_easy.setNormalColor(new Color(255,255,255));
		dif.add(dif_easy);
		
		//dif_lame PictureBox
		PictureBox dif_lame = new PictureBox("textures/dif_lame.png", "dif_lame",300,400);
		dif_lame.setHasFocusEffect(true);
		dif_lame.setFocusColor(new Color(136,228,48));
		dif_lame.setNormalColor(new Color(255,255,255));
		dif.add(dif_lame);
		
		//dif_sleep PictureBox
		PictureBox dif_sleep = new PictureBox("textures/dif_sleep.png", "dif_sleep",300,460);
		dif_sleep.setHasFocusEffect(true);
		dif_sleep.setFocusColor(new Color(136,228,48));
		dif_sleep.setNormalColor(new Color(255,255,255));
		dif.add(dif_sleep);
		
		//dif_sleep PictureBox
		PictureBox dif_head = new PictureBox("textures/difficulty.png", "head",180,40);
		dif_head.setHasFocusEffect(false);
		dif_head.setNormalColor(new Color(255,255,255));
		dif.add(dif_head);
		
		frames.add(dif);
	}
	
	private void createMainFrame()
	{
		MenuFrame main = new MenuFrame("Main");
				
		//Start PictureBox
		PictureBox start = new PictureBox("textures/start.png", "start",50,430);
		start.setHasFocusEffect(true);
		start.setFocusColor(new Color(136,228,48));
		start.setNormalColor(new Color(255,255,255));
		main.add(start);
		
		//Options PictureBox
		PictureBox difc = new PictureBox("textures/difficulty.png", "dif",300,430);
		difc.setHasFocusEffect(true);
		difc.setFocusColor(new Color(136,228,48));
		difc.setNormalColor(new Color(255,255,255));
		main.add(difc);

		//Quit PictureBox
		PictureBox quit = new PictureBox("textures/quit.png", "quit",55,490);
		quit.setHasFocusEffect(true);
		quit.setFocusColor(new Color(136,228,48));
		quit.setNormalColor(new Color(255,255,255));
		main.add(quit);
		
		//Options PictureBox
		PictureBox options = new PictureBox("textures/options.png", "options",300,490);
		options.setHasFocusEffect(true);
		options.setFocusColor(new Color(136,228,48));
		options.setNormalColor(new Color(255,255,255));
		main.add(options);
				
		//Logo PictureBox
		PictureBox logo = new PictureBox("textures/logo.png", "logo",250,50);
		main.add(logo);		
		
		//Add frame to others
		frames.add(main);
		
		
	}
	
	public void renderMenu()
	{
		System.out.println("MENU RENDER");
		frames.get(StaticManager.getActiveFrame()).render(true);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		frames.get(StaticManager.getActiveFrame()).render(false);
	}
}
