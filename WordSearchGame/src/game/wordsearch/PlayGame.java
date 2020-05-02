package game.wordsearch;

import java.io.IOException;
import java.util.Scanner;

/**
 * Word Search Game
 * 
 * @author Eric Wu
 * @version v 1.4 2020.04.21
 * 
 * Description: I get the Word Grid from
 * <url>https://api.razzlepuzzles.com/wordsearch</url>
 * Unfortunately this website doest not give any ID of their puzzles
 * so, I cannot re-visit the puzzle that I used to test my application
 * Instead, I capture screenshots which stored in the <code>image<\code> folder.
 * 
 * The puzzle I used is a medium level which is 11*11 and have 15
 * words hiden in it.
 * 
 * I hope the v2.0 could display a GUI interface for user to play
 * this game by themself.
 * I am working on it.
 * 
 */

public class PlayGame {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input the path of Word Grid: ");
		String gridFile = sc.nextLine();
		System.out.print("Please input the path of Word Database: ");
		String wordDB = sc.nextLine();
		System.out.println();
		sc.close();
		
		WordGrid p = null;
		try {
			p = new WordGrid(gridFile, wordDB);
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
			return;
		}
		System.out.println("Resolving ...\r");
		System.out.println("\rThere are total " + p.traverseGrid() + " words hiden in the Grid.");
	}

}
