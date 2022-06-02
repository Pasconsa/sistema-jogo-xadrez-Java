package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
//8.1.1 color piece codigos especiais , cores texto e fundo
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
//10 codigo limpar tela do gitbash 
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	//Metodo abaixo esta no link acima
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	
//09.2 read chessposition fazer leitura posição com scanner
	public static  ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro chees position , Valid  A1 at H8");
		}
	}
	
	
/*Imprimindo o tabuleiro
 * fazer 2 for um percorre as linhas e outr as colunas */
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8-i) + " ");      //imprimir na tabela 8 a 1 linhas
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j],false);        //colocar a peça na posição i e j
			}
			System.out.println();  //quebra linha do tabuleiro
		}
		System.out.println("  a b c d e f g h");  //imprimindo as colunas
	}
	
	//15.2 Print Match
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn : " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
	}
	
//13.2.2 recebe as matrizes de movimentos possiveis
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8-i) + " ");      //imprimir na tabela 8 a 1 linhas
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);        //colocar a peça na posição i e j
			}
			System.out.println();  //quebra linha do tabuleiro
		}
		System.out.println("  a b c d e f g h");  //imprimindo as colunas
	}
	
	
	/* 08.1.1 testar se a peça é branca ou preta
	 * 04 e  Metodo auxliar para imprimir uma unica peça   
	        -printpiece rebendo uma chessprice 
	        -se a peça for igual a nulo quer dizer não tem peça -
	        -caso contrario imprimo peça
	        - espaço em branco peças nao colarem */
	
	private static void printPiece(ChessPiece piece, boolean background) { //13.2.3 variavel colorir o fundo da peça
    	if(background) {
    		 System.out.print(ANSI_BLUE_BACKGROUND);
    	}
		
		if (piece == null) {
            System.out.print("-"+ ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.white) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	//16.1 Imprimir peças capturadas
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x-> x.getColor() == Color.white).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x-> x.getColor() == Color.black).collect(Collectors.toList());
			System.out.println("Captured pieces: ");
			System.out.print("White: ");
			System.out.print(ANSI_WHITE);
			System.out.println(Arrays.toString(white.toArray()));
			System.out.print(ANSI_RESET);
			
			System.out.print("Black: ");
			System.out.print(ANSI_YELLOW);
			System.out.println(Arrays.toString(black.toArray()));
			System.out.print(ANSI_RESET);
		
	}
	
	
	
}
