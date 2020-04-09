package model.boardgamer;

public class Piece {

	protected Position posicao;
	private Board tabuleiro;

	public Piece(Board tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Board getTabuleiro() {
		return tabuleiro;
	}

}
