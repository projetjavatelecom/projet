package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {
	/** Donner le résultat pour un sommet */

	public void setValue(VertexInterface vertex, VertexInterface value);

	/**
	 * Retourner la valeur pour un sommet ou null si jamais le père n'est pas
	 * encore déclaré
	 */

	public VertexInterface getValue(VertexInterface vertex);

	/** Retourner le chemin le plus court de la racine à un sommet donné */

	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);

}
