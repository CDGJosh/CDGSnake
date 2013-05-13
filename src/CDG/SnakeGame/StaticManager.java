package CDG.SnakeGame;

public abstract class StaticManager 
{
	
	public static final int DIF_ASIAN = 2;
	public static final int DIF_HARD = 4;
	public static final int DIF_CLASSIC = 6;
	public static final int DIF_NORMAL = 8;
	public static final int DIF_EASY = 10;
	public static final int DIF_LAME = 12;
	public static final int DIF_SLEEP = 14;
	
	public static final int MENU_MAIN = 0;
	public static final int MENU_DIF = 1;
	public static final int MENU_GAMEOMVER = 2;
	public static final int MENU_PAUSE = 3;
	public static final int MENU_OPTIONS = 3;
	
	private static boolean wasd = true;
	private static int soundVolume = 5;
	private static boolean easterEgg = false;
	private static boolean restartGame = false;
	private static int score = 0;
	private static boolean mouse_buf = false;
	private static boolean inMenu = true;
	private static int difficulty = DIF_CLASSIC;
	private static int activeFrame = 0;

	public static int getDifficulty() 
	{
		return difficulty;
	}

	public static void setDifficulty(int difficulty) 
	{
		StaticManager.difficulty = difficulty;
	}

	public static boolean isInMenu() 
	{
		return inMenu;
	}

	public static void setInMenu(boolean inMenu) 
	{
		StaticManager.inMenu = inMenu;
	}

	public static int getActiveFrame() 
	{
		return activeFrame;
	}

	public static void setActiveFrame(int activeFrame) 
	{
		StaticManager.inMenu = true;
		StaticManager.activeFrame = activeFrame;
	}

	public static boolean isMouse_buf() {
		return mouse_buf;
	}

	public static void setMouse_buf(boolean mouse_buf) {
		StaticManager.mouse_buf = mouse_buf;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		StaticManager.score = score;
	}

	public static boolean getRestartGame() 
	{
		return restartGame;
	}

	public static void setRestartGame(boolean restartGame) 
	{
		StaticManager.restartGame = restartGame;
	}

	public static boolean isEasterEgg() 
	{
		return easterEgg;
	}

	public static void setEasterEgg(boolean easterEgg) 
	{
		StaticManager.easterEgg = easterEgg;
	}

	public static int getSoundVolume() 
	{
		return soundVolume;
	}

	public static void setSoundVolume(int soundVolume) 
	{
		if(soundVolume > 0 && soundVolume <= 10)
			StaticManager.soundVolume = soundVolume;
	}

	public static boolean isWasd() 
	{
		return wasd;
	}

	public static void setWasd(boolean wasd) 
	{
		StaticManager.wasd = wasd;
	}
}
