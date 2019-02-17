package isoGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BGSprites extends Sprites {
	
	public static Image getSprite(int set, int idNum)
	{
		switch(set)
		{
		case 1:
			sprite = getSet1(idNum);
			return sprite;
		case 2:
			sprite = getSet2(idNum);
			return sprite;
		default:
			sprite = new ImageIcon(BGSprites.class.getResource("/isoGame/bgImages/defaultImages/brokenSprite.png")).getImage();
			System.out.println("missing sprite");
			return sprite;
		}
		
	}
	
	private static Image getSet1(int idNum)
	{
		switch(idNum)
		{
			case 0:
				clickable = false;
				return new ImageIcon(BGSprites.class.getResource("/isoGame/bgImages/defaultImages/blankTile.png")).getImage();
			
			case 1:
				clickable = true;
				return new ImageIcon(BGSprites.class.getResource("/isoGame/bgImages/set1/2.png")).getImage();
			
			case 2:
				clickable = true;
				return new ImageIcon(BGSprites.class.getResource("/isoGame/bgImages/set1/2.png")).getImage();
			
			default:
				return new ImageIcon(BGSprites.class.getResource("/isoGame/bgImages/defaultImages/brokenSprite.png")).getImage();
		}	
	}
	
	private static Image getSet2(int idNum)
	{
		switch(idNum)
		{		
			default:
				return new ImageIcon(BGSprites.class.getResource("/isoGame/tileImages/defaultImages/brokenSprite.png")).getImage();
		}	
	}

}
