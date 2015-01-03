package maze;
// creation d'une classe fille de Exception 
//creation d'une nouvelle exception SymbolException pour la méthode setSymbolForBox dans la classe Maze 

public class SymbolException extends Exception {
	public SymbolException(String message) {
		super (message) ; 
	}

}
