package dijkstra;

public interface PiInterface {

	/** Retourner le r�sultat de Pi pour un sommet donn� */

	public void setValue(VertexInterface vertex, int value);

	/** Returns th value for a vertex */

	public int getValue(VertexInterface vertex);

}
