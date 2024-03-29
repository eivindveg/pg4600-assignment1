package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.exceptions.PlayerException;

public class NewGameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.new_game, mContentFrame);

        wireButton();
    }

    private void wireButton() {
        final Button button = (Button) findViewById(R.id.start_game_button);
        final EditText player1 = (EditText) findViewById(R.id.playerOneText);
        final EditText player2 = (EditText) findViewById(R.id.playerTwoText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    Player[] players = Player.fromTextFields(player1.getText().toString(), player2.getText().toString());
                    Intent gameIntent = new Intent(NewGameActivity.this, GameActivity.class);
                    gameIntent.putExtra("players", players);
                    NewGameActivity.this.startActivity(gameIntent);
                } catch (PlayerException e) {
                    Toast.makeText(NewGameActivity.this, "Please input valid player name for Player " + e.getPlayerId() + 1, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
