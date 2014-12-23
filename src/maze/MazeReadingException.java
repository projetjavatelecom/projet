package maze;

public class MazeReadingException extends Exception
{
	public static final long serialVersionUID=1L;
	public MazeReadingException(String fileName, int lineNo, String errorMsg)
	{
		super("Erreur pendant la lecture de Maze dans"+fileName+"("+lineNo+")"+errorMsg);
	}
}
