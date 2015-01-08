package maze;

import java.util.ArrayList;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeViewSource;

public class MazeViewControllerClass implements MazeViewController {
	// creation d'une nouvelle classe qui implemente MazeViewController
	private Maze maze = new Maze(); // creation d'un nouveau labyrinthe

	@Override
	public void calculateShortestPath() {

		// On recherche d'abord le point de depart et le point d'arrivee

		try {

			VertexInterface r = maze.determinateDeparture();

			VertexInterface a = maze.determinateArrival();

			// Ensuite on fait tourner dijikstra pour avoir le tableau previous

			PreviousInterface previous = Dijkstra.dijkstra(maze, r);

			// Maintenant on va chercher a determiner le chemin entre r et a

			ArrayList<VertexInterface> shortestPath = previous
					.getShortestPathTo(a);

			for (VertexInterface vertex : shortestPath) {
				MBox box = (MBox) vertex;

				int i = box.getLine();
				int j = box.getColumn();

				maze.setSymbolForBox(i, j, "*");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	public MazeViewSource getMazeViewSource() {
		return maze;
	}

	@Override
	public MazeViewSource newMaze() {
		maze.initialize();
		return maze;
	}

	@Override
	public MazeViewSource openMaze(String fileName) {
		maze.initFromTextFile(fileName); // on ouvre le labyrinthe a partir d'un
											// fichier texte ou d'un labyrinthe
											// deja enregistre
		return maze;

	}

	@Override
	public void saveMazeAs(String fileName) {
		maze.saveToTextFile(fileName); // sauvegarde du labyrinthe

	}

}
