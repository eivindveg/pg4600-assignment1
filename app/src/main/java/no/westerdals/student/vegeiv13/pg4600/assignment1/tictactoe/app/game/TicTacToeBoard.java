package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

public class TicTacToeBoard {

    private final Square[][] board;
    private final int size;

    public TicTacToeBoard(int size) {
        this.size = size;
        board = new Square[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Square();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isTickedByMark(int x, int y, SquareState state) {
        return !(x >= board.length || y >= board[x].length)
                && board[x][y].getState() == state;
    }

    private boolean isPositionTicked(final int x, final int y) {
        return board[x][y].getState() != SquareState.EMPTY;
    }

    public boolean tickWithMark(final int x, final int y, final SquareState mark) {
        if (isPositionTicked(x, y)) {
            return false;
        }

        board[x][y].setState(mark);
        return true;
    }

    protected boolean isMarkOnAntiDiagonal(final int x, final int y) {
        return x == getSize() - (y + 1);
    }

    protected boolean isMarkOnStandardDiagonal(final int x, final int y) {
        return x == y;
    }

    protected boolean isMarkAtBoardsEdge(final int i) {
        return i == getSize() - 1;
    }
}
