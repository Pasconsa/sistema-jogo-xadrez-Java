package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//nesta classe que tem as regras do jogo de xadrez partida de xadrex
		//nesta classe que vai dizer a dimensão que vai ser 8 por 8
public class ChessMatch { 

//15.1.1 criar atibutos turn current player e seus get seter	
	private int turn;
	private Color currentPlayer;
	private Board board; //partida precisa do tabuleiro
	private boolean check;  //17.2.2
	private boolean checkMate; //18.1.1
	
//16.4.1
	private List <Piece> piecesOnTheBoard = new ArrayList<>();
	private List <Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.white;
		initialSetup();
	}
	
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	

	public ChessPiece[][] getPieces(){  //retorna uma matriz de peças de xadrez corresponde essa partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //quantidade de linha e colunas do tabuleiro
		for (int i=0 ; i < board.getRows(); i++) {
			for(int j=0 ; j < board.getColumns(); j++){
					mat[i][j] = (ChessPiece) board.piece(i,j);
			}	
		}return mat;
	}
	
	//13.1 aplicação colorir imprimir as posições possiveis de uma posição de origem
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toposition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	//9.3.1 movimento peça posição de origem = source pra target = destino
			//converter as duas posições para a matriz
			//validar a posição de origem
			//make move reposnsavel pelo movimento da peça
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toposition();
		Position target = targetPosition.toposition();
		validateSourcePosition(source);
	//12.3	
		validateSourcePosition(source, target);
		Piece capturedPiece = makeMove(source , target);
		
	//17.2.6
		if (testCheck(currentPlayer)) {
			UndoMove(source , target , capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		check = (testCheck(opponent(currentPlayer))) ? true : false ;
	//18.1.3	
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
	}
	else {
		nextTurn();
	}
		
		return (ChessPiece)capturedPiece;
		
	}
	
	//9.3.2 Implemetar validateSourcePosition ; senão existir uma peça na posição de origem
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}	
			//15.1.2
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
			
			//11.3 senão existe movientos possiveis não posso usar como origem
		if (!board.piece(position).isThereAnyPossibleMove() ) {
			throw new ChessException("There is no possible movers for the chosen pieces.");
			
		}
	}
	
	//9.3.3 Metodo make move peça se mover ;
		//remover a peça da posição de origem
		//remover a peça qe esteja na posição de destino
		//colocar a posição de origem na de destino
	private Piece makeMove(Position source , Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
	//16.4.3	
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	//17.2.1
	private void UndoMove(Position source , Position target , Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	//12.3 se para peça de origem a posição de destino nao é possivel nã posso mexer
	private void validateSourcePosition(Position source , Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can t move to target possible");
		}
	}
	
	//15.1.2 mETODO NEXT TURN
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.white) ? Color.black : Color.white;
	}
	
	//17.2.3
		private Color opponent(Color color) {
			return (color == Color.white) ? Color.black : Color.white;
		}
		
		private ChessPiece King(Color color) {
			List<Piece> list = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
			for (Piece p : list) {
				if (p instanceof King) {
					return (ChessPiece) p;
				}
			}
			throw new IllegalStateException("There is no " + color + "king on the board");
		}
		
	//17.2.5 Test check
		private boolean testCheck(Color color) {
			Position kingPosition = King(color).getChessPosition().toposition();
			List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
			for (Piece p : opponentPieces ) {
				boolean[][] mat= p.possibleMoves();
				if(mat[kingPosition.getRow()][kingPosition.getColumn()])  {
					return true;
				}
			}
			return false;
		
		}
		
	//18.1.2
		private boolean testCheckMate(Color color) {
			if(!testCheck(color)) {
				return false;
			}
			List<Piece> list = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
			for(Piece p : list) {
				boolean[][] mat = p.possibleMoves();
				for (int i=0; i<board.getRows(); i++) {
					for(int j=0; j<board.getColumns(); j++) {
						if (mat[i][j]) {
							Position source = ((ChessPiece)p).getChessPosition().toposition();
							Position target = new Position (i, j);
							Piece capturedPiece = makeMove(source, target);
							boolean testCheck = testCheck(color);
							UndoMove(source, target, capturedPiece);
							if (!testCheck) {
								return false;
							}
							
						}
					}
				}
			}
			return true;
		}
		
	//7.3 metodo recebe as coordenadas do xadrez
		private void placeNewPiece(char column, int row, ChessPiece piece) {
			board.placePiece(piece,new ChessPosition(column, row).toposition());
			piecesOnTheBoard.add(piece);   //16.4.2
		}

	
	//7.3 e 8.1.3 metodo responsavel por iniciar a partida de xadrez; lugar onde coloca as peças
	private void initialSetup( ) {
		
		//18.1.1 colocar posições em check
		placeNewPiece('h', 7, new Rook(board, Color.white));
        placeNewPiece('d', 1, new Rook(board, Color.white));
        placeNewPiece('e', 1, new Rook(board, Color.white));
        
        placeNewPiece('b', 8, new Rook(board, Color.black));
        placeNewPiece('a', 8, new Rook(board, Color.black));
		
		
	       /* placeNewPiece('c', 1, new Rook(board, Color.white));
	        placeNewPiece('c', 2, new Rook(board, Color.white));
	        placeNewPiece('d', 2, new Rook(board, Color.white));
	        placeNewPiece('e', 2, new Rook(board, Color.white));
	        placeNewPiece('e', 1, new Rook(board, Color.white));
	        placeNewPiece('d', 1, new King(board, Color.white));
	      
	        placeNewPiece('c', 7, new Rook(board, Color.black));
	        placeNewPiece('c', 8, new Rook(board, Color.black));
	        placeNewPiece('d', 7, new Rook(board, Color.black));
	        placeNewPiece('e', 7, new Rook(board, Color.black));
	        placeNewPiece('e', 8, new Rook(board, Color.black));
	        placeNewPiece('d', 8, new King(board, Color.black));  */
	      
		}
	}

