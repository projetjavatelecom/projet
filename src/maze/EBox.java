package maze;

import java.io.PrintWriter;

public class EBox extends MBox {

	public EBox(Maze maze, int line, int column) {
		super(maze, line, column);
		// TODO Auto-generated constructor stub
	}

	public String afficherLettre() {
		return "E";
	}

	// Ajout d'une m�thode pour afficher la lettre associ�e

	public final void writeCharTo(PrintWriter pw) {
		pw.print('E');
	}
}
