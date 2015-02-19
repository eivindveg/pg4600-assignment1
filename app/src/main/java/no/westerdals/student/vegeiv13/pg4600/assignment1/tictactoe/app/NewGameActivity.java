package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.os.Bundle;

public class NewGameActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.new_game, mContentFrame);
    }
}
