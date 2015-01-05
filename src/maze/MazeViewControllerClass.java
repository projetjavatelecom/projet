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
		// On d�termine le point de d�part du labyrinthe
		VertexInterface r= Dijkstra.determinateDeparture(maze); 
		
		//On d�termine le point d'arriv�e du labyrinthe
		VertexInterface a=Dijkstra.determinateArrival(maze);
		// On lance l'algorithme de Dijkstra pour r�cuperer le previous
		PreviousInterface previous = Dijkstra.dijkstra(maze,r);
		// On d�termine le chemin entre le d�part et l'arriv�e
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
