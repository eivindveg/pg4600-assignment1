package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors;

import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.exceptions.PlayerException;

import java.io.Serializable;

public class Player implements Serializable {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Player[] fromTextFields(final String... playerNames) throws PlayerException {
        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            final String playerName = playerNames[i];
            if (playerName == null || playerName.replace(" ", "").equals("")) {
                throw new PlayerException(i);
            }
            players[i] = new Player(playerName);
        }
        return players;
    }
}
