package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

import java.io.Serializable;

public class FinishedGameInfo implements Serializable, Comparable<FinishedGameInfo> {
    private final Player winner;
    private final long stamp;

    public FinishedGameInfo(final Player winner, final long stamp) {
        this.winner = winner;
        this.stamp = stamp;
    }

    public Player getWinner() {
        return winner;
    }

    public long getStamp() {
        return stamp;
    }

    @Override
    public int compareTo(final FinishedGameInfo another) {
        if (this.getWinner() == null && another.getWinner() == null) {
            // Both were draws
            return 0;
        } else if (this.getWinner() == null) {
            return 1;
        } else if (another.getWinner() == null) {
            return -1;
        }
        return Long.compare(this.getWinner().getElapsedTime(), another.getWinner().getElapsedTime());
    }
}
