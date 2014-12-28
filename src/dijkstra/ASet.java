package dijkstra;

import java.util.HashSet;

public class ASet implements AsetInterface {
	private final HashSet<VertexInterface> aSet;

	public ASet() {
		aSet = new HashSet<VertexInterface>();
	}

	public void add(VertexInterface vertex) {
		aSet.add(vertex);
	}

	public boolean contains(VertexInterface vertex) {
		return aSet.contains(vertex);
	}

}
