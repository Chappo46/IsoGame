/**
 * Created by Patrick Murphy 
 * Copyright Patrick Murphy January 2019
 */

package isoGame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.Timer;



public class Characters extends JPanel implements Selectable, ActionListener {
	private Image sprite;
	private Image selectImage;
	private int xPos;
	private int yPos;
	private Point standingPoint;
	private Point destinationPoint;
	private Point nextPoint = standingPoint;
	private int speed = 1;
	private int maxHp = 3;
	private int Hp;
	private boolean selected = false;
	private boolean inBattle = false;
	private boolean moving = false;
	private boolean move1 = false;
	private boolean move2 = false;
	private boolean move3 = false;
	private boolean onTPoint = true;
	private Direction characterOrientation = Direction.S;
	private Tile tileOn,tileDestination;
	private Direction cFacing = Direction.S;
	private MapAnalyzer mapAnalyzer;
	Timer timer;
	
	public Characters(Dimension screen,Tile tileOn,int set, int idNum, MapAnalyzer ma)
	{
		this.tileOn = tileOn;
		changeTile(tileOn);
		standingPoint = new Point(xPos + 32, yPos + 48);
		sprite = CSprites.getSprite(set,idNum);
		selectImage = MSprites.getSprite(set,idNum);
		mapAnalyzer = ma;
//		setRequestFocusEnabled(false);
		setOpaque(false);
		this.setPreferredSize(screen);
		timer = new Timer(33, this);
        timer.start();    
	}
	
	public void move1(Point dp)
	{
		move1 = true;
		destinationPoint = dp;
	}
	
	public void move2(Point dp)
	{
		move2 = true;
		destinationPoint = dp;
	}
	public void move3(Point dp)
	{
		move3 = true;
		destinationPoint = dp;
		characterOrientation = mapAnalyzer.directionTo(standingPoint,destinationPoint);
		System.out.println(characterOrientation.toString());
	}
	
	private void adjustStandingPoint(int x, int y)
	{
		standingPoint.setLocation(standingPoint.x+x, standingPoint.y+y);
		updatePos();
	}
	
	private void updatePos()
	{
		xPos = standingPoint.x-32;
		yPos = standingPoint.y-48;
	}
	
	private void moveToPoint(Point to)
	{
		int xAdj = 0;
		int yAdj = 0;
		if(to.x>standingPoint.x)
		{
			xAdj = 1*speed;
		}
		if(to.y>standingPoint.y)
		{
			yAdj = 1*speed;
		}
		if(to.x<standingPoint.x)
		{
			xAdj = -1*speed;
		}
		if(to.y<standingPoint.y)
		{
			yAdj = -1*speed;
		}
		adjustStandingPoint(xAdj,yAdj);
	}
	
	
	
	public void move(Tile moveTo)
	{
		moving = true;
		tileDestination = moveTo;
	}
	
	public void battleMove(Tile moveTo)
	{
		    Tile old = tileOn; 
			changeTile(moveTo);
			old.setOccupied(false);
			old.setOccupier(null);
			tileOn = moveTo;
	}
	

		
	private void changeStanding(Point newStandingPoint)
	{
		xPos = newStandingPoint.x;
		yPos = newStandingPoint.y;
		updateStandingPoint(newStandingPoint);
		Tile possibleTileOn = mapAnalyzer.getPointedTile(standingPoint);
		if(!possibleTileOn.equals(null))
		{
			tileOn = possibleTileOn;
		}
	}
	
	
	private void changeTile(Tile newTile)
	{
		xPos = newTile.getXpos();
		yPos = newTile.getYpos();
		newTile.setOccupied(true);
		newTile.setOccupier(this);
	}
	
	private void updateStandingPoint(Point newPoint)
	{
		standingPoint.setLocation(newPoint);
	}

	
	public Point getStandingPoint() {
		return standingPoint;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public Image getSprite() {
		return sprite;
	}

	@Override
	public Image getSelectImage() {
		return selectImage;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	public Tile getTileOn() {
		return tileOn;
	}
	public void setTileOn(Tile tileOn)
	{
		this.tileOn = tileOn;
	}

	public Direction getcFacing() {
		return cFacing;
	}

	public void setcFacing(Direction cFacing) {
		this.cFacing = cFacing;
	}



	public boolean isInBattle() {
		return inBattle;
	}



	public void setInBattle(boolean inBattle) {
		this.inBattle = inBattle;
	}



	public boolean isMoving() {
		return moving;
	}



	public void setMoving(boolean moving) {
		this.moving = moving;
	}


	/*
	 * Animation Loop for character
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(moving)
		{
			try
			{
				if(!tileOn.equals(tileDestination)) 
				{	
					
					battleMove(mapAnalyzer.closestTileTo(tileOn, tileDestination));
					tileOn.setHighlighted(true);

				}
				else
				{
				moving = false;
				}
			}
			catch(NullPointerException e)
			{
				moving = false;
			}
		}
		
		if(move1)
		{
			destinationPoint = mapAnalyzer.closestTravelPointTo(destinationPoint);
			if(standingPoint == destinationPoint)
			{
				move1 = false;
			}
			else
			{
				if(onTPoint)
				{
					nextPoint = mapAnalyzer.closestPointTo(standingPoint, destinationPoint);
					onTPoint = false;
				}
				if(mapAnalyzer.distanceTo(standingPoint,destinationPoint)<mapAnalyzer.distanceTo(nextPoint,destinationPoint))
				{
					nextPoint = destinationPoint;
				}
				if(!standingPoint.equals(nextPoint))
				{
					moveToPoint(nextPoint);
				}
				if(standingPoint.equals(nextPoint))
				{
					onTPoint = true;
				}
			}
		
			
		}
		if(move2)
		{
			if(standingPoint != destinationPoint)
			{
				moveToPoint(destinationPoint);
			}
			else
			{
				move2 = false;
			}
		}
		if(move3)
		{
				
				destinationPoint = mapAnalyzer.closestTravelPointTo(destinationPoint);
				if(standingPoint == destinationPoint)
				{
					move1 = false;
				}
				else
				{
				if(onTPoint)
				{
					nextPoint = mapAnalyzer.directionalyClosestTPoint(standingPoint, destinationPoint);
					onTPoint = false;
				}
				if(mapAnalyzer.distanceTo(standingPoint,destinationPoint)<mapAnalyzer.distanceTo(nextPoint,destinationPoint))
				{
					nextPoint = destinationPoint;
				}
				if(!standingPoint.equals(nextPoint))
				{
					moveToPoint(nextPoint);
				}
				if(standingPoint.equals(nextPoint))
				{
					onTPoint = true;
				}
		}
		
		repaint();
	}
		
	}
	
}
