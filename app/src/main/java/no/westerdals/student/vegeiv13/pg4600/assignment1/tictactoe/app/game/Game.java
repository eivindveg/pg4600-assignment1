package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

public class Game {

    private final TicTacToeBoard board;
    private final Player[] players;

    private Player activePlayer;
    private int roundsPlayed;
    private boolean newRound;

    public Game(Player[] players) {
        this.players = players;
        board = new TicTacToeBoard(3);
        prepare();
    }

    private void prepare() {
        roundsPlayed = 1;
        resolvePlayers();
    }

    private void resolveBoard() {
        if (checkForVictory()) {

        }
    }

    private boolean checkForVictory() {
        return checkVictoryHorizontal(0, 0)
                || checkVictoryVertical(0, 0)
                || checkVictoryDiagonal(0, 0);
    }

    private boolean checkVictoryDiagonal(final int x, final int y) {
        return !(x != y || x >= board.getSize())
                && board.isTickedByMark(x, y, activePlayer.getMark())
                && checkVictoryDiagonal(x + 1, y + 1);

    }

    private boolean checkVictoryVertical(final int x, final int y) {
        for (int i = x; i < board.getSize(); i++) {
            if (board.isTickedByMark(i, y, activePlayer.getMark())) {
                if (checkVictoryVertical(i, y + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVictoryHorizontal(final int y, final int x) {

        if (board.isTickedByMark(x, y, activePlayer.getMark())) {
            if (checkVictoryVertical(x, y + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVictoryHorizontal() {
        for (int y = 0; y < board.getSize(); y++) {

        }
    }

    private void resolvePlayers() {
        if (newRound) {
            activePlayer = players[0];
        } else {
            activePlayer = players[1];
            roundsPlayed++;
        }
        newRound = !newRound;
    }

    public boolean tick(int x, int y) {
        if (!board.tickWithMark(x, y, activePlayer.getMark())) {
            return false;
        }
        resolveBoard();
        resolvePlayers();

        return true;
    }


    public Player getActivePlayer() {
        return activePlayer;
    }
}
