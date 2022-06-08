package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		List<ChessPiece> captured = new ArrayList<>();  //16.1
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();  //10
				UI.printMatch(chessMatch, captured);
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
				if(capturedPiece != null) {    //16.1
					captured.add(capturedPiece);
				}
		//26.3
				if(chessMatch.getPromoted() != null ) {
					System.out.println("Enter piece for promotion (B/N/R/Q) :");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") &&  !type.equals("N") &&  !type.equals("R") &&  !type.equals("Q")) {
						System.out.println("Invalid Value ! Enter piece for promotion (B/N/R/Q) :");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
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
		UI.clearScreen();
		UI.printMatch(chessMatch,  captured);
		
	}

}
 