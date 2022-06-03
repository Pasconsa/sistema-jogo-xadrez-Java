package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {  	//programação defensiva na~aceitar o chess position
		if (column < 'a' ||  column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error intantiating ChessPosition . Valid a1  to h8.");									
		}
		this.column = column;
		this.row = row;
			
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}


	//converter posição de xadrez para matriz
	protected Position toposition() {
		return new Position(8 - row, column - 'a' );
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition ((char)('a'+ position.getColumn()), 8 - position.getRow());
	}
	
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
	
	
}
