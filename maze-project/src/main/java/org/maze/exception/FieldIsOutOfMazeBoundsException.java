package org.maze.exception;

public class FieldIsOutOfMazeBoundsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FieldIsOutOfMazeBoundsException() {
        super("Field is out of the maze!");
    }
}
