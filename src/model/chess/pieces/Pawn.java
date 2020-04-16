package model.chess.pieces;

import model.boardgamer.Board;
import model.boardgamer.Position;
import model.chess.ChessPiece;
import model.chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		if (getCor() == Color.WHITE) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Position p2 = new Position(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)
					&& getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		} else {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Position p2 = new Position(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)
					&& getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
