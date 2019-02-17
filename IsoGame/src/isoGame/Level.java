/**
 * Created by Patrick Murphy 
 * Copyright Patrick Murphy January 2019
 */

package isoGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Level extends DisplayScreen implements ActionListener {
	

	//map layers
	private Tile[][] mapLayer1; 
//	private Tile[][] mapLayer3; 
	private Tile[][] mapLayer4; 
	private Characters player;
	private ArrayList<Characters> characters = new ArrayList<>();
	
	
	//Sets the size of the screen
	private int windowX ;
	private int windowY;
	
	private Dimension screen;
	
	
	//Used for adjusting view of window
	//needs work
	private int cameraX =0;
	private int cameraY =0;
	
	//Coordinates for the selected element
	private int selectedY = 0;
	private int selectedX = 0;
	
	//storage place for selected selectables
	private Selectable leftSelected,rightSelected;
	
	//numer for the current level
	private int levelNum;
	
	//boolean to help determine if the base layer of tiles repainted 
	private boolean drawTiles = true;
	
	//helps the method TileSelected select and deselect tiles
	private boolean tileReselected = false;
	
	//creates a MapAnalyzer for the level
	private MapAnalyzer mapAnalyzer;
	
	// swing timer for animation
	//will be replaced by thread
	private Timer timer;

	//tracking tiles moved for 
	private int playerSteps = 0;

	
	//background images
	private Image treeBG =  BGSprites.getSprite(1,1);
	private BottomMenu bottomMenu;
	
	

	
	/**
	 * Creates a new Level object.
	 * 
	 * @param wX width of the screen.
	 * @param wY height of the screen.
	 * @param levelNum number to identify and instantiate the level.
	 */
	public Level(Dimension screen,int levelNum)
	{
		this.levelNum = levelNum;
		this.screen = screen;
		this.windowX = screen.width;
		this.windowY = screen.height;
		initLevel();
	}
	
	/*
	 * Initializes the level.
	 * Instantiates maps 1 and 4.
	 * squareTile() creates an array of coordinates for the tiles in the map.
	 * setTiles() applies the array of coordinates to each tile in order 
	 * mapAnalyzer is instantiated with mapLayer1, adding the surrounding tiles coordinates to every tile
	 * 
	 * Characters are instantiated and added to the characters ArrayList
	 * 
	 * the bottom menu is instantiated and added to the level
	 * 
	 * timer created for animation loop
	 * timer will be replaced with thread
	 * 
	 * a mouse listener is added to the entire JPanel so if you click anywhere on the screen it will register a point
	 * and perform an action either from the left click or the right click
	 */
	private void initLevel()
	{
		mapLayer1 = CreateMap.getMapLayer1(levelNum);
		mapLayer4 = CreateMap.getMapLayer4(levelNum);
		
		Integer[] tileCoordinates = squareTile(windowX,windowY,mapLayer1.length);		
		setTiles(mapLayer1, tileCoordinates);
		//set tiles for mapLayer4 use the same coordinates as mapLayer1 for simplicity
		setTiles(mapLayer4, tileCoordinates);
		
		mapAnalyzer = new MapAnalyzer(mapLayer1);
		
		bottomMenu = new BottomMenu(screen,player);
		
		player = new Characters(screen,mapLayer1[3][3],1,2,mapAnalyzer,this);
		this.add(player);
		characters.add(player);
		
//		timer = new Timer(200, this);
//      timer.start();

		//working on key bindings
//		this.getInputMap().put(KeyStroke.getKeyStroke("w"),
//		"doSomething");
//		this.getActionMap().put("doSomething",
//		UpAction);
		
        
        
		this.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e)
			{
				Point mp = e.getPoint();

				
				if(SwingUtilities.isLeftMouseButton(e))
				{
					leftMousePressed(mp);
				}
				if(SwingUtilities.isRightMouseButton(e))
				{
					rightMousePressed(mp);
				}
				
				
			}
		});	
	}
	
	
	/*Action when the left mouse button is clicked
	 * New boolean inArea is set to false
	 * 
	 * Loops through the map and sets all tiles adjacent boolean as false
	 * 
	 * Res
	 * 
	 */
	private void leftMousePressed(Point mp)
	{
		boolean inArea = false;
		//RESET ADJACENT TILES

		resetAdjacentTiles();
			
		//RESET HIGHLIGHTED TILES
		mapAnalyzer.unhighlightAdjacent(mapLayer1[selectedY][selectedX]);
		deselectAll();
		//Loop through map
		Tile clickedTile = mapAnalyzer.getPointedTile(mp);
		
		mapAnalyzer.directionTo(player.getStandingPoint(), mp);
		
		try 
		{
			setSelectedTile(clickedTile.getRowNumber(),clickedTile.getColumnNumber());
			mapAnalyzer.setAdjacent(clickedTile,1);
		}
		catch(NullPointerException e)
		{
			deselectAll();
		}
		
	}

	
	private void rightMousePressed(Point mp)
	{
		Tile clickedTile = mapAnalyzer.getPointedTile(mp);
		dehighlightAll();
		try 
		{
			player.move3(mp);
		}
		catch(NullPointerException e)
		{
			deselectAll();
		}
	}

	
	/*
	 * 
	 */
	private void resetAdjacentTiles()
	{
		for(int row = 0; row<mapLayer1.length;row++)
		{	
			for(int column=0;column<mapLayer1[row].length;column++)
			{	
				
				mapLayer1[row][column].setAdjacent(false);
			}	
		}
	}
	/*
	 * deselects all tiles
	 */
	private void deselectAll()
	{
		for(int row = 0; row<mapLayer1.length;row++)
		{	
			for(int column=0;column<mapLayer1[row].length;column++)
			{	
				mapLayer1[row][column].setSelected(false);
			}	
		}
	}

	/*
	 * deselects all tiles
	 */
	private void dehighlightAll()
	{
		for(int row = 0; row<mapLayer1.length;row++)
		{	
			for(int column=0;column<mapLayer1[row].length;column++)
			{	
				mapLayer1[row][column].setHighlighted(false);
			}	
		}
	}
	
	/**
	 * takes a point and returns the tile it is in
	 * 
	 */
	public Tile getPointedTile(Point mp)
	{
		boolean inArea = false;
		Tile tileSelected = null;
		
		for(int row = 0; row<mapLayer1.length;row++)
		{	
				for(int column=0;column<mapLayer1[row].length;column++)
				{	
					inArea = mapLayer1[row][column].getClickDiamond().contains(mp.getX(),(mp.getY()));
					if(inArea == true&&mapLayer1[row][column].isClickable())
				{
						tileSelected = mapLayer1[row][column];
				}
					inArea = false;
			}	
		}
		return tileSelected;
	}
	
