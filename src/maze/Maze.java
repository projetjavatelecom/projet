package maze;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public class Maze implements GraphInterface {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;

	private final MBox[][] boxes;

	public Maze() {
		boxes = new MBox[HEIGHT][WIDTH];
	}

	public final MBox getBox(int line, int column) {
		return boxes[line][column];
	}

	public final ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> allVertices = new ArrayList<VertexInterface>();

		for (int line = 0; line < HEIGHT; line++) {
			MBox[] theLine = boxes[line];
			for (int column = 0; column < WIDTH; column++)
				allVertices.add(theLine[column]);
		}
		return allVertices;

	}

	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();

		MBox box = (MBox) vertex; // cast

		int line = box.getLine();
		int column = box.getColumn();

		if (line > 0) {// voisin du haut
			MBox neighbor = boxes[line - 1][column];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}

		if (line < HEIGHT) {// voisin du bas
			MBox neighbor = boxes[line + 1][column];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}

		if (column > 0) {// voisin de gauche
			MBox neighbor = boxes[line][column - 1];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}

		if (column < WIDTH) {// voisin de droite
			MBox neighbor = boxes[line][column + 1];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}

		return successors;
	}

	public final int getWeight(VertexInterface src, VertexInterface dst) {
		return 1;
	}

	public final void initFromTextFile(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			for (int lineNo = 0; lineNo < HEIGHT; lineNo++) {
				String line = br.readLine();
				if (line == null)
					throw new MazeReadingException(fileName, lineNo,
							"pas assez de lignes");
				if (line.length() < WIDTH)
					throw new MazeReadingException(fileName, lineNo,
							"ligne trop courte");
				if (line.length() > WIDTH)
					throw new MazeReadingException(fileName, lineNo,
							"ligne trop longue");

				for (int colNo = 0; colNo < WIDTH; colNo++) {
					switch (line.charAt(colNo)) {
					case 'D':
						boxes[lineNo][colNo] = new Dbox(this, lineNo, colNo);
						break;
					case 'A':
						boxes[lineNo][colNo] = new ABox(this, lineNo, colNo);
						break;
					case 'W':
						boxes[lineNo][colNo] = new WBox(this, lineNo, colNo);
						break;
					case 'E':
						boxes[lineNo][colNo] = new EBox(this, lineNo, colNo);
						break;
					default:
						throw new MazeReadingException(fileName, lineNo,
								"unknown char'" + boxes[lineNo][colNo] + "'");
					}
				}
			}

		} catch (MazeReadingException e) {
			System.err.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.err
					.println("Erreur classe Maze, initFromTextFile: fichier non trouvé\""
							+ fileName + "\"");
		} catch (IOException e) {
			System.err
					.println("Erreur classe Maze, initFromTextFile: erreur de lecture du fichier\""
							+ fileName + "\"");
		} catch (Exception e) {
			System.err.println("Erreur: erreur inconnue");
			e.printStackTrace(System.err);
		} finally {
			if (fr != null)
				try {
					fr.close();
				} catch (Exception e) {
				}
			;

			if (br != null)
				try {
					br.close();
				} catch (Exception e) {
				}
			;
		}
	}

	public final void saveToTextFile(String fileName) {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(fileName);

			for (int lineNo = 0; lineNo < HEIGHT; lineNo++) {
				MBox[] line = boxes[lineNo];
				for (int colNo = 0; colNo < WIDTH; colNo++) {
					line[colNo].writeCharTo(pw);
				}
					pw.println();
			}
		} catch (FileNotFoundException e) {
			System.err
					.println("Erreur classe Maze, saveToTextFile: fichier non trouvé\""
							+ fileName + "\"");
		} catch (SecurityException e) {
			System.err
					.println("Erreur classe Maze, saveToTextFile: exception de sécurité\""
							+ fileName + "\"");
		} catch (Exception e) {
			System.err.println("Erreur: erreur inconnue");
			e.printStackTrace(System.err);
		} finally {
			if (pw != null)
				try {
					pw.close();
				} catch (Exception e) {
				}
		}

	}
}
