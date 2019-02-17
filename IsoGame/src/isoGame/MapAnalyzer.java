/**
 * Created by Patrick Murphy 
 * Copyright Patrick Murphy January 2019
 */


package isoGame;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.Line;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Patrick Murphy
 * This class analyzes a map, sets adjacent tiles in each tile, and creates a grid of standing points for characters.
 * It contains a lot of useful methods for dealing with the map.
 * A map analyzer object should be initialized after all of the tiles on a level have been set.
 *
 */

public class MapAnalyzer {
	
	Tile[][] map;
	List<Point> travelPoints = new ArrayList<>();
	boolean[] pointOccupied;
	Random rand = new Random();
	
	public MapAnalyzer(Tile[][] map)
	{
		this.map = map;
		setAdjacentTiles();
		setTravelPoints();
	}
	
	/*
	 * Takes all the tiles in a map and adds an array of possible coordinates of all of the adjacent tiles.
	 * Also adds coordinates of tiles that may not exist if the selected tile is on the edge of the map.
	 */
	private void setAdjacentTiles()
	{
		for(int y = 0; y<map.length;y++)
		{	
				for(int x=0;x<map[y].length;x++)
				{	
					int lLength = map.length/2;
					int xN,yN,xNE,yNE,xE,yE,xSE,ySE,xS,yS,xSW,ySW,xW,yW,xNW,yNW;
					
				//xN
					if(y<=lLength)
					{
						xN = x -1;
					}
					else if(y==lLength+1)
					{
						xN = x ;
					}
					else
					{
						xN=x+1;
					}
				//yN
					yN = y-2;
					
					
				//xNE
					if(y<=lLength)
					{
						xNE = x;
					}
//					else if(y==lLength)
//					{
//						xNE = x+1 ;
//					}
					else
					{
						xNE = x+1;
					}
				//yNE
					yNE = y-1;
					
				//xE
					xE = x+1;
				//yE
					yE = y;
					
				//xSE
					if(y<lLength)
					{
						xSE = x+1;
					}
					else
					{
						xSE = x;
					}
				//ySE
					ySE = y+1;
					
				//xS
					if(y>=lLength)
					{
						xS = x-1;
					}
					else if(y == lLength-1)
					{
						xS = x;
					}
					else
					{
						xS = x+1;
					}
				//yS
					yS = y+2;
					
				//xSW
					if(y>=lLength)
					{
						xSW = x-1;
					}
					else
					{
						xSW = x;
					}
				//ySW
					ySW = y+1;
					
				//xW
					xW = x-1;
				//yW
					yW = y;
					
				//xNW
					if(y<=lLength)
					{
						xNW = x-1
;					}
					else 
					{
						xNW = x;
					}
				//yNW
					yNW = y-1;
					
					
					int[] adjacentTiles = {xN,yN,xNE,yNE,xE,yE,xSE,ySE,xS,yS,xSW,ySW,xW,yW,xNW,yNW};
					
					map[y][x].setAdjacentTiles(adjacentTiles);	
				}			
			}
		}
	

	
	// set all the point you can walk on
	private void setTravelPoints()
	{
		ArrayList<Point> makePoints = new ArrayList<>();
		for(int y = 0; y<map.length;y++)
		{	
				for(int x=0;x<map[y].length;x++)
				{
					int tX = map[y][x].getXpos();
					int tY = map[y][x].getYpos();
					
					//new looped version//NOT WORKING
//					int adjX = 32;
//					int adjY = 32;
//					makePoints.add(new Point(tX+adjX,tY+adjY));
//					for(int i = 0; i< 18;i++)
//					{
////						if(i<6)
//						{
//
//							
//
//							for(int j = 0;j<i;j++)
//							{
//								makePoints.add(new Point(tX+adjX,tY+adjY));
//								adjX+=4;
//							}
//							adjX-=4*i;
//							
//						}
////						else
//						{
//							
//						}
//						adjY+=2;
//					}
					
					
					//old hard coded version
					makePoints.add(new Point(tX+32,tY+32));
					makePoints.add(new Point(tX+24,tY+36));
					makePoints.add(new Point(tX+40,tY+36));
					makePoints.add(new Point(tX+16,tY+40));
					makePoints.add(new Point(tX+32,tY+40));
					makePoints.add(new Point(tX+48,tY+40));
					makePoints.add(new Point(tX+8,tY+44));
					makePoints.add(new Point(tX+24,tY+44));
					makePoints.add(new Point(tX+40,tY+44));
					makePoints.add(new Point(tX+56,tY+44));
					makePoints.add(new Point(tX,tY+48));
					makePoints.add(new Point(tX+16,tY+48));
					makePoints.add(new Point(tX+32,tY+48));
					makePoints.add(new Point(tX+48,tY+48));
					makePoints.add(new Point(tX+64,tY+48));
					makePoints.add(new Point(tX+8,tY+52));
					makePoints.add(new Point(tX+24,tY+52));
					makePoints.add(new Point(tX+40,tY+52));
					makePoints.add(new Point(tX+56,tY+52));
					makePoints.add(new Point(tX+16,tY+56));
					makePoints.add(new Point(tX+32,tY+56));
					makePoints.add(new Point(tX+48,tY+56));
					makePoints.add(new Point(tX+24,tY+60));
					makePoints.add(new Point(tX+40,tY+60));
					makePoints.add(new Point(tX+32,tY+64));
//					
					for(Point pel: makePoints)
					{
						boolean exists = false;
						for(Point el: travelPoints)
						{
							if(pel.equals(el))
							{
								exists = true;
							}
						}
						if(!exists)
						{
							travelPoints.add(pel);
						}
					}
				}
		}		
	}
	
	
	/*
	 * creates occupied boolean for travelPoints
	 */
	
