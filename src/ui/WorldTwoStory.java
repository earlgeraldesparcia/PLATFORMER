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

public class WorldTwoStory {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	
	private int storyIndex2 = 1;
	private String story1 = "world2dialog1.png";
	private String story2 = "world2dialog2.png";
	private String story3 = "world2dialog3.png";
	private String story4 = "world2dialog4.png";
	private String story5 = "world2dialog5.png";

	public WorldTwoStory(Playing playing) {
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
		if(getStoryIndex2() > 3) {
			playing.showStory(false);
			storyIndex2 = 1;
		}
	}
	
	public void nextStory() {
		storyIndex2++;
		
		if(storyIndex2 == 1) {
			backgroundImg = GetSpriteAtlas(story1);
		}else if(storyIndex2 == 2) {
			backgroundImg = GetSpriteAtlas(story2);
		}else if(storyIndex2 == 3) {
			backgroundImg = GetSpriteAtlas(story3);
		}
	}
	
	public int getStoryIndex2() {
		return storyIndex2;
	}
}
