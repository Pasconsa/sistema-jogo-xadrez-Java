package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[] [] pieces;   //matrizes de peças
	
	
	public Board(int rows, int columns) {
		if(rows <1 || columns <1) {   // 6.3 aplicação de exception
			throw new BoardException ("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece [rows] [columns];  //matriz de peças será instanciada com piece na quntidade
												//coluna e linha informada
	}


	
	
	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}

	
	
	//04 camadas //06.3 exception se esse posição não existir no tabuleiro
	public Piece piece (int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException ("Position not on the board");
		}
		return pieces[row][column];
	}
	
	//06.3
	public Piece piece (Position position) {
		if (!positionExists(position)) {
			throw new BoardException ("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//05.1 esse metodo , pega matriz na posição dada e coloca e a peçar atribuida.
	//06.3 exceção se existir peça ja na posição.
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//06.2
		//condições completas para ver se aposição existe
			
	private boolean positionExists(int row , int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(),position.getColumn());
	}
	
	//06.2 Implementar thereis a piece, teste se tem uma peça nesta posição.
		//06.3 testando se a posição existe
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) !=null;
	}
	
	
}
