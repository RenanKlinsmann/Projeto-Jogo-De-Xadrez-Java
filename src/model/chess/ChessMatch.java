package model.chess;

import java.util.ArrayList;
import java.util.List;

import model.boardgamer.Board;
import model.boardgamer.Piece;
import model.boardgamer.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMatch {

	private int turno;
	private Color jogadorAtual;
	private Board tabuleiro;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		turno = 1;
		jogadorAtual = Color.WHITE;
		initialSetup();
	}
	
	
	public int getTurno() {
		return turno;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
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
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private void validateSourcePosition(Position posicao) {
		if(!tabuleiro.thereIsAPiece(posicao)) {
			throw new ChessException("N�o existe pe�a na posi��o de origem");
		}
		if(jogadorAtual != ((ChessPiece)tabuleiro.peca(posicao)).getCor()) {
			throw new ChessException("A pe�a escolhida n�o e sua");
		}
		if(!tabuleiro.peca(posicao).isThereAnyPossibleMove()) {
			throw new ChessException("N�o existe movimentos possiveis para a pe�a escolhida");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!tabuleiro.peca(source).possibleMove(target)) {
			throw new ChessException("A pe�a escolhida n�o pode se mover para posi��o de destino");
		}
	}
	
	private void nextTurn() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Piece makeMove (Position source, Position target) {
		Piece p = tabuleiro.removePiece(source);
		Piece capturedPiece = tabuleiro.removePiece(target);
		tabuleiro.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void placeNewPiece(char coluna, int linha, ChessPiece peca) {
		tabuleiro.placePiece(peca, new ChessPosition(coluna, linha).toPosition());
		piecesOnTheBoard.add(peca);
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
