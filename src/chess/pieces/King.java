package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);	
	}

	@Override
	public String toString() {
		return "K";
	}

	//14.1 se o rei pode pode mover determinada posição metodo
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	//11.2 automatic
		//tpdas posições do rei são falsos
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat= new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		//14.2
		Position p = new Position(0,0);
		
//14.3 testar direçoes do rei
		// above acima do rei
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		// left do rei
		p.setValues(position.getRow(), position.getColumn() -1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		// right do rei
		p.setValues(position.getRow(), position.getColumn() +1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// noroeste diagonal do rei
				p.setValues(position.getRow() -1, position.getColumn() -1);
				if (getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		
		// nordeste diagonal do rei
				p.setValues(position.getRow() - 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
		// suldoeste diagonal do rei
				p.setValues(position.getRow() + 1, position.getColumn() - 1);
				if (getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}	
				
		// suldeste diagonal do rei
				p.setValues(position.getRow() + 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}		
	
	
				return mat;
	}
	

	
}
