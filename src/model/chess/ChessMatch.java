package model.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.boardgamer.Board;
import model.boardgamer.Piece;
import model.boardgamer.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMatch {

	private int turno;
	private Color jogadorAtual;
	private Board tabuleiro;
	private boolean check;
	private boolean checkMate;

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

	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece[][] getPecas() {
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPiece) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
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

		if (testCheck(jogadorAtual)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Você nao pode se colocar em check");
		}

		check = (testCheck(opponent(jogadorAtual))) ? true : false;

		if (testCheckMate(opponent(jogadorAtual))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}

		return (ChessPiece) capturedPiece;
	}

	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)tabuleiro.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = tabuleiro.removePiece(target);
		tabuleiro.placePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)tabuleiro.removePiece(target);
		p.decreaseMoveCount();
		tabuleiro.placePiece(p, source);

		if (capturedPiece != null) {
			tabuleiro.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void validateSourcePosition(Position posicao) {
		if (!tabuleiro.thereIsAPiece(posicao)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		if (jogadorAtual != ((ChessPiece) tabuleiro.peca(posicao)).getCor()) {
			throw new ChessException("A peça escolhida não e sua");
		}
		if (!tabuleiro.peca(posicao).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!tabuleiro.peca(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode se mover para posição de destino");
		}
	}

	private void nextTurn() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color cor) {
		return (cor == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color cor) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Não há Rei" + cor + " no tabuleiro");
	}

	private boolean testCheck(Color cor) {
		Position kingPosition = king(cor).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getCor() == opponent(cor))
				.collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getLinha()][kingPosition.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color cor) {
		if (!testCheck(cor)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getCor() == cor).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(cor);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	

	
	private void placeNewPiece(char coluna, int linha, ChessPiece peca) {
		tabuleiro.placePiece(peca, new ChessPosition(coluna, linha).toPosition());
		piecesOnTheBoard.add(peca);
	}

	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(tabuleiro, Color.WHITE));
		placeNewPiece('d', 1, new Rook(tabuleiro, Color.WHITE));
		placeNewPiece('e', 1, new King(tabuleiro, Color.WHITE));
		
		placeNewPiece('b', 8, new Rook(tabuleiro, Color.BLACK));
        placeNewPiece('a', 8, new King(tabuleiro, Color.BLACK));

	}

}
