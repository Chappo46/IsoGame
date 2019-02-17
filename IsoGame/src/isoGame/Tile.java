/**
 * Created by Patrick Murphy 
 * Copyright Patrick Murphy January 2019
 */

package isoGame;

import java.awt.Polygon;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Tile implements Selectable{
	
	protected ImageIcon full,left,leftBottom,leftBottomCap,leftCap,leftTop,leftTopCap,middleCap,right,rightBottom, rightBottomCap,rightCap,rightTop,rightTopCap,top,blank,bottom;
	
	private int polyyMove, polyxMove;
	protected int rowNumber,columnNumber;
	private Polygon clickDiamond;
	private boolean clickable;
	private boolean selected = false;
	private boolean subSelected = false;
	private boolean occupied = false;
	private Characters occupier;
	private boolean highlighted = false;
	private int[] polygonX;
	private int[] polygonY;
	private int[] adjacentTiles;
	private boolean isAdjacent = false;
	private int adjacentDistance = 0; 
	private int xpos,ypos;
	private Image tile;
	private Image selectImage;
	
	//NONFUNCTIONAL
	private Point middlePoint,topPoint,leftPoint,rightPoint,bottomPoint;
	
	
	//MapAnalyzer tester
	char[] printDirection;
	
	
	public Tile(int set,int idNum)
	{
		tile = TSprites.getSprite(set, idNum);
		clickable = TSprites.getClickable();
	}
	
	public Tile(int set,int idNum,boolean clickable)
	{
		tile = TSprites.getSprite(set, idNum);
		this.clickable = clickable;
	}
	


	public void createClickDiamond(int polyX, int polyY)
	{
		polyxMove = polyX;
		polyyMove = polyY;
		int[] polyx = {1+polyxMove,32+polyxMove,63+polyxMove,32+polyxMove};
		int[] polyy = {47+polyyMove,32+polyyMove,48+polyyMove,63+polyyMove};
		polygonX = polyx;
		polygonY = polyy;
		clickDiamond = new Polygon(polyx,polyy,4);
	}
	
	public void moveClickDiamond(int x, int y)
	{
		int[] polyx = {polygonX[0]+x,polygonX[1]+x,polygonX[2]+x,polygonX[3]+x};
		int[] polyy = {polygonY[0]+y,polygonY[1]+y,polygonY[2]+y,polygonY[3]+y};
		polygonX = polyx;
		polygonY = polyy;
		Polygon cd = new Polygon(polyx,polyy,4);
		clickDiamond = cd;
		clickDiamond.invalidate();
	}
	
	
//NONFUNCTIONAL
	public void setStandingPoints()
	{
		
		topPoint = new Point(xpos+32,ypos+37);
		rightPoint = new Point(xpos+48,ypos+46);
		bottomPoint = new Point(xpos+32,ypos+55);
		leftPoint = new Point(xpos+15,ypos+46);
		middlePoint = new Point(xpos+32,ypos+46);
	}
	
//NONFUNCTIONAL
	
	public Point getStandingPoints(int i)
	{
		switch(i)
		{
		case 0:
			return topPoint;
		case 1:
			return rightPoint;
		case 2:
			return bottomPoint;
		case 3:
			return leftPoint;
		case 4: 
			return middlePoint;
		default:
			return middlePoint;
		}
	}
	
	
//NONFUNCTIONAL
	public Point[] getStandingPoints()
	{
		Point[] standingPoints = {topPoint,rightPoint,bottomPoint,leftPoint,middlePoint};
		return standingPoints;
	}
	
	public Image getTile()
	{
		return tile;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public void setColumnNumber(int tileNumber) {
		this.columnNumber = tileNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}


	public boolean isClickable() {
		return clickable;
	}

	public Polygon getClickDiamond() {
		return clickDiamond;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public int[] getAdjacentTiles() {
		return adjacentTiles;
	}

	public void setAdjacentTiles(int[] adjacentTiles) {
		this.adjacentTiles = adjacentTiles;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public char[] getPrintDirection() {
		return printDirection;
	}

	public void setPrintDirection(char[] printDirection) {
		this.printDirection = printDirection;
	}

	public boolean isAdjacent() {
		return isAdjacent;
	}

	public void setAdjacent(boolean isAdjacent) {
		this.isAdjacent = isAdjacent;
	}

	public int getAdjacentDistance() {
		return adjacentDistance;
	}

	public void setAdjacentDistance(int adjacentDistance) {
		this.adjacentDistance = adjacentDistance;
	}
	
	
	
	public boolean isSubSelected() {
		return subSelected;
	}

	public void setSubSelected(boolean subSelected) {
		this.subSelected = subSelected;
	}

	@Override
	public String toString()
	{
		return String.format("Tile %d,%d at: %d,%d",columnNumber,rowNumber,xpos,ypos);
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

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Characters getOccupier() {
		return occupier;
	}

	public void setOccupier(Characters occupier) {
		this.occupier = occupier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xpos;
		result = prime * result + ypos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (xpos != other.xpos)
			return false;
		if (ypos != other.ypos)
			return false;
		return true;
	}
	
	


	
	
}
