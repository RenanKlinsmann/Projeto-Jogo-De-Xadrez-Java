package model.chess.pieces;

import model.boardgamer.Board;
import model.boardgamer.Position;
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

		Position p = new Position(0, 0);

		// verifica acima da peça
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifica a esquerda da peça
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifica a direita da peça
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifica abaixo da peça
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
