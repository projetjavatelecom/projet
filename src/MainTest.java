import java.util.Scanner;

import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeWindow;
import maze.Maze;
import maze.MazeViewControllerClass;


public class MainTest {

	public static void main(String[] args) {
		
Maze maze = new Maze() ; 
		
        Scanner sc = new Scanner(System.in) ; 
		System.out.println("Saisissez la largeur du labyrinthe") ; // il faut saisir la largeur dans la console
		int largeur = sc.nextInt();
		maze.setWIDTH(largeur); 
		System.out.println("Saisissez la hauteur du labyrinthe") ; // il faut saisir la hauteur dans la console
		int hauteur = sc.nextInt();
		maze.setHEIGHT(hauteur);
		
		
		
		
		MazeViewController mazeController= new MazeViewControllerClass();
		MazeWindow mazeWindow = new MazeWindow("My awesome labyrinth", mazeController) ;

	}

}
