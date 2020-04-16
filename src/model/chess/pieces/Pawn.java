package model.chess.pieces;

import model.boardgamer.Board;
import model.boardgamer.Position;
import model.chess.ChessMatch;
import model.chess.ChessPiece;
import model.chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board tabuleiro, Color cor, ChessMatch chessMatch) {
		super(tabuleiro, cor);
		this.chessMatch = chessMatch;
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

			// #specialmove en passant white
			if (posicao.getLinha() == 3) {
				Position left = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().positionExists(left) && isThereOpponentPiece(left)
						&& getTabuleiro().peca(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getLinha() - 1][left.getColuna()] = true;
				}
				Position right = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().positionExists(right) && isThereOpponentPiece(right)
						&& getTabuleiro().peca(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getLinha() - 1][right.getColuna()] = true;
				}
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

			// #specialmove en passant black
			if (posicao.getLinha() == 4) {
				Position left = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().positionExists(left) && isThereOpponentPiece(left)
						&& getTabuleiro().peca(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getLinha() + 1][left.getColuna()] = true;
				}
				Position right = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().positionExists(right) && isThereOpponentPiece(right)
						&& getTabuleiro().peca(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getLinha() + 1][right.getColuna()] = true;
				}
			}

		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
