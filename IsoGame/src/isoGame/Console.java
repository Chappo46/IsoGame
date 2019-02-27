package isoGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a text console to monitor events in the game.
 * @author Patrick Murphy
 *
 */

public class Console {
	
	List<String> contents = new ArrayList<String>();
	
	public Console()
	{
		cAction("New Session");
	}
	
	/**
	 * Adds a string to the console with the current time and date.
	 * @param str
	 */
	public void cAction(String str)
	{
		contents.add(String.format("%s > %s%n", java.time.LocalDateTime.now(),str));
	}
	
	/**
	 * Prints the contents of the console.
	 */
	public void printConsole()
	{
		for(String el: contents)
		{
			System.out.print(el);
		}
	}
}
