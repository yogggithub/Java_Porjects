package game.wordsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class WordGrid {
	private int row;
	private int column;
	private char[][] grid;
	private String[] words;
	private BufferedReader loadGrid;
	private BufferedReader loadWordsDB;
	
	/**
	 * Within the no-arg constructor, generate two file input stream
	 * to product the Grid and buffer the Words List
	 * Make sure that whenever the Game is start, the application will
	 * get the maze and answer ready.
	 * 
	 * @throws IOException
	 *                     The file path the user input might be incorrect.
	 */
	public WordGrid(String gridFile, String wordDB) throws IOException {
		loadGrid = readFile(gridFile);
		loadWordsDB = readFile(wordDB);
		launchGrid();
		getWords();
	}

	/**
	 * Iterative the Grid, append the char in every cell to a char
	 * and compare the char with the Word
	 * 
	 * @return matches how many words can application find that match the WordDB
	 * 
	 */
	public int traverseGrid() {
		int matches = 0;
		/*
		 * Traverse the Grid from eight directions
		 * (r,c) is original point
		 * dr, dc refer to the increase/decrease of row and column
		 * There are eight directions
		 * (-1,-1) means move toward southwest
		 * (-1,0) means move toward west
		 * (-1,1) means move toward northeast
		 * (0,-1) means move toward south
		 * (0,1) means move toward north
		 * (1,-1) means move toward southeast
		 * (1,0) means move toward east
		 * (1,1) means move toward northeast
		 */
		// Limit the prointer does not move outside the Grid
		for (int c = 0; c < column; c++)
			for (int r = 0; r < row; r++)
				// Changing of rows, move only one row each time
				for (int dc = -1; dc <= 1; dc++)
					for (int dr = -1; dr <= 1; dr++)
						// either dr or dc must be changing, otherwise get a duplicate cell
						if (dr != 0 || dc != 0)
							matches += concatWord(r, c, dr, dc);
		return matches;
	}

	/**
	 * When pointer moving, get every cell and get the char
	 * Concatenate these chars one by one to a string
	 * Using Arrays.binarySearch() to compare the concatenated string to Words list
	 * check whether there is
	 * 
	 * @return matches
	 *         number of words in the Grid that match the Word list
	 * 
	 */
	private int concatWord(int baseRow, int baseCol, int dRow, int dCol) {
		String concatChar = "";
		int matches = 0;
		int search;
		concatChar += grid[baseRow][baseCol];
		for (int i = baseRow + dRow, j = baseCol + dCol; i >= 0 && j >= 0 && i < row
				&& j < column; i += dRow, j += dCol) {
			concatChar += grid[i][j];
			// display concatenated string and find whether it works as expected
			// System.out.println(charSequence);

			search = Arrays.binarySearch(words, concatChar);

			/*
			 * If Arrays.binarySearch returns a negative or larger than the length of the
			 * Words list
			 * that means it can not match the concatenated string.
			 * Skip this string, and begin next loop
			 */
			if (search < 0 || search >= words.length) {
				continue;
			}
			// double check the match result
			else if (words[search].equals(concatChar)) {
				matches++;
				System.out.println("Found >> " + concatChar + " <<\t-> from (" + (baseRow + 1) + ", " + (baseCol + 1)
						+ ") to (" + (i + 1) + ", " + (j + 1) + ")");
			}
		}
		return matches;
	}

	/**
	 * Read file content which is inputed by <code>readFile()</code>
	 * 
	 * @param message
	 *                The file name
	 * @return fileIn
	 *         A buffered input stream
	 * @exception IOException if the file does not exist,
	 *                        is a directory rather than a regular file,
	 *                        or for some other reason cannot be opened for
	 *                        reading.
	 */
	private BufferedReader readFile(String message) {
		String fileName = "";
		FileReader theFile;
		BufferedReader fileIn = null;
		do {
			try {
				fileName = message;
				if (fileName == null)
					System.exit(0);
				theFile = new FileReader(fileName);
				fileIn = new BufferedReader(theFile);
			} catch (IOException e) {
				System.err.println("Can not open " + fileName);
				break;
			}
		} while (fileIn == null);
		return fileIn;
	}

	/**
	 * From the buffered input steam, generate a grid using the file content.
	 * 
	 * @throws IOException if the file is empty,
	 *                     contains no-rectangular content
	 */
	private void launchGrid() throws IOException {
		String line;
		List<String> gridLines = new ArrayList<String>();
		if ((line = loadGrid.readLine()) == null)
			throw new IOException("No lines in puzzle file");
		column = line.length();
		gridLines.add(line);
		/*
		 * Double check whether the content in the file is a rectangular
		 * Firstly, when read every line of the file,
		 * if there is a line that has a different length.
		 * Secondary, when all lines have been fetched and the grid is generated
		 * compare the height and width of grid.
		 */
		while ((line = loadGrid.readLine()) != null) {
			if (line.length() != column) {
				throw new IOException("Grid is not rectangular.");
			} else {
				gridLines.add(line);
			}
		}
		row = gridLines.size();
		if (row == column) {
			grid = new char[row][column];
			int r = 0;
			for (String theLine : gridLines) {
				grid[r++] = theLine.toLowerCase().toCharArray();
				// System.out.println(theBoard[r-1]); // check the grid content
			}
		} else {
			throw new IOException("Grid is not rectangular.");
		}
	}

	/**
	 * Load the Word list which need to be sorted by character.
	 * 
	 * @throws IOException if the Word list has not been sorted by characters.
	 */
	private void getWords() throws IOException {
		List<String> wordList = new ArrayList<String>();
		String lastWord = null;
		String thisWord;
		while ((thisWord = loadWordsDB.readLine()) != null) {
			if (lastWord != null && thisWord.compareTo(lastWord) < 0) {
				System.err.println("The database has not been sorted.");
				continue;
			}
			wordList.add(thisWord.trim());
			lastWord = thisWord;
		}
		words = new String[wordList.size()];
		words = wordList.toArray(words);

		// display all words inputed into application
//		for (String str : words) {
//			System.out.println(str);
//		}
	}


}