package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites1.png";
	public static final String LEVEL_ATLAS = "outside_sprites1.png";
	public static final String MENU_BUTTONS = "button_atlas1.png";
	public static final String MENU_BACKGROUND = "menu_background1.png";
	public static final String PAUSE_BACKGROUND = "pause_menu1.png";
	public static final String SOUND_BUTTONS = "sound_button1.png";
	public static final String URM_BUTTONS = "urm_buttons1.png";
	public static final String VOLUME_BUTTONS = "volume_buttons1.png";
	public static final String MENU_BACKGROUND_IMG = "background_menu1.png";
	public static final String PLAYING_BG_IMG1 = "World1.png";
	public static final String PLAYING_BG_IMG2 = "World2.png";
	public static final String PLAYING_BG_IMG3 = "World3.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String CRABBY_SPRITE = "dwarfsprites.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	public static final String COMPLETED_IMG = "completed_sprite1.png";
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects_sprites.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";
	public static final String CANNON_ATLAS = "cannon_atlas.png";
	public static final String CANNON_BALL = "ball.png";
	public static final String DEATH_SCREEN = "death_screen1.png";
	public static final String OPTIONS_MENU = "options_background2.png";
	public static final String PINKSTAR_ATLAS = "manananggal.png";
	public static final String SHROOMBIE_ATLAS = "shroombie.png";
	public static final String QUESTION_ATLAS = "question_atlas.png";
	public static final String EXCLAMATION_ATLAS = "exclamation_atlas.png";
	public static final String SHARK_ATLAS = "minotaur.png"; //edited
	public static final String FROST_GIANT_ATLAS = "frostgiant.png";
	public static final String BOSS_ATLAS = "boss.png";
	public static final String CREDITS = "credits_list1.png";
	public static final String GRASS_ATLAS = "grass_atlas.png";
	public static final String TREE_ONE_ATLAS = "tree_one_atlas1.png";
	public static final String TREE_TWO_ATLAS = "tree_two_atlas1.png";
	public static final String GAME_COMPLETED = "game_completed1.png";
	public static final String RAIN_PARTICLE = "rain_particle.png";
	public static final String WATER_TOP = "water_atlas_animation.png";
	public static final String WATER_BOTTOM = "water.png";
	public static final String SHIP = "ship.png";

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

	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++) {
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];

			}

		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
	}

}