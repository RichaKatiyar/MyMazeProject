package org.maze.impl;

import java.util.ArrayList;

import java.util.List;

import org.maze.exception.MovementBlockedException;
import org.maze.interfaces.Explorer;
import org.maze.interfaces.Maze;
import org.maze.model.HeadingDirectionClockWise;
import org.maze.model.MazeCoordinate;
import org.maze.model.MazeStructure;
import org.maze.position.ExplorerPosition;

/**
 * Thread safe implementation of a an explorer of the maze
 */
public class MazeExplorer implements Explorer {

	protected final Maze maze;

	public List<MazeCoordinate> movement;

	public ExplorerPosition position;

	public MazeExplorer(Maze maze) {
		this(maze, HeadingDirectionClockWise.UP);
	}

	public MazeExplorer(Maze maze, HeadingDirectionClockWise startingDirection) {
		this.maze = maze;
		position = new ExplorerPosition(maze.getStartLocation(), startingDirection);
		this.movement = new ArrayList<>();
		this.movement.add(maze.getStartLocation());
	}

	protected void movingToHook(MazeCoordinate mazeCoordinate) {
		// do nothing here, provide it for extendability
	}

	@Override
	public synchronized final void moveForward() {
		ExplorerPosition newPosition = position.calculateForwardPositionInMaze(maze);
		if (maze.whatsAt(newPosition.getCoordinate()).canBeExplored()) {
			movingToHook(newPosition.getCoordinate());
			this.movement.add(newPosition.getCoordinate());
			this.position = newPosition;
		} else {
			throw new MovementBlockedException(newPosition.getCoordinate());
		}
	}

	@Override
	public synchronized final void turnLeft() {
		position = position.turnLeft();
	}

	@Override
	public synchronized final void turnRight() {
		position = position.turnRight();
	}

	@Override
	public synchronized final void turnTo(HeadingDirectionClockWise direction) {
		position = position.withDirection(direction);
	}

	@Override
	public synchronized final MazeStructure whatsAtMyLocation() {
		return maze.whatsAt(position.getCoordinate());
	}

	public synchronized final MazeStructure setMyLocation() {
		return maze.whatsAt(position.getCoordinate());
	}

	@Override
	public synchronized List<MazeCoordinate> getMovement() {
		return new ArrayList<>(movement);
	}

	@Override
	public synchronized ExplorerPosition getPosition() {
		return position;
	}

	@Override
	public void setPosition(ExplorerPosition position) {
		this.position = position;
	}

}
