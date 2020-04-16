package model.chess.pieces;

import model.boardgamer.Board;
import model.boardgamer.Position;
import model.chess.ChessMatch;
import model.chess.ChessPiece;
import model.chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	public King(Board tabuleiro, Color cor, ChessMatch chessMatch) {
		super(tabuleiro, cor);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "R";
	}
	
	private boolean canMove(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testRookCastling(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peca(posicao);
		return p != null && p instanceof Rook && p.getCor() == getCor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		
Position p = new Position(0, 0);
		
		// acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// noroeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nordeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// suldoeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// suldeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// #specialmove castling
				if (getMoveCount() == 0 && !chessMatch.getCheck()) {
					// #specialmove castling kingside rook
					Position posT1 = new Position(posicao.getLinha(), posicao.getColuna() + 3);
					if (testRookCastling(posT1)) {
						Position p1 = new Position(posicao.getLinha(), posicao.getColuna() + 1);
						Position p2 = new Position(posicao.getLinha(), posicao.getColuna() + 2);
						if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
							mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
						}
					}
					// #specialmove castling queenside rook
					Position posT2 = new Position(posicao.getLinha(), posicao.getColuna() - 4);
					if (testRookCastling(posT2)) {
						Position p1 = new Position(posicao.getLinha(), posicao.getColuna() - 1);
						Position p2 = new Position(posicao.getLinha(), posicao.getColuna() - 2);
						Position p3 = new Position(posicao.getLinha(), posicao.getColuna() - 3);
						if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
							mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
						}
					}
				}
		
		
		return mat;
	}
}
