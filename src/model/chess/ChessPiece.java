package model.chess;

import model.boardgamer.Board;
import model.boardgamer.Piece;
import model.boardgamer.Position;

public abstract class ChessPiece extends Piece {

	private Color cor;
	private int moveCount;

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(posicao);
	}
	
	protected boolean isThereOpponentPiece(Position posicao) {
		ChessPiece p = (ChessPiece)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
