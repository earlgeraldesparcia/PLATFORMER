package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
	private int playerBasicAttackDamage = Player.getBasicAttackDamage();

	private Playing playing;
	private BufferedImage[][] crabbyArr, pinkstarArr, sharkArr, shroombieArr, frostGiantArr, bossArr;
	private Level currentLevel;

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		this.currentLevel = level;
	}

	public void update(int[][] lvlData) {
		boolean isAnyActive = false;
		for (Duwende c : currentLevel.getCrabs())
			if (c.isActive()) {
				c.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Manananggal p : currentLevel.getPinkstars())
			if (p.isActive()) {
				p.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Tikbalang s : currentLevel.getSharks())
			if (s.isActive()) {
				s.update(lvlData, playing);
				isAnyActive = true;
			}
		
		for (Shroombie sh : currentLevel.getShroombie())
			if (sh.isActive()) {
				sh.update(lvlData, playing);
				isAnyActive = true;
			}
		
		for (Kapfrost f : currentLevel.getFrostGiant())
			if (f.isActive()) {
				f.update(lvlData, playing);
				isAnyActive = true;
			}
		
		for (Boss b : currentLevel.getBoss())
			if (b.isActive()) {
				b.update(lvlData, playing);
				isAnyActive = true;
			}

		if (!isAnyActive)
			playing.setLevelCompleted(true);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
		drawPinkstars(g, xLvlOffset);
		drawSharks(g, xLvlOffset);
		drawFrostGiant(g, xLvlOffset);
		drawShroombie(g, xLvlOffset);
		drawBoss(g, xLvlOffset);
	}
	
	private void drawBoss(Graphics g, int xLvlOffset) {
		for (Boss b : currentLevel.getBoss())
			if (b.isActive()) {
				g.drawImage(bossArr[b.getState()][b.getAniIndex()], (int) b.getHitbox().x - xLvlOffset - BOSS_DRAWOFFSET_X + b.flipX(),
						(int) b.getHitbox().y - BOSS_DRAWOFFSET_Y + (int) b.getPushDrawOffset(), BOSS_WIDTH * b.flipW(), BOSS_HEIGHT, null);
//				s.drawHitbox(g, xLvlOffset);
//				s.drawAttackBox(g, xLvlOffset);
			}
	}
	
	private void drawFrostGiant(Graphics g, int xLvlOffset) {
		for (Kapfrost f : currentLevel.getFrostGiant())
			if (f.isActive()) {
				g.drawImage(frostGiantArr[f.getState()][f.getAniIndex()], (int) f.getHitbox().x - xLvlOffset - FROST_GIANT_DRAWOFFSET_X + f.flipX(),
						(int) f.getHitbox().y - FROST_GIANT_DRAWOFFSET_Y + (int) f.getPushDrawOffset(), FROST_GIANT_WIDTH * f.flipW(), FROST_GIANT_HEIGHT, null);
//				s.drawHitbox(g, xLvlOffset);
//				s.drawAttackBox(g, xLvlOffset);
			}
	}

	private void drawSharks(Graphics g, int xLvlOffset) {
		for (Tikbalang s : currentLevel.getSharks())
			if (s.isActive()) {
				g.drawImage(sharkArr[s.getState()][s.getAniIndex()], (int) s.getHitbox().x - xLvlOffset - SHARK_DRAWOFFSET_X + s.flipX(),
						(int) s.getHitbox().y - SHARK_DRAWOFFSET_Y + (int) s.getPushDrawOffset(), SHARK_WIDTH * s.flipW(), SHARK_HEIGHT, null);
//				s.drawHitbox(g, xLvlOffset);
//				s.drawAttackBox(g, xLvlOffset);
			}
	}

	private void drawPinkstars(Graphics g, int xLvlOffset) {
		for (Manananggal p : currentLevel.getPinkstars())
			if (p.isActive()) {
				g.drawImage(pinkstarArr[p.getState()][p.getAniIndex()], (int) p.getHitbox().x - xLvlOffset - PINKSTAR_DRAWOFFSET_X + p.flipX(),
						(int) p.getHitbox().y - PINKSTAR_DRAWOFFSET_Y + (int) p.getPushDrawOffset(), PINKSTAR_WIDTH * p.flipW(), PINKSTAR_HEIGHT, null);
//				p.drawHitbox(g, xLvlOffset);
			}
	}

	private void drawCrabs(Graphics g, int xLvlOffset) {
		for (Duwende c : currentLevel.getCrabs())
			if (c.isActive()) {

				g.drawImage(crabbyArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
						(int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y + (int) c.getPushDrawOffset(), CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);

//				c.drawHitbox(g, xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);
			}

	}
	
	private void drawShroombie(Graphics g, int xLvlOffset) {
		for (Shroombie sh : currentLevel.getShroombie())
			if (sh.isActive()) {

				g.drawImage(shroombieArr[sh.getState()][sh.getAniIndex()], (int) sh.getHitbox().x - xLvlOffset - SHROOMBIE_DRAWOFFSET_X + sh.flipX(),
						(int) sh.getHitbox().y - SHROOMBIE_DRAWOFFSET_Y + (int) sh.getPushDrawOffset(), SHROOMBIE_WIDTH * sh.flipW(), SHROOMBIE_HEIGHT, null);

//				c.drawHitbox(g, xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);
			}

	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Duwende c : currentLevel.getCrabs())
			if (c.isActive())
				if (c.getState() != DEAD && c.getState() != HIT)
					if (attackBox.intersects(c.getHitbox())) {
						c.hurt(playerBasicAttackDamage);
						return;
					}

		for (Manananggal p : currentLevel.getPinkstars())
			if (p.isActive()) {
				if (p.getState() == ATTACK && p.getAniIndex() >= 7)
					return;
				else {
					if (p.getState() != DEAD && p.getState() != HIT)
						if (attackBox.intersects(p.getHitbox())) {
							p.hurt(playerBasicAttackDamage);
							return;
						}
				}
			}

		for (Tikbalang s : currentLevel.getSharks())
			if (s.isActive()) {
				if (s.getState() != DEAD && s.getState() != HIT)
					if (attackBox.intersects(s.getHitbox())) {
						s.hurt(playerBasicAttackDamage);
						return;
					}
			}
		
		for (Kapfrost f : currentLevel.getFrostGiant())
			if (f.isActive()) {
				if (f.getState() != DEAD && f.getState() != HIT)
					if (attackBox.intersects(f.getHitbox())) {
						f.hurt(playerBasicAttackDamage);
						return;
					}
			}
		
		for (Shroombie sh : currentLevel.getShroombie())
			if (sh.isActive()) {
				if (sh.getState() != DEAD && sh.getState() != HIT)
					if (attackBox.intersects(sh.getHitbox())) {
						sh.hurt(playerBasicAttackDamage);
						return;
					}
			}
		
		for (Boss b : currentLevel.getBoss())
			if (b.isActive()) {
				if (b.getState() != DEAD && b.getState() != HIT)
					if (attackBox.intersects(b.getHitbox())) {
						b.hurt(playerBasicAttackDamage);
						return;
					}
			}
	}

	private void loadEnemyImgs() {
		crabbyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE), 8, 5, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
		pinkstarArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.PINKSTAR_ATLAS), 8, 5, PINKSTAR_WIDTH_DEFAULT, PINKSTAR_HEIGHT_DEFAULT);
		sharkArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SHARK_ATLAS), 9, 5, SHARK_WIDTH_DEFAULT, SHARK_HEIGHT_DEFAULT);
		shroombieArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SHROOMBIE_ATLAS), 8, 5, SHROOMBIE_WIDTH_DEFAULT, SHROOMBIE_HEIGHT_DEFAULT);
		frostGiantArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.FROST_GIANT_ATLAS), 10, 5, FROST_GIANT_WIDTH_DEFAULT, FROST_GIANT_HEIGHT_DEFAULT);
		bossArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BOSS_ATLAS), 10, 5, BOSS_WIDTH_DEFAULT, BOSS_HEIGHT_DEFAULT);
	}

	private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
		BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
		for (int j = 0; j < tempArr.length; j++)
			for (int i = 0; i < tempArr[j].length; i++)
				tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
		return tempArr;
	}

	public void resetAllEnemies() {
		for (Duwende c : currentLevel.getCrabs())
			c.resetEnemy();
		for (Manananggal p : currentLevel.getPinkstars())
			p.resetEnemy();
		for (Tikbalang s : currentLevel.getSharks())
			s.resetEnemy();
		for (Kapfrost f : currentLevel.getFrostGiant())
			f.resetEnemy();
		for (Shroombie sh : currentLevel.getShroombie())
			sh.resetEnemy();
		for (Boss b : currentLevel.getBoss())
			b.resetEnemy();
	}

}
