package dijkstra;

import java.util.ArrayList;

import maze.ABox;
import maze.Dbox;
import maze.Maze;

public class Dijkstra {
	
	// Méthode qui permet de déterminer le point de départ du labyrinthe
		public static VertexInterface determinateDeparture(Maze maze){
			Boolean test= false;
			int i=-1;
			int j =-1;
			int WIDTH= maze.getWidth();
			int HEIGHT=maze.getHeight();
			while (i<WIDTH && test ==false){
				i=i+1;
				while (j<HEIGHT && test==false){
					j=j+1;
					if (maze.getSymbolForBox(i,j)=="D"){
						test=true;
					}
				}
			}
			VertexInterface r = new Dbox(maze, i, j);
			return (r);
		}
		
			// Méthode qui permet de déterminer le point d'arrivée du labyrinthe
			public static VertexInterface determinateArrival(Maze maze){
				Boolean test= false;
				int i=-1;
				int j =-1;
				int WIDTH= maze.getWidth();
				int HEIGHT=maze.getHeight();
				while (i<WIDTH && test ==false){
					i=i+1;
					while (j<HEIGHT && test==false){
						j=j+1;
						if (maze.getSymbolForBox(i,j)=="A"){
							test=true;
						}
					}
				}
			VertexInterface r = new ABox(maze, i, j);
			return (r);
		}

	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
		return dijkstra(g, r, new ASet(), new Pi(), new Previous());
	}

	private static PreviousInterface dijkstra(GraphInterface g,
			VertexInterface r, AsetInterface a, PiInterface pi,
			PreviousInterface previous) {
		ArrayList<VertexInterface> allVertices = g.getAllVertices();
		int n = allVertices.size();
		a.add(r);

		for (VertexInterface x : allVertices)
			pi.setValue(x, Integer.MAX_VALUE);
		pi.setValue(r, 0);

		VertexInterface pivot = r;
		int piPivot = 0;

		for (int i = 1; i < n; i++) {
			ArrayList<VertexInterface> pivotSuccessors = g.getSuccessors(pivot);
			for (VertexInterface y : pivotSuccessors)
				if (!a.contains(y)) {
					int newPi = piPivot + g.getWeight(pivot, y);
					if (newPi < pi.getValue(y)) {
						pi.setValue(y, newPi);
						previous.setValue(y, pivot);
					}
				}
			VertexInterface newPivot = null;
			int piNewPivot = Integer.MAX_VALUE;
			for (VertexInterface v : allVertices)
				if (!a.contains(v)) {
					int piV = pi.getValue(v);
					if (piV < piNewPivot) {
						newPivot = v;
						piNewPivot = piV;
					}
				}
			if (newPivot == null)
				return previous;

			pivot = newPivot;
			piPivot = piNewPivot;
			a.add(pivot);
		}
		return previous;
	}

}
