package maze;
import java.io.PrintWriter;

public class EBox extends MBox 
{

	public EBox(Maze maze, int line, int column) 
	{
		super(maze, line, column);
		// TODO Auto-generated constructor stub
	}

	public final void writeCharTo(PrintWriter pw) 
	{
		pw.print('E');
	}
}
