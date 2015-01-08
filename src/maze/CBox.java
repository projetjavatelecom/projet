package maze;

import java.io.PrintWriter;

public class CBox extends MBox {

	public CBox(Maze maze, int line, int column) {
		super(maze, line, column);
		// TODO Auto-generated constructor stub
	}

	public String afficherLettre() {
		return "*";
	}

	// Ajout d'une m�thode pour afficher la lettre associ�e

	public final void writeCharTo(PrintWriter pw) {
		pw.print('*');
	}

}
