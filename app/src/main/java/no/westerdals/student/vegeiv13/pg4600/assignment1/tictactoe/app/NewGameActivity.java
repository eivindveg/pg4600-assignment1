package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.exceptions.PlayerException;

public class NewGameActivity extends BaseActivity {

    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.new_game, mContentFrame);

        wireButton();
    }

    private void wireButton() {
        button = (Button) findViewById(R.id.start_game_button);
        EditText player1 = (EditText) findViewById(R.id.playerOneText);
        EditText player2 = (EditText) findViewById(R.id.playerTwoText);
        button.setOnClickListener(v -> {
            try {
                Player[] players = Player.fromTextFields(player1.getText().toString(), player2.getText().toString());
                Intent gameIntent = new Intent(this, GameActivity.class);
                gameIntent.putExtra("players", players);
                startActivity(gameIntent);
            } catch(PlayerException e) {
                Toast.makeText(this, "Please input valid player name for " + e.getPlayerId(), Toast.LENGTH_LONG);
            }
        });
    }
}