//SET TILE SELECTED
	
//	Sets tile selected and also deselects tile if it is currently selected
//	change to work with right and left selectable elements
	private void setSelectedTile(int x, int y)
	{

		if(selectedY==x&&selectedX==y&&tileReselected == false)
		{
			mapLayer1[selectedY][selectedX].setSelected(false);
			tileReselected = true;
		}
		else
		{
			mapLayer1[selectedY][selectedX].setSelected(false);
			selectedY = x;
			selectedX = y;
			mapLayer1[selectedY][selectedX].setSelected(true);
			tileReselected = false;
		}
		

	}

	
//SET TILES	
	private void setTiles(Tile[][] map, Integer[] arr) {
		int arrGet = 0;
		int rowNumber = 0;
		int tileNumber = 0;
		for(int row = 0; row<map.length;row++)
		{	
			tileNumber = 0;
			for(int column=0;column<map[row].length;column++)
			{
				
				map[row][column].setXpos(arr[arrGet].intValue());
				map[row][column].setYpos(arr[arrGet+1].intValue()+1);
				map[row][column].createClickDiamond(arr[arrGet].intValue(),arr[arrGet+1].intValue()+1);
				map[row][column].getClickDiamond().invalidate();
//				if(map[row][column].isClickable())
				map[row][column].setColumnNumber(tileNumber);
				map[row][column].setRowNumber(rowNumber);
				map[row][column].setStandingPoints();
				tileNumber++;
//				System.out.println(map[row][column].getTileNumber());
//				System.out.println(map[row][column].getXpos()+", "+map[row][column].getYpos());
//				System.out.println();		
				arrGet+=2;
			}	
			rowNumber++;
		}
	}

	

	
//CREATE SQUARE TILE	
	private Integer[] squareTile(int screenSizeX,int screenSizeY,int length)
	{
		int rectCross = length/2+1;
		ArrayList<Integer> xy = new ArrayList<Integer>();
		int centerX = screenSizeX/2-32;
		int centerY = screenSizeY/2-(rectCross*16);
		int x = centerX;
		int y = centerY;
		boolean forward = true;
		int rowLength = 1;
		for(int i = 0; i<length;i++ )
		{
			for(int j=0;j<rowLength;j++)
			{
				xy.add(x);
				xy.add(y-32);
				x+=64;
			}

				if(rowLength == rectCross)
				{
					forward = false;
				}
				if(forward == true)
				{
					rowLength++;
				}
				else
				{
					rowLength--;
				}
				x = centerX-(rowLength*32)+32;
				y+=16;
		}
		
		return xy.toArray(new Integer[xy.size()]);	
	}
	
	public void setCharacterTileOn(Characters ch)
	{
		
	}
	
