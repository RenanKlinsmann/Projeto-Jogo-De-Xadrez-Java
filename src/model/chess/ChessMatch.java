package model.chess;

import model.boardgamer.Board;
import model.boardgamer.Piece;
import model.boardgamer.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMatch {

	private Board tabuleiro;
	
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPecas(){
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
	    for (int i =0; i<tabuleiro.getLinhas(); i++) {
	    	for (int j=0; j<tabuleiro.getColunas(); j++) {
	    		mat[i][j] = (ChessPiece) tabuleiro.peca(i, j);
	    	}
	    }
	    return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position posicao = sourcePosition.toPosition();
		validateSourcePosition(posicao);
		return tabuleiro.peca(posicao).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private void validateSourcePosition(Position posicao) {
		if(!tabuleiro.thereIsAPiece(posicao)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		if(!tabuleiro.peca(posicao).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!tabuleiro.peca(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode se mover para posição de destino");
		}
	}
	
	private Piece makeMove (Position source, Position target) {
		Piece p = tabuleiro.removePiece(source);
		Piece capturePiece = tabuleiro.removePiece(target);
		tabuleiro.placePiece(p, target);
		return capturePiece;
	}
	
	private void placeNewPiece(char coluna, int linha, ChessPiece peca) {
		tabuleiro.placePiece(peca, new ChessPosition(coluna, linha).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(tabuleiro, Color.WHITE));
        placeNewPiece('c', 2, new Rook(tabuleiro, Color.WHITE));
        placeNewPiece('d', 2, new Rook(tabuleiro, Color.WHITE));
        placeNewPiece('e', 2, new Rook(tabuleiro, Color.WHITE));
        placeNewPiece('e', 1, new Rook(tabuleiro, Color.WHITE));
        placeNewPiece('d', 1, new King(tabuleiro, Color.WHITE));

        placeNewPiece('c', 7, new Rook(tabuleiro, Color.BLACK));
        placeNewPiece('c', 8, new Rook(tabuleiro, Color.BLACK));
        placeNewPiece('d', 7, new Rook(tabuleiro, Color.BLACK));
        placeNewPiece('e', 7, new Rook(tabuleiro, Color.BLACK));
        placeNewPiece('e', 8, new Rook(tabuleiro, Color.BLACK));
        placeNewPiece('d', 8, new King(tabuleiro, Color.BLACK));
	}
	
}
