package model.chess.pieces;

import model.boardgamer.Board;
import model.chess.ChessPiece;
import model.chess.Color;

public class King extends ChessPiece {

	public King(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
