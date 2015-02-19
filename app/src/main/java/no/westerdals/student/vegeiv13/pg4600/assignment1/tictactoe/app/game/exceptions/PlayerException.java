package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.exceptions;

public class PlayerException extends Exception {

    private final int playerId;

    public PlayerException(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
