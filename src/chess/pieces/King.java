package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	
	//24.1 jogada Roque
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch	= chessMatch;
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
	
	//24.2 
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p !=null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
	
		//24.3 Rei pode mover duas a esquerda e direita jogada especial
				if(getMoveCount() == 0 && !chessMatch.getCheck()) {
					//24.3.1 testar  rook 1 pequeno
					Position posT1 = new Position(position.getRow(), position.getColumn()+ 3);
					if (testRookCastling(posT1)) {
						Position p1 = new Position (position.getRow(), position.getColumn()+1);
						Position p2 = new Position (position.getRow(), position.getColumn()+2);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
							mat[position.getRow()][position.getColumn() +2] = true;
						}
					}
					//24.3.2 testar  rook grande
					Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
					if (testRookCastling(posT2)) {
						Position p1 = new Position (position.getRow(), position.getColumn()-1);
						Position p2 = new Position (position.getRow(), position.getColumn()-2);
						Position p3 = new Position (position.getRow(), position.getColumn()-3);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
							mat[position.getRow()][position.getColumn() -2] = true;
					
				}
			}
			
		}			
				return mat;
	}
	

					
}
