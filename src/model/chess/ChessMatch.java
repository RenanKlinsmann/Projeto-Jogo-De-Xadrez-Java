package model.chess;

import model.boardgamer.Board;
import model.boardgamer.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMatch {

	private Board tabuleiro;
	
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
	    for (int i =0; i<tabuleiro.getLinhas(); i++) {
	    	for (int j=0; j<tabuleiro.getColunas(); j++) {
	    		mat[i][j] = (ChessPiece) tabuleiro.peca(i, j);
	    	}
	    }
	    return mat;
	}
	
	private void initialSetup() {
		tabuleiro.placePiece(new Rook(tabuleiro, Color.WHITE),new Position(2, 1));
		tabuleiro.placePiece(new King(tabuleiro, Color.BLACK),new Position(0, 4));
		tabuleiro.placePiece(new King(tabuleiro, Color.WHITE),new Position(7, 4));
	}
	
}
