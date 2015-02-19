package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.os.Bundle;
import android.widget.TextView;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

public class GameActivity extends BaseActivity {

    private Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.game_board, mContentFrame);

        setupPlayers();
    }

    private void setupPlayers() {
        players = (Player[]) getIntent().getSerializableExtra("players");
        TextView playerOneText = (TextView) findViewById(R.id.playerOneName);
        playerOneText.setText(players[0].getName());
        TextView playerTwoText = (TextView) findViewById(R.id.playerTwoName);
        playerTwoText.setText(players[1].getName());
    }
}
