package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	//construtor 5.2
	public Rook(Board board, Color color) {
		super(board, color);
	}

	//R vai ser a peça torre
	@Override
	public String toString() {
		return "R" ;
	}

	//11.2 automatic
			//tpdas posições do rei são falsos
		@Override
		public boolean[][] possibleMoves() {
			boolean[][] mat= new boolean[getBoard().getRows()][getBoard().getColumns()];
		//12.2 movimentos da torre acima da peça
			Position p = new Position(0,0);
			
			//12.2.1 cima
			p.setValues(position.getRow() - 1, position.getColumn());
			while (getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
				p.setRow(p.getRow() - 1);
			}
			if (getBoard().positionExists(p)  &&  isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
			}
			
			//12.2.2 esquerda
			p.setValues(position.getRow() , position.getColumn() - 1);
			while (getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
				p.setColumn(p.getColumn() - 1);
			}
			if (getBoard().positionExists(p)  &&  isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
			}
			
			//12.2.3 direita
			p.setValues(position.getRow() , position.getColumn() + 1);
			while (getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
				p.setColumn(p.getColumn() + 1);
			}
			if (getBoard().positionExists(p)  &&  isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
			}

			//12.2.4 baixo
			p.setValues(position.getRow() + 1, position.getColumn());
			while (getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
				p.setRow(p.getRow() + 1);
			}
			if (getBoard().positionExists(p)  &&  isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true ;
			}

			
			return mat;
		}
	
	
	
}
