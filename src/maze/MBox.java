package maze;

import java.io.PrintWriter;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {

	private final Maze maze;
	private final int line;
	private final int column;

	public abstract void writeCharTo(PrintWriter pw);

	public boolean isAccessible() {
		return true;
	}

	public final String getLabel() {
		return "(" + line + "," + column + ")";
	}

	public MBox(Maze maze, int line, int column) {

		this.maze = maze;
		this.line = line;
		this.column = column;
	}

	public final int getLine() {
		return line;
	}

	public final int getColumn() {
		return column;
	}

	public String afficherLettre() {
		// TODO Auto-generated method stub
		return null;
	}

}
