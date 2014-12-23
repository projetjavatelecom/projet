package maze;
import java.io.PrintWriter;


public class WBox extends MBox{

	public WBox(Maze maze, int line, int column)
	{
		super(maze , line, column);
		// TODO Auto-generated constructor stub
	}
	@Override
	public final boolean isAccessible()
	{
		return false;
	}
	
	public final void writeCharTo(PrintWriter pw)
	{
		pw.print('W');
	}

}
