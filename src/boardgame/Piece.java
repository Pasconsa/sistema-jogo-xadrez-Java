package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null; //posição da peça inicia no zero
	}


	protected Board getBoard() { //set board foi apagado pois este não sera alterado
		return board;          		//protected pois esse tabuleiro é apenas uso camada tabuleiro
	}

	
	
}
