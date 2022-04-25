package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;

	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}


	public Color getColor() { //não gerado set para cor não ser alterada
		return color;
	}

//12.1 se existe uma peça adversaria na posição
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null  &&  p.getColor() != color;
	}
	
	
}
