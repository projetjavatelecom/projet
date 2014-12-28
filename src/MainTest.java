import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeWindow;
import maze.Maze;
import maze.MazeViewControllerClass;


public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Maze maze= new Maze();
		
		maze.initFromTextFile("data/labyrinthe.txt");
		maze.saveToTextFile("data/labyrinthe2.txt");
		
		MazeViewController mazeController= new MazeViewControllerClass();
		MazeWindow mazeWindow = new MazeWindow("My awesome labyrinth", mazeController) ;

	}

}
