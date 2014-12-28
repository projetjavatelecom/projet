package dijkstra;

public interface PiInterface {

	/** Retourner le résultat de Pi pour un sommet donné */

	public void setValue(VertexInterface vertex, int value);

	/** Returns th value for a vertex */

	public int getValue(VertexInterface vertex);

}
