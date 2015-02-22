package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.*;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.Game;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.SquareState;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

public class GameActivity extends BaseActivity {

    private Player[] players;
    private Game game;
    private Drawable xImage;
    private Drawable oImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.game_board, mContentFrame);

        prepareAssets();
        setupPlayers();
        setupGame();
        wireButtons();
    }

    private void prepareAssets() {
        Resources resources = getResources();
        xImage = resources.getDrawable(R.drawable.mark_x);
        oImage = resources.getDrawable(R.drawable.mark_o);
    }

    private void wireButtons() {
        TableLayout board = (TableLayout) findViewById(R.id.game_board);
        int rows = board.getChildCount();
        int i = 0;
        while(i < rows) {
            final int y = i;
            TableRow row = (TableRow) board.getChildAt(i);
            int columns = row.getChildCount();
            int j = 0;
            while(j < columns) {
                final int x = j;
                ImageButton button = (ImageButton) row.getChildAt(j);
                button.setOnClickListener(v -> rigConditions(y, x, button));
                j++;
            }
            i++;
        }
    }

    private void rigConditions(final int y, final int x, final ImageButton button) {
        if(game.tick(x, y)) {
            SquareState mark = game.getActivePlayer().getMark();
            button.setImageDrawable(getImageForMark(mark));
        }
        if(game.isGameOver()) {
            Player winner = game.getWinner();
            Intent intent = new Intent(this, LeaderboardActivity.class);
            intent.putExtra("winner", winner);
            startActivity(intent);
        }
    }

    private Drawable getImageForMark(SquareState mark) {
        switch(mark) {
            case X: return xImage;
            case O: return oImage;
            default: {
                Toast.makeText(this, "We have encountered a problem", Toast.LENGTH_LONG).show();
                return null;
            }
        }
    }

    private void setupGame() {
        Game game = (Game) getIntent().getSerializableExtra("game");
        if (game != null) {
            this.game = game;
        } else {
            this.game = new Game(players);
        }
    }

    private void setupPlayers() {
        players = (Player[]) getIntent().getSerializableExtra("players");
        TextView playerOneText = (TextView) findViewById(R.id.playerOneName);
        playerOneText.setText(players[0].getName());
        TextView playerTwoText = (TextView) findViewById(R.id.playerTwoName);
        playerTwoText.setText(players[1].getName());
    }
}
