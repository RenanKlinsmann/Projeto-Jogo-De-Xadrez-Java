package model.boardgamer;

public abstract class Piece {

	protected Position posicao;
	private Board tabuleiro;

	public Piece(Board tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Board getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position posicao) {
		return possibleMoves()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