//MoveClickTiles
	private void moveClickTiles()
	{
		
		for(int row = 0; row<mapLayer1.length;row++)
		{	
			for(int column=0;column<mapLayer1[row].length;column++)
			{	
				mapLayer1[row][column].moveClickDiamond(cameraX,cameraY);
//				mapLayer1[row][column].getClickDiamond().invalidate();
			}	
		}
		

	}
	
//SET DRAW COLOR	
	private Color setColor(int c)
	{
		
		switch(c)
		{
		case 1:
			return Color.GREEN;

		case 2:
			return Color.RED;
		default:
			return Color.RED;
		}
	}
	
	
	
//PAINT	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//draw background
		drawBG(g);
		//draw layer 1
		drawMap(g,mapLayer1);
		//draw layer 2
		drawHighlights(g);
		
		//create layer 3 characters
		drawCharacters(g);
		//draw layer 4
		drawMap(g,mapLayer4);
		
		drawBottomMenu(g);

		drawStandingPoints(g);
		
//		Toolkit.getDefaultToolkit().sync();
	}
//END OF PAINT
	
	//draw map loops
	private void drawMap(Graphics g, Tile[][] map)
	
	{
		for(int row = 0; row<map.length;row++)
		{	
			for(int column=0;column<map[row].length;column++)
			{	
				g.drawImage(map[row][column].getTile(),map[row][column].getXpos()+cameraX,map[row][column].getYpos()+cameraY,null);
			}	
		}
	}
	
	
	//Draw highlights
	private void drawHighlights(Graphics g)
	{
		
		
		moveClickTiles();
		for(int row = 0; row<mapLayer1.length;row++)
		{	
			for(int column=0;column<mapLayer1[row].length;column++)
			{	
				//Selected tile
				if(mapLayer1[row][column].isSelected()&&mapLayer1[row][column].isClickable())
				{

					g.setColor(setColor(2));
					g.drawPolygon(mapLayer1[row][column].getClickDiamond());
				}
				if(mapLayer1[row][column].isHighlighted())
				{
					g.setColor(setColor(1));
					g.drawPolygon(mapLayer1[row][column].getClickDiamond());
				}
				if(mapLayer1[row][column].isAdjacent())
				{
					g.setColor(setColor(1));
					g.drawChars(mapLayer1[row][column].getPrintDirection(),0,mapLayer1[row][column].getPrintDirection().length,mapLayer1[row][column].getXpos()+25,mapLayer1[row][column].getYpos()+50);
					g.drawPolygon(mapLayer1[row][column].getClickDiamond());
				}
			}	
		}
	}

	
//Characters
	private void drawCharacters(Graphics g)
	{
		g.drawImage(player.getSprite(),player.getxPos()+cameraX,player.getyPos(),null);
	}
	
	private void drawBottomMenu(Graphics g)
	{
		g.drawImage(bottomMenu.getBase(),0, windowY-225,null);
	}
	
	private void drawBG(Graphics g)
	{
		for(int i = -600; i<1320;i+=300)
		{
			g.drawImage(treeBG,i+cameraX,-30+cameraY,null);
			g.drawImage(treeBG,i+cameraX,mapLayer1[0][0].getYpos()-100+cameraY,null);
			g.drawImage(treeBG,i+cameraX,mapLayer1[0][0].getYpos()+100+cameraY,null);
			g.drawImage(treeBG,i+cameraX,mapLayer1[0][0].getYpos()+300+cameraY,null);
		}
	}
	
//StandingPoints
	private void drawStandingPoints(Graphics g)
	{
		g.setColor(setColor(2));
		for(Point el: mapAnalyzer.getTravelPoints())
		{
			g.drawOval(el.x, el.y, 1, 1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		
//		if(player.isMoving()&&rightSelected instanceof Tile)
//		{
//			
//
//				if(!player.getTileOn().equals(rightSelected)) 
//				{	
//					player.battleMove(mapAnalyzer.closestTileTo(player.getTileOn(), (Tile)rightSelected));
//					playerSteps ++;
//				}
//				else
//				{
//				System.out.println(playerSteps);
//				player.setMoving(false);
//				playerSteps = 0;
//				}
//
//		}
//		repaint();
	}
	
	

}