	private void createOccupiedBooleans()
	{
		pointOccupied = new boolean[travelPoints.size()];
		for(int i = 0; i<travelPoints.size();i++)
		{
			pointOccupied[i] = false;
		}
	}
	
	
	/*
	 * given a point it returns the tile that that point is on.
	 */
	public Tile getPointedTile(Point p)
	{
		boolean inArea = false;
		Tile tileSelected = null;
		
		for(int row = 0; row<map.length;row++)
		{	
				for(int column=0;column<map[row].length;column++)
				{	
					inArea = map[row][column].getClickDiamond().contains(p.getX(),(p.getY()));
					if(inArea == true&&map[row][column].isClickable())
				{
						tileSelected = map[row][column];
				}
					inArea = false;
			}	
		}
		return tileSelected;
	}
	
	/*
	 * returns adjacent travel points from a given travel point
	 */
	public ArrayList<Point> getAdjacentTPoints(Point p)
	{
		ArrayList<Point> adjacentPoints = new ArrayList<>();
		if(travelPoints.contains(p))
		{
			for(int i = 0; i<travelPoints.size();i++)
			{
				Point testPoint = travelPoints.get(i);
				//N
				if(testPoint.x == p.x && testPoint.y == p.y-8) adjacentPoints.add(testPoint);
				//NE
				if(testPoint.x == p.x+8 && testPoint.y == p.y-4) adjacentPoints.add(testPoint);
				//E
				if(testPoint.x == p.x+16 && testPoint.y == p.y) adjacentPoints.add(testPoint);
				//SE
				if(testPoint.x == p.x+8 && testPoint.y == p.y+4) adjacentPoints.add(testPoint);
				//S
				if(testPoint.x == p.x && testPoint.y == p.y+8) adjacentPoints.add(testPoint);
				//SW
				if(testPoint.x == p.x-8 && testPoint.y == p.y+4) adjacentPoints.add(testPoint);
				//W
				if(testPoint.x == p.x- 16 && testPoint.y == p.y) adjacentPoints.add(testPoint);
				//NW
				if(testPoint.x == p.x-8 && testPoint.y == p.y-4) adjacentPoints.add(testPoint);
			}
		}
		else adjacentPoints.add(p);
		
		return adjacentPoints;
	}
	
