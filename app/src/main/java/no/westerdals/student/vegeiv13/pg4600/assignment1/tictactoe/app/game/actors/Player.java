package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors;

import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.SquareState;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.exceptions.PlayerException;

import java.io.Serializable;

public class Player implements Serializable {

    private final String name;
    private final SquareState mark;
    private long elapsedTime;
    private long startedAt;

    public Player(String name, SquareState mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Allows creating multiple player objects from a variable length string array
     *
     * @param playerNames A variable-length array containing player names
     * @return An array containing player names
     * @throws PlayerException if any of the array indexes are empty or contain empty strings.
     */
    public static Player[] fromTextFields(final String... playerNames) throws PlayerException {
        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            final String playerName = playerNames[i];
            SquareState mark;
            if (playerName == null || playerName.replace(" ", "").equals("")) {
                throw new PlayerException(i);
            }
            if (i == 0) {
                mark = SquareState.X;
            } else {
                mark = SquareState.O;
            }
            players[i] = new Player(playerName, mark);
        }
        return players;
    }

    public String getName() {
        return name;
    }

    public SquareState getMark() {
        return mark;
    }

    /**
     * Stops this player's timer and adds the elapsed time since start to the total elapsed
     */
    public void pauseTimer() {
        if (startedAt == 0L) {
            throw new UnsupportedOperationException("Timer not started");
        }
        long stamp = System.currentTimeMillis();
        elapsedTime += stamp - startedAt;
        startedAt = 0L;
    }

    /**
     * Starts this player's timer
     */
    public void startTimer() {
        startedAt = System.currentTimeMillis();
    }

    /**
     * Gets the total time elapsed between every call to startTimer and pauseTimer
     * @return this player's elapsed time
     */
    public long getElapsedTime() {
        return this.elapsedTime;
    }

}
