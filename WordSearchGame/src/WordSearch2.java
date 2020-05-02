
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单词搜索迷宫
 * 
 */
public class WordSearch2 {
	private int rows;
	private int columns;
	private char[][] theBoard;
	private String[] theWords;
	private BufferedReader puzzleStream;
	private BufferedReader wordStream;

	/**
	 * 在构造函数中构造两个输入流，单词的输入流，和表格的输入流
	 */
	public WordSearch2() throws IOException {
		puzzleStream = openFile("Grid.txt" /* "输入表格文件路径：" */);
		wordStream = openFile("WordDB.txt" /* "输入单词文件路径：" */);
		System.out.println("文件读取中...");
		readPuzzle();
		readWords();
	}

	/**
	 * @return matches 共有多少个单词匹配
	 *         按照每个位置从八个方向搜索
	 *         rd 表示行上得增量，eg:rd=-1，表示向上一行
	 *         cd 表示列上得增量 eg:cd=-1。表示向左一步
	 *         所以rd=1,cd=0表示南
	 *         rd=-1,cd=0表示北，
	 *         rd=-1,cd=1,表示东北
	 */
	public int solvePuzzle() {
		int matches = 0;
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++)
				for (int rd = -1; rd <= 1; rd++)
					for (int cd = -1; cd <= 1; cd++)
						if (rd != 0 || cd != 0)
							matches += solveDirection(r, c, rd, cd);
		return matches;
	}

	/**
	 * 在指定的坐标上，按照给定的方向搜索，返回匹配的单词数量
	 * 
	 * @return number of matches
	 */
	private int solveDirection(int baseRow, int baseCol, int rowDelta, int colDelta) {
		String charSequence = "";
		int numMatches = 0;
		int searchResult;
		charSequence += theBoard[baseRow][baseCol];
		for (int i = baseRow + rowDelta, j = baseCol + colDelta; i >= 0 && j >= 0 && i < rows
				&& j < columns; i += rowDelta, j += colDelta) {
			charSequence += theBoard[i][j];
			// System.out.println(charSequence); // used to display and check all the
			// attempts
			searchResult = prefixSearch(theWords, charSequence);
			/**
			 * 下面的 if( searchResult == theWords.length )
			 * 必须要判断，否则会出现越界的危险，及当最后一个单词之匹配前缀时，返回的是索引-1
			 */
			if (searchResult == theWords.length)
				break;
			/**
			 * 如果没有响应的前缀，直接跳过这个基点的搜索，即使继续搜索，做的也是无用功
			 */
			if (!theWords[searchResult].startsWith(charSequence))
				break;
			if (theWords[searchResult].equals(charSequence)) {
				numMatches++;
				System.out.println("发现了 " + charSequence + " 在 " + (baseRow + 1) + "行  " + (baseCol + 1) + " 列   "
						+ (i + 1) + " " + (j + 1));
			}
		}
		return numMatches;
	}

	/**
	 * 先解释Arrays.binarySearch(Object[] ,Object)
	 * 使用二进制搜索算法来搜索指定数组，以获得指定对象。在进行此调用之前，
	 * 必须根据数组元素的自然顺序 对数组进行升序排序（通过上面的 Sort(Object[] 方法）。
	 * 如果没有对数组进行排序，则结果是不明确的。（如果数组包含不可相互比较的元素（例如，字符串和整数），
	 * 则无法 根据数组元素的自然顺序对数组进行排序，因此结果是不明确的。）
	 * 如果数组包含多个等于指定对象的元素，则无法保证找到的是哪一个。
	 */
	private static int prefixSearch(String[] a, String x) {
		int idx = Arrays.binarySearch(a, x);
		if (idx < 0)
			return -idx - 1;
		else
			return idx;
	}

	/**
	 * 读取文件内容，获得输入流
	 */
	private BufferedReader openFile(String message) {
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
				System.err.println("Cannot open " + fileName);
			}
		} while (fileIn == null);
		System.out.println("Opened " + fileName);
		return fileIn;
	}

	/**
	 * 读入表格
	 */
	private void readPuzzle() throws IOException {
		String oneLine;
		List<String> puzzleLines = new ArrayList<String>();
		if ((oneLine = puzzleStream.readLine()) == null)
			throw new IOException("No lines in puzzle file");
		columns = oneLine.length();
		puzzleLines.add(oneLine);
		while ((oneLine = puzzleStream.readLine()) != null) {
			if (oneLine.length() != columns)
				System.err.println("Puzzle is not rectangular; skipping row");
			else
				puzzleLines.add(oneLine);
		}
		rows = puzzleLines.size();
		theBoard = new char[rows][columns];
		int r = 0;
		for (String theLine : puzzleLines) {
			theBoard[r++] = theLine.toLowerCase().toCharArray();
			// System.out.println(theBoard[r-1]); // check the grid content
		}
	}

	/**
	 * 读取已经按照字典排序的单词列表
	 */
	private void readWords() throws IOException {
		List<String> words = new ArrayList<String>();
		String lastWord = null;
		String thisWord;
		while ((thisWord = wordStream.readLine()) != null) {
			if (lastWord != null && thisWord.compareTo(lastWord) < 0) {
				System.err.println("没有按照字典顺序排序，此次跳过");
				continue;
			}
			words.add(thisWord.trim());
			lastWord = thisWord;
		}
		theWords = new String[words.size()];
		theWords = words.toArray(theWords);

		// display all words inputed into application
//		for (String str : words) {
//			System.out.println(str);
//		}
	}

	// Cheap main
	public static void main(String[] args) {
		WordSearch2 p = null;
		try {
			p = new WordSearch2();
		} catch (IOException e) {
			System.out.println("IO Error: ");
			e.printStackTrace();
			return;
		}
		System.out.println("正在搜索...");
		p.solvePuzzle();
	}

}