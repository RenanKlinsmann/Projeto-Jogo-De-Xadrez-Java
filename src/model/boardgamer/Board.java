package model.boardgamer;

public class Board {

	private int linhas;
	private int colunas;
	private Piece[][] pecas;

	public Board(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new BoardException("Erro ao criar tabuleiro: deve haver pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	

	public int getColunas() {
		return colunas;
	}

	

	public Piece peca(int linha, int coluna) {
		if (!positionExists(linha, coluna)) {
			throw new BoardException("Posição nao existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public Piece peca(Position posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("Posição nao existe no tabuleiro");
		}
		return pecas [posicao.getLinha()][posicao.getColuna()];
	}
	
	public void placePiece(Piece peca, Position posicao) {
		if (thereIsAPiece(posicao)) {
			throw new BoardException("já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean positionExists(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean positionExists(Position posicao) {
		return positionExists(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean thereIsAPiece(Position posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("Posição nao existe no tabuleiro");
		}
		return peca(posicao) != null;
	}
}
