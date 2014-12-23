package dijkstra;


public interface AsetInterface {
	/** Ajouter un sommet � l'ensemble A. */
	
	public void add(VertexInterface vertex);
	
	/** Tester si un sommet est dans A */
	
	public boolean contains(VertexInterface vertex);

}
