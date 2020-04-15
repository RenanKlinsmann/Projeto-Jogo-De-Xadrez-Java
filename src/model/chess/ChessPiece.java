package model.chess;

import model.boardgamer.Board;
import model.boardgamer.Piece;

public abstract class ChessPiece extends Piece {

	private Color cor;

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}

}
