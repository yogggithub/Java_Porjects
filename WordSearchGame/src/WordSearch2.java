
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ���������Թ�
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
	 * �ڹ��캯���й������������������ʵ����������ͱ���������
	 */
	public WordSearch2() throws IOException {
		puzzleStream = openFile("Grid.txt" /* "�������ļ�·����" */);
		wordStream = openFile("WordDB.txt" /* "���뵥���ļ�·����" */);
		System.out.println("�ļ���ȡ��...");
		readPuzzle();
		readWords();
	}

	/**
	 * @return matches ���ж��ٸ�����ƥ��
	 *         ����ÿ��λ�ôӰ˸���������
	 *         rd ��ʾ���ϵ�������eg:rd=-1����ʾ����һ��
	 *         cd ��ʾ���ϵ����� eg:cd=-1����ʾ����һ��
	 *         ����rd=1,cd=0��ʾ��
	 *         rd=-1,cd=0��ʾ����
	 *         rd=-1,cd=1,��ʾ����
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
	 * ��ָ���������ϣ����ո����ķ�������������ƥ��ĵ�������
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
			 * ����� if( searchResult == theWords.length )
			 * ����Ҫ�жϣ���������Խ���Σ�գ��������һ������֮ƥ��ǰ׺ʱ�����ص�������-1
			 */
			if (searchResult == theWords.length)
				break;
			/**
			 * ���û����Ӧ��ǰ׺��ֱ����������������������ʹ��������������Ҳ�����ù�
			 */
			if (!theWords[searchResult].startsWith(charSequence))
				break;
			if (theWords[searchResult].equals(charSequence)) {
				numMatches++;
				System.out.println("������ " + charSequence + " �� " + (baseRow + 1) + "��  " + (baseCol + 1) + " ��   "
						+ (i + 1) + " " + (j + 1));
			}
		}
		return numMatches;
	}

	/**
	 * �Ƚ���Arrays.binarySearch(Object[] ,Object)
	 * ʹ�ö����������㷨������ָ�����飬�Ի��ָ�������ڽ��д˵���֮ǰ��
	 * �����������Ԫ�ص���Ȼ˳�� �����������������ͨ������� Sort(Object[] ��������
	 * ���û�ж�����������������ǲ���ȷ�ġ������������������໥�Ƚϵ�Ԫ�أ����磬�ַ�������������
	 * ���޷� ��������Ԫ�ص���Ȼ˳����������������˽���ǲ���ȷ�ġ���
	 * �����������������ָ�������Ԫ�أ����޷���֤�ҵ�������һ����
	 */
	private static int prefixSearch(String[] a, String x) {
		int idx = Arrays.binarySearch(a, x);
		if (idx < 0)
			return -idx - 1;
		else
			return idx;
	}

	/**
	 * ��ȡ�ļ����ݣ����������
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
	 * ������
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
	 * ��ȡ�Ѿ������ֵ�����ĵ����б�
	 */
	private void readWords() throws IOException {
		List<String> words = new ArrayList<String>();
		String lastWord = null;
		String thisWord;
		while ((thisWord = wordStream.readLine()) != null) {
			if (lastWord != null && thisWord.compareTo(lastWord) < 0) {
				System.err.println("û�а����ֵ�˳�����򣬴˴�����");
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
		System.out.println("��������...");
		p.solvePuzzle();
	}

}