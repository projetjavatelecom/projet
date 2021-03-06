package maze;

import interfaceGraphique.Fenetre;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import fr.enst.inf103.ui.MazeView;
import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeViewSource;

public class Maze implements GraphInterface, MazeViewSource {
	public static int WIDTH = 10;
	public static int HEIGHT = 10;

	public void setWIDTH(int newWIDTH) throws WIDTHException {
		if (newWIDTH <= 0) {
			Fenetre fenetre = new Fenetre("Largeur negative !"); // affiche une
																	// fenetre
																	// d'erreur
			throw new WIDTHException("WIDTH is undefined ");
		}
		WIDTH = newWIDTH;
	}

	public void setHEIGHT(int newHEIGHT) throws HEIGHTException {
		if (newHEIGHT <= 0) {
			Fenetre fenetre = new Fenetre("hauteur négative !"); // affiche une
																	// fenetre
																	// d'erreur
			throw new HEIGHTException("HEIGHT is undefined");
		}
		HEIGHT = newHEIGHT;
	}

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

		MBox box = (MBox) vertex;

		int line = box.getLine();
		int column = box.getColumn();

		if (line > 0) {
			MBox neighbor = boxes[line - 1][column];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}
		if (line < HEIGHT - 1) {
			MBox neighbor = boxes[line + 1][column];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}

		if (column > 0) {
			MBox neighbor = boxes[line][column - 1];
			if (neighbor.isAccessible())
				successors.add(neighbor);
		}

		if (column < WIDTH - 1) {
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
							"not enough lines");
				if (line.length() < WIDTH)
					throw new MazeReadingException(fileName, lineNo,
							"line too short");
				if (line.length() > WIDTH)
					throw new MazeReadingException(fileName, lineNo,
							"line too long");

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
								"unknown char '" + boxes[lineNo][colNo] + "'");
					}
				}
			}

		} catch (MazeReadingException e) {
			System.err.println(e.getMessage());
		}

		catch (FileNotFoundException e) {
			System.err
					.println("Error class Maze, initFromTextFile: file not found\""
							+ fileName + "\"");
		}

		catch (IOException e) {
			System.err
					.println("Error class Maze, initFromTextFile: read error on file \""
							+ fileName + "\"");
		}

		catch (Exception e) {
			System.err.println("Error: unknown error.");
			e.printStackTrace(System.err);
		}

		finally {
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
				for (int colNo = 0; colNo < WIDTH; colNo++)
					line[colNo].writeCharTo(pw);
				pw.println();
			}
		}

		catch (FileNotFoundException e) {
			System.err
					.println("Error class Maze, saveToTextFile: file not found\""
							+ fileName + "\"");
		}

		catch (IOException e) {
			System.err
					.println("Error class Maze, saveToTextFile: security exception \""
							+ fileName + "\"");
		}

		catch (Exception e) {
			System.err.println("Error: unknown error.");
			e.printStackTrace(System.err);
		} finally {
			if (pw != null)
				try {
					pw.close();
				} catch (Exception e) {
				}
			;
		}

	}

	@Override
	public boolean drawMaze(Graphics g, MazeView mazeView) {

		return false;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public String getSymbolForBox(int line, int column) {
		if (boxes[line][column] != null) {
			return boxes[line][column].afficherLettre();
		}
		;
		return "";

	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public boolean handleClick(MouseEvent e, MazeView mazeView) {
		return false;
	}

	@Override
	public boolean handleKey(KeyEvent e, MazeView mazeView) {

		return false;
	}

	// methode qui permet de modifier une case du labyrinthe 
	// A:arrival , D:depart , E:empty , W:wall ; *:yellow box for the path in method calcultateShortestPath
	@Override
	public void setSymbolForBox(int line, int column, String symbol) {
		try {
			switch (symbol) {
			case "A":
				boxes[line][column] = new ABox(this, line, column);
				break;
			case "D":
				boxes[line][column] = new Dbox(this, line, column);
				break;
			case "E":
				boxes[line][column] = new EBox(this, line, column);
				break;
			case "W":
				boxes[line][column] = new WBox(this, line, column);
				break;
			case "*":
				boxes[line][column] = new CBox(this, line, column);
				break;
			default:
				throw new SymbolException(symbol + " is an unknown symbol");

			}
		}

		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	// methode qui permet d'initialiser un nouveau labyrinthe avec des murs sur
	// tous les bords

	public void initialize() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				boxes[i][j] = new WBox(this, i, j);
			}
		}
		for (int i = 1; i < HEIGHT - 1; i++) {
			for (int j = 1; j < WIDTH - 1; j++) {
				boxes[i][j] = new EBox(this, i, j);
			}
		}

	}

	// methode qui determine le depart du labyrinthe

	public final Dbox determinateDeparture() throws MazeException {
		Dbox depart = null;
		int count = 0;

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (boxes[i][j].afficherLettre().equals("D")) {
					depart = (Dbox) boxes[i][j];
					count++;

				}

			}
		}
		if (count > 1) {
			Fenetre fenetre = new Fenetre("Il y a plusieurs cases de depart !");
			throw new MazeException("Il y a plusieurs cases de depart");
		}
		if (depart == null) {
			Fenetre fenetre = new Fenetre("Pas de case de depart !");
			throw new MazeException("Pas de case de depart !");
		}
		return depart;

	}

	// methode qui determine l'arrivee

	public final ABox determinateArrival() throws MazeException {
		ABox arrivee = null;
		int count = 0;

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (boxes[i][j].afficherLettre().equals("A")) {
					arrivee = (ABox) boxes[i][j];
					count++;
				}

			}
		}
		// traitement des exceptions
		if (count > 1) {
			Fenetre fenetre = new Fenetre("Il y a plusieurs cases d'arrivee !");
			throw new MazeException("Il y a plusieurs cases d'arrivee");
		}
		if (arrivee == null) {
			Fenetre fenetre = new Fenetre("pas de case d'arrivee");
			throw new MazeException("pas de case d'arrivee");
		}
		return arrivee;
	}
}
