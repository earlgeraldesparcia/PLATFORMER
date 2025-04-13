package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class WorldOneStory extends State implements Statemethods{
	
	private MenuButton[] buttons = new MenuButton[4];
	private BufferedImage backgroundImg1, backgroundImg2, backgroundImg3;
	private int menuX, menuY, menuWidth, menuHeight;
	
	private String world1_1 = "world1dialog1.png";
	private String world1_2 = "world1dialog2.png";
	private String world1_3 = "world1dialog3.png";
	
	public WorldOneStory(Game game) {
		super(game);
		backgroundImg1 = GetSpriteAtlas(world1_1);
		backgroundImg2 = GetSpriteAtlas(world1_2);
		backgroundImg3 = GetSpriteAtlas(world1_3);

	}
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/story/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public void update(Graphics g) {
//		g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
	}

	@Override
	public void draw(Graphics g) {
		if(storyIndex1 == 1)
			g.drawImage(backgroundImg1, 0, 0, Game.GAME_WIDTH - 140, Game.GAME_HEIGHT, null);
		else if(storyIndex1 ==2)
			g.drawImage(backgroundImg2, 0, 0, Game.GAME_WIDTH - 140, Game.GAME_HEIGHT, null);
		else if(storyIndex1 == 3)
			g.drawImage(backgroundImg3, 0, 0, Game.GAME_WIDTH - 140, Game.GAME_HEIGHT, null);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			storyIndex1++;
		if(storyIndex1 == 4)
			Gamestate.state = Gamestate.PLAYING;
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
