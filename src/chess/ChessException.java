package chess;

import boardgame.BoardException;

public class ChessException extends BoardException{  //??
	private static final long serialVersionUID = 1L;   //serial padrao??
	
	public ChessException(String msg ) {
		super(msg); //repassar o contrutor desta classe.
	}
}
