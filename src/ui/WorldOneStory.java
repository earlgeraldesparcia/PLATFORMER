package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class WorldOneStory {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	
	private int storyIndex1 = 1;
	private String story1 = "world1dialog1.png";
	private String story2 = "world1dialog2.png";
	private String story3 = "world1dialog3.png";

	public WorldOneStory(Playing playing) {
		this.playing = playing;
		loadBackground();
	}

	private void loadBackground() {
		backgroundImg = GetSpriteAtlas(story1);
		
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);
	}

	public void update() {
	}

	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH - 120, Game.GAME_HEIGHT, null);

	}
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
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

	public void mouseDragged(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}
	
	public void enterPressed() {
		nextStory();
		if(getStoryIndex1() > 3) {
			playing.showStory(false);
			storyIndex1 = 1;
		}
	}
	
	public void nextStory() {
		storyIndex1++;
		
		if(storyIndex1 == 1) {
			backgroundImg = GetSpriteAtlas(story1);
		}else if(storyIndex1 == 2) {
			backgroundImg = GetSpriteAtlas(story2);
		}else if(storyIndex1 == 3) {
			backgroundImg = GetSpriteAtlas(story3);
		}
	}
	
	public int getStoryIndex1() {
		return storyIndex1;
	}
}
