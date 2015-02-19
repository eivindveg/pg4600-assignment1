package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game;

import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

public class Game {

    private TicTacToeBoard board;
    private Player playerOne;
    private Player playerTwo;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
}
