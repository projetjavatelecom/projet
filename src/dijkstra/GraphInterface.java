package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
	/** Donner le tableau de tous les sommets */

	public ArrayList<VertexInterface> getAllVertices();

	/** Retourner le tableau de tous les successeurs d'un sommet */

	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);

	/** Retourner le poids d'un arc */

	public int getWeight(VertexInterface src, VertexInterface dst);

}