	/**
	 * returns direction of a point given a starting point and and end point
	 */
	public Direction directionTo(Point from, Point to)
	{
		//N
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x-4, from.y-6, from.x+4, from.y-6))
		{
			System.out.println("north");
			return Direction.N;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x+4, from.y-6, from.x+12, from.y-2))
		{
			System.out.println("northeast");
			return Direction.NE;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x+12, from.y-2, from.x+12, from.y+2))
		{
			System.out.println("east");
			return Direction.E;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x+12, from.y+2, from.x+4, from.y+6))
		{
			System.out.println("southeast");
			return Direction.SE;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x+4, from.y+6, from.x-4, from.y+6))
		{
			System.out.println("south");
			return Direction.S;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x-4, from.y+6, from.x-12, from.y+2))
		{
			System.out.println("southwest");
			return Direction.SW;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x-12, from.y+2, from.x-12, from.y-2))
		{
			System.out.println("west");
			return Direction.W;
		}
		if(Line2D.linesIntersect(from.x, from.y, to.x, to.y, from.x-12, from.y-2, from.x-4, from.y-6))
		{
			System.out.println("northwest");
			return Direction.NW;
		}
		else
		{
			return Direction.S;
		}
	}
	
	/**
	 * Given a point it returns an adjacent point to this point in the direction of a second point.
	 */
	
	public Point directionalyClosestTPoint(Point from, Point to)
	{
		//ArrayList<Point> adjacentPoints = getAdjacentTPoints(from);
		Direction direction = directionTo(from,to);
		return pointInDirection(from, direction);
	}
	
	/**
	 * Given a point and a direction it will return the point that is directly next to it in that direction.
	 * If there is no point in the specified direction is will return the original point.
	 * 
	 */
	
	public Point pointInDirection(Point p, Direction d)
	{
		Point testPoint;
		
		if(d == Direction.N)
		{
			testPoint = new Point(p.x,p.y-8);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		
		if(d == Direction.NE)
		{
			testPoint = new Point(p.x+8,p.y-4);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		
		if(d == Direction.E)
		{
			testPoint = new Point(p.x+16,p.y);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		
		if(d == Direction.SE)
		{
			testPoint = new Point(p.x+8,p.y+4);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		
		if(d == Direction.S)
		{
			testPoint = new Point(p.x,p.y+8);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		
		if(d == Direction.SW)
		{
			testPoint = new Point(p.x-8,p.y+4);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		
		if(d == Direction.W)
		{
			testPoint = new Point(p.x-16,p.y);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		if(d == Direction.NW)
		{
			testPoint = new Point(p.x-8,p.y-4);
			if(travelPoints.contains(testPoint))
			{
				return travelPoints.get(travelPoints.indexOf(testPoint));
			}
			else
			{
				return p;
			}
		}
		else 
		{
			return p;
		}
		
	}
	
	/*
	 * highlights adjacent tiles
	 * Sets highlight to true for adjacent tiles
	 */
	
	public void highlightAdjacent(Tile tile)
	{
		int[] at = tile.getAdjacentTiles();
		for(int i = 0;i<at.length;i+=2)
		{
			try {
				map[at[i+1]][at[i]].setHighlighted(true);
//				map[at[i+1]][at[i]].setPrintDirection(directionTest(i));
//				System.out.println("succeed : "+ direction(i));
//				System.out.println("("+at[i]+","+at[i+1]+")");
			}catch(Exception e)
			{
			}
		}
	}
	
	/*
	 * un-highlights adjacent tiles
	 * Sets highlight to false on adjacent tiles
	 */
	public void unhighlightAdjacent(Tile tile)
	{
		int[] at = tile.getAdjacentTiles();
		for(int i = 0;i<at.length;i+=2)
		{
			try {
				map[at[i+1]][at[i]].setHighlighted(false);
//				map[at[i+1]][at[i]].setPrintDirection(directionTest(i));
//				System.out.println("succeed : "+ direction(i));
//				System.out.println("("+at[i]+","+at[i+1]+")");
			}catch(Exception e)
			{
			}
		}
	}
	
	
	
	/*
	 * returns the closest point from adjacent points to a specified point
	 */
	
	public Point closestPointTo(Point from,Point to)
	{
		ArrayList<Point> testList = getAdjacentTPoints(from);
		ArrayList<Double> distanceList = new ArrayList<>();
		for(Point el: testList)
		{
			distanceList.add(distanceTo(el,to));
		}
		double leastDistance = distanceList.get(0);
		for(Double el:distanceList)
		{	
			if(el < leastDistance)
			{
				leastDistance = el;
			}

		}
		int r = rand.nextInt();
		if(r%2==0)
		{
			return testList.get(distanceList.lastIndexOf(leastDistance));
		}
		else
		{
			return testList.get(distanceList.indexOf(leastDistance));
		}
	}
	
	/*
	 * returns closest travel point to any point
	 */
	public Point closestTravelPointTo(Point p)
	{
		Point closest;
		double shortest;
		int pointIndex = 0;
		ArrayList<Double> distanceList = new ArrayList<>();
		for(Point el: travelPoints)
		{
			distanceList.add(distanceTo(p,el));
		}
		shortest = distanceList.get(0);
		for(int i = 1;i<distanceList.size();i++)
		{
			if(distanceList.get(i)<shortest)
			{
				shortest = distanceList.get(i);
				pointIndex = i;
			}
		}
		return travelPoints.get(pointIndex);
	}
	
	/**
	 *Finds the closest adjacent tile from "from" to "to"
	 */
	public Tile closestTileTo(Tile from, Tile to)
	{
		ArrayList<Tile> testList = getAdjacentTiles(from);
		ArrayList<Double> distanceList = new ArrayList<>();
		for(Tile el: testList)
		{
			distanceList.add(distanceTo(el,to));
		}
		double leastDistance = distanceList.get(0);
		for(Double el:distanceList)
		{	
			if(el < leastDistance)
			{
				leastDistance = el;
			}

		}
		int r = rand.nextInt();
		if(r%2==0)
		{
			return testList.get(distanceList.lastIndexOf(leastDistance));
		}
		else
		{
			return testList.get(distanceList.indexOf(leastDistance));
		}
		
	}
	

	
	/*
	 * Finds and returns distance from one Tile, to another Tile.
	 */
	public double distanceTo(Tile from, Tile to)
	{
		double  x = from.getXpos() - to.getXpos();
		double y = from.getYpos() - to.getYpos();
		x= x*x;
		y = y*y;
		double d = Math.sqrt(x+y);
		return d;
	}
	
	/*
	 * Finds and returns distance from one Point, to another Point.
	 */
	public double distanceTo(Point from, Point to)
	{
		double  x = from.x - to.x;
		double y = from.y - to.y;
		x= x*x;
		y = y*y;
		double d = Math.sqrt(x+y);
		return d;
	}

	/*
	 * Returns an ArrayList of all adjacent tiles
	 */
	public ArrayList<Tile> getAdjacentTiles(Tile tile)
	{
		ArrayList<Tile> tiles = new ArrayList<>();
		int[] atarr = tile.getAdjacentTiles();
		for(int i = 0; i<atarr.length;i+=2)
		{
			try 
			{		
					tiles.add(map[atarr[i+1]][atarr[i]]);
				
			}
			catch(Exception e)
			{
			}
		}
		return tiles;
	}
	
	
//PROBLEMS
	/*
	 * Sets adjacent tiles to adjacent(true) to the distance specified
	 * redundant recursion problems
	 */
	public void setAdjacent(Tile tile, int distance)
	{

		int distanceCounter = 1;
		for(int j = distance;j>0;j--)
		{
			int[] at = tile.getAdjacentTiles();
			for(int i = 0;i<at.length;i+=2)
			{
				try {
					if(!map[at[i+1]][at[i]].isSelected())
					{
						map[at[i+1]][at[i]].setAdjacent(true);
					}
					map[at[i+1]][at[i]].setPrintDirection(directionTest(i));
//					setDistanceFrom(tile,map[at[i+1]][at[i]]);
					
					//recursion
//					if(!map[at[i+1]][at[i]].isSelected())
//					{
					setAdjacent(map[at[i+1]][at[i]],distance-1);
//					}
				}catch(Exception e)
				{
				}
			}
		}
	}
	
	/*
	 * Returns an array of 2 integers 
	 * the distance.
	 */
	public int[] distanceFrom(Tile from, Tile to)
	{
		int xDistance = from.getXpos()-to.getXpos();
		int yDistance = from.getYpos()-to.getYpos();
		System.out.println(from.toString()+(from.getXpos()-to.getXpos())+","+(from.getYpos()-to.getYpos()));
		int[] distance = {xDistance,yDistance};
		return distance;
	}
	
	
	/*
	 * returns a direction based on an integer starting with north at 0 and going clockwise
	 */
	private Direction direction(int i)
	{
		switch(i)
		{
		case 0:
			return Direction.N;

		case 2:
			return Direction.NE;

		case 4:
			return Direction.E;

		case 6:
			return Direction.SE;

		case 8:
			return Direction.S;

		case 10:
			return Direction.SW;
		
		case 12:
			return Direction.W;

		case 14:
			return Direction.NW;

		default :
			return Direction.S;
		}
	}
	
	/*
	 * For painting direction of adjacent tiles on screen
	 * Testing mostly
	 */
	private char[] directionTest(int i)
	{

		switch(i)
		{
		case 0:
			char[] test = {'N'};
			return test;
		case 2:
			char[] test1 = {'N','E'};
			return test1;
		case 4:
			char[] test2 = {'E'};
			return test2;

		case 6:
			char[] test3 = {'S','E'};
			return test3;
		case 8:
			char[] test4 = {'S'};
			return test4;
		case 10:
			char[] test5 = {'S','W'};
			return test5;

		case 12:
			char[] test6 = {'W'};
			return test6;
		case 14:
			char[] test7 = {'N','W'};
			return test7;
		default :
			char[] test8 = {'?'};
			return test8;
		}
	}


	/*
	 * For painting direction and distance of adjacent tiles on screen
	 * Testing mostly
	 */
	private char[] directionTest(int i,int distance)
	{	
		char dis = (char)(distance+48);
		switch(i)
		{
		case 0:
			char[] test = {'N',dis};
			return test;
		case 2:
			char[] test1 = {'N','E',(char)dis};
			return test1;
		case 4:
			char[] test2 = {'E',(char)dis};
			return test2;

		case 6:
			char[] test3 = {'S','E',(char)dis};
			return test3;
		case 8:
			char[] test4 = {'S',(char)dis};
			return test4;
		case 10:
			char[] test5 = {'S','W',(char)dis};
			return test5;

		case 12:
			char[] test6 = {'W',(char)dis};
			return test6;
		case 14:
			char[] test7 = {'N','W',(char)dis};
			return test7;
		default :
			char[] test8 = {'?'};
			return test8;
		}
	}

	public List<Point> getTravelPoints() {
		return travelPoints;
	}

}
