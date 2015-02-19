package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

public class TicTacToeBoard {

    private Square[][] board = new Square[3][3];

    public TicTacToeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Square();
            }
        }
    }

    public
}
