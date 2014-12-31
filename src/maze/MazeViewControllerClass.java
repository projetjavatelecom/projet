package maze;

import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeViewSource;

public class MazeViewControllerClass implements MazeViewController {
	// creation d'une nouvelle classe qui implemente MazeViewController
	private Maze maze = new Maze(); // creation d'un nouveau labyrinthe
	
	
	
	

	@Override
	public void calculateShortestPath() {
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
