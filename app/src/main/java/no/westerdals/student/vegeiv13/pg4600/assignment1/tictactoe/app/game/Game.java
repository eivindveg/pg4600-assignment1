package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

import android.util.Log;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

public class Game {

    private final TicTacToeBoard board;
    private final Player[] players;

    private Player activePlayer;
    private int roundsPlayed;
    private boolean newRound;
    private Player winner;

    public Game(Player[] players) {
        this.players = players;
        board = new TicTacToeBoard(3);
        prepare();
    }

    private void prepare() {
        resolvePlayers();
    }

    private void resolve(final int x, final int y) {
        boolean b = checkForVictory(x, y);
        resolvePlayers();
        if(b) {
            setWinner(getActivePlayer());
        }
    }

    private boolean checkForVictory(final int x, final int y) {
        return checkVictoryHorizontal(y)
                || checkVictoryVertical(x)
                || checkVictoryDiagonal(x, y);
    }

    private boolean checkVictoryDiagonal(final int x, final int y) {
        // Diagonal going top-left to bottom-right
        if(board.isMarkOnStandardDiagonal(x, y)) {
            for(int i = 0; i < board.getSize(); i++) {
                if(!board.isTickedByMark(i, i, getActivePlayer().getMark())) {
                    break;
                } else if(i == board.getSize() - 1) {
                    return true;
                }
            }
        }

        // Diagonal going bottom-left to top-right
        if(board.isMarkOnAntiDiagonal(x, y)) {
            for(int i = 0; i < board.getSize(); i++) {
                if(!board.isTickedByMark(i, (board.getSize()-1) - i, getActivePlayer().getMark())) {
                    break;
                } else if(board.isMarkAtBoardsEdge(i)) {
                    return true;
                }
            }
        }

        return false;
    }


    private boolean checkVictoryVertical(final int column) {
        for (int y = 0; y < board.getSize(); y++) {
            if(!board.isTickedByMark(column, y, activePlayer.getMark())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkVictoryHorizontal(int row) {
        for (int x = 0; x < board.getSize(); x++) {
            if(!board.isTickedByMark(x, row, activePlayer.getMark())) {
                return false;
            }
        }
        return true;
    }

    private void resolvePlayers() {
        if(activePlayer != null) {
            activePlayer.pauseTimer();
        }
        if (newRound) {
            activePlayer = players[0];
        } else {
            activePlayer = players[1];
            roundsPlayed++;
        }
        newRound = !newRound;
        activePlayer.startTimer();
    }

    public boolean tick(int x, int y) {
        if (isGameOver() || !board.tickWithMark(x, y, activePlayer.getMark())) {
            return false;
        }
        resolve(x, y);

        return true;
    }

    public boolean isGameOver() {
        Log.d("Rounds played", String.valueOf(roundsPlayed));
        Log.d("Winner", winner != null ? winner.getName() : "Noone");
        return this.winner != null || roundsPlayed * 2 - 1 > Math.pow(board.getSize(), 2);
    }


    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
}
