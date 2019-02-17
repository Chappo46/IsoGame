package isoGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class BottomMenu extends Menu {
	
	private int windowX, windowY;
	private Image base = MSprites.getSprite(1,1);
	private Rectangle battleBottomClick = new Rectangle(144,64);
	private boolean inBattle = true;
	private Image inB = MSprites.getSprite(1,3);
	private Image outB = MSprites.getSprite(1,2);
	Characters player;
	
	public BottomMenu(Dimension screen, Characters player)
	{
		windowX = screen.width;
		windowY = screen.height;

		this.player = player;
	}
	
	
	



	public Rectangle getBattleBottomClick() {
		return battleBottomClick;
	}



	public void setBattleBottomClick(Rectangle battleBottonClick) {
		this.battleBottomClick = battleBottonClick;
	}

	public boolean buttonClicked(Point p)
	{
		return battleBottomClick.contains(p);
	}
	
	public void changeBattleStatus()
	{
		if(player.isInBattle())
		{
			player.setInBattle(false);
		}
		player.setInBattle(true);
	}

	public Image getBase() 
	{
		return base;
	}
	
	
}
