package org.maze.exception;

import org.maze.model.MazeCoordinate;

public class MovementBlockedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovementBlockedException(MazeCoordinate location) {
        super(String.format("Movement to location %s is blocked!", location));
    }
}
