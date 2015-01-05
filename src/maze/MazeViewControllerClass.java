package maze;

import java.util.ArrayList;

import dijkstra.Dijkstra;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeViewSource;

public class MazeViewControllerClass implements MazeViewController {
	// creation d'une nouvelle classe qui implemente MazeViewController
	private Maze maze = new Maze(); // creation d'un nouveau labyrinthe
	
	
	
	

	@Override
	public void calculateShortestPath() {
		// On détermine le point de départ du labyrinthe
		VertexInterface r= Dijkstra.determinateDeparture(maze); 
		
		//On détermine le point d'arrivée du labyrinthe
		VertexInterface a=Dijkstra.determinateArrival(maze);
		// On lance l'algorithme de Dijkstra pour récuperer le previous
		PreviousInterface previous = Dijkstra.dijkstra(maze,r);
		// On détermine le chemin entre le départ et l'arrivée
		ArrayList<VertexInterface> chemin=previous.getShortestPathTo(a);
		}

	@Override
	public MazeViewSource getMazeViewSource() {
		return maze;
	}

	@Override
	public MazeViewSource newMaze() {
		maze.initialize();
		return maze ; 
	}

	@Override
	public MazeViewSource openMaze(String fileName) {
		maze.initFromTextFile(fileName); // on ouvre le labyrinthe a partir d'un fichier texte ou d'un labyrinthe deja enregistre
		return maze;
		
		
	
	}

	@Override
	public void saveMazeAs(String fileName) {
	     maze.saveToTextFile(fileName); // sauvegarde du labyrinthe
		
	}

}
