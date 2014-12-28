package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {
	/** Donner le r�sultat pour un sommet */

	public void setValue(VertexInterface vertex, VertexInterface value);

	/**
	 * Retourner la valeur pour un sommet ou null si jamais le p�re n'est pas
	 * encore d�clar�
	 */

	public VertexInterface getValue(VertexInterface vertex);

	/** Retourner le chemin le plus court de la racine � un sommet donn� */

	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);

}
