package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

public class Square {

    private SquareState state;

    public Square() {
        state = SquareState.EMPTY;
    }

    public SquareState getState() {
        return state;
    }

    public void setState(SquareState newState) {
        state = newState;
    }
}
