package dijkstra;

import java.util.ArrayList;

import maze.ABox;
import maze.Dbox;
import maze.Maze;

public class Dijkstra {

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
