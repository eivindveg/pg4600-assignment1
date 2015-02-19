package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors;

import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.SquareState;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.exceptions.PlayerException;

import java.io.Serializable;

public class Player implements Serializable {

    private final String name;
    private final SquareState mark;

    public Player(String name, SquareState mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public static Player[] fromTextFields(final String... playerNames) throws PlayerException {
        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            final String playerName = playerNames[i];
            SquareState mark;
            if (playerName == null || playerName.replace(" ", "").equals("")) {
                throw new PlayerException(i);
            }
            if(i == 0) {
                mark = SquareState.X;
            } else {
                mark = SquareState.O;
            }
            players[i] = new Player(playerName, mark);
        }
        return players;
    }

    public SquareState getMark() {
        return mark;
    }
}
