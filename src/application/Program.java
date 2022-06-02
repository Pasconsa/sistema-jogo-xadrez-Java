package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
//04 intanciar uma partida e imprimir o tabuleiro
		//UI.printboard recebe a matriz de peças da partida
		
		Scanner sc = new Scanner (System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			try {
				UI.clearScreen();  //10
				UI.printMatch(chessMatch);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
//13.2.1	Imprimir o tabuleiro e colorindo as posições possiveis			
			boolean[][] possibleMoves =chessMatch.possibleMoves(source);
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces(),possibleMoves);
	//04	
			System.out.println();
			System.out.print("target: ");
			ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source , target );
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		
		
	}

}
 