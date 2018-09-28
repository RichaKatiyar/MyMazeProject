package org.maze.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.maze.impl.MazeExplorer;
import org.maze.impl.MazeImpl;
import org.maze.interfaces.Explorer;
import org.maze.model.HeadingDirectionClockWise;
import org.maze.model.MazeCoordinate;
import org.maze.model.MazeStructure;
import org.maze.position.ExplorerPosition;
import org.apache.log4j.Logger;

/**
 * @author Richa Katiyar
 *
 */
/**
 * @author Richa Katiyar
 *
 */
public class MazeMain {
	
	 private static final Logger LOGGER = Logger.getLogger(MazeMain.class);
	
	 public static void main(String[] args) throws IOException { 

		 LOGGER.debug("The example maze in file Maze.txt should be initialized correctly");
		 
		/*
		 * As a world famous explorer of Mazes I would like a maze to exist So that I
		 * can explore it
		 */
		 
		 String mazeStr = loadMaze ();	
	   
	   	LOGGER.info("mazeStr: " + "\n" + mazeStr);
     
	     MazeImpl maze = new MazeImpl(mazeStr);
	     
	    //initializing maze with correct startLocation 
		 maze.startLocation = new MazeCoordinate(3, 3);
		 
		 //Maze created with number of walls and number of spaces
		 LOGGER.info("Maze start location: " + maze.getStartLocation());
		 LOGGER.info("No of walls: " + maze.getNumberOfWalls());
		 LOGGER.info("No of empty spaces: " + maze.getNumberOfEmptySpaces());
		 LOGGER.info("Maze Exit Location: " + maze.getExitLocation());

		 
		/*		 After a maze has been created I should be able to put in a co ordinate 
		* and know what exists at that point */
						  
		 MazeCoordinate coord = new MazeCoordinate(4,3);
		 MazeStructure mazeStructure = getValueAtCoordinate(maze,coord);
		 
		 LOGGER.info("What is at the coordinate(4,3): " + mazeStructure);
	
		 	 
		/*
		 * An explorer on a maze must be able to: Move forward Turn left and right
		 * Understand what is in front of them Understand all movement options from
		 * their given location Have a record of where they have been
		 */
		 int movementSize =  exploreMaze(maze);
	  	 LOGGER.info("Number of movements explorer took:: " + movementSize); 
		
		 
		 
	   }

	  /** 
	 * Load Maze.txt file to explore the maze
	 * @return the maze as a string
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String loadMaze () throws IOException {
		  
		  File file = new File("Maze.txt");

		  BufferedReader br = new BufferedReader(new FileReader(file));
		 
		  StringBuilder sb = new StringBuilder();
	      String line = br.readLine();
		 
	      while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
  
	    }
	  

	  /**
	   * Finding the value exists at provided coordinates
	 * @param maze
	 * @param coord
	 * @return value exists at given coordinate
	 */
	public static MazeStructure getValueAtCoordinate(MazeImpl maze, MazeCoordinate coord) {
		return  maze.whatsAt(coord);
	  }
	
	
	  /**
	 * Explorer on maze moving in all directions
	 * @param maze
	 * @return movementSize
	 */
	public static int exploreMaze( MazeImpl maze) {
		
		Explorer explorer = new MazeExplorer(maze);
		
		/* Given a maze the explorer should be able to drop in to the Start point **/
		 ExplorerPosition position = new ExplorerPosition(new MazeCoordinate(3, 3), HeadingDirectionClockWise.UP);
		 explorer.setPosition(position);
		 
		 	explorer.turnRight();
	        explorer.moveForward(8);
	        explorer.turnRight();
	        explorer.moveForward(3);
	        explorer.turnRight();
	        explorer.moveForward(5);
	        explorer.turnLeft();
	        explorer.moveForward(3);
	        explorer.turnRight();
	        explorer.moveForward(3);
	        explorer.turnLeft();
	        explorer.moveForward(3);
	        explorer.turnLeft();
	        explorer.moveForward(8);
	        explorer.turnRight();
	        explorer.moveForward(1);
	        explorer.turnLeft();
	        explorer.moveForward(2);
	        explorer.turnLeft();
	        explorer.moveForward(12);
	        explorer.turnLeft();
	        explorer.moveForward(12);
	        explorer.turnLeft();
	        explorer.moveForward(13);
		   
		return explorer.getMovement().size();
	}
}
