package isoGame;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class TSprites extends Sprites {
	
	
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
			sprite = new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/defaultImages/brokenSprite.png")).getImage();
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
			return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/defaultImages/blankTile.png")).getImage();
			
			case 1:
			clickable = true;
			return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/2.png")).getImage();

			case 2:
				clickable = true;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/2.png")).getImage();
				
			case 7:
				clickable = true;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/7.png")).getImage();
			case 11:
				clickable = true;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/11.png")).getImage();
			case 15:
				clickable = true;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/15.png")).getImage();
			case 19:
				clickable = false;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/19.png")).getImage();
			case 23:
				clickable = false;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/23.png")).getImage();

			case 27:
				clickable = false;
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/set1/27.png")).getImage();
			
			default:
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/defaultImages/brokenSprite.png")).getImage();
		}	
	}
	
	private static Image getSet2(int idNum)
	{
		switch(idNum)
		{		
			default:
				return new ImageIcon(TSprites.class.getResource("/isoGame/tileImages/defaultImages/brokenSprite.png")).getImage();
		}	
	}
	
	
}
