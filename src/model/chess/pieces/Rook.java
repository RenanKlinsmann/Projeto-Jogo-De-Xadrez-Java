package model.chess.pieces;

import model.boardgamer.Board;
import model.chess.ChessPiece;
import model.chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
