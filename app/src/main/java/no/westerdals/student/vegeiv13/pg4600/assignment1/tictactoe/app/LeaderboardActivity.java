package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.FinishedGameInfo;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.adapters.LeaderboardAdapter;

import java.util.*;

public class LeaderboardActivity extends BaseActivity {

    private FinishedGameInfo finishedGame;
    private SharedPreferences preferences;

    private ListView leaderListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        LayoutInflater layoutInflater = getLayoutInflater();
        preferences = getPreferences(MODE_PRIVATE);
        leaderListView = (ListView) layoutInflater.inflate(R.layout.leader_board, mContentFrame).findViewById(R.id.leaderListView);
        finishedGame = (FinishedGameInfo) intent.getSerializableExtra("finished");
        populateList(layoutInflater);
    }

    private void populateList(LayoutInflater layoutInflater) {
        final List<FinishedGameInfo> finishedGames;
        if (finishedGame != null && finishedGame.getWinner() != null) {
            Toast.makeText(this, finishedGame.getWinner().getName() + " has won!", Toast.LENGTH_SHORT).show();
            finishedGames = updateLeaderboard();
        } else {
            Gson gson = new Gson();
            finishedGames = new ArrayList<>();
            Set<String> leaderboard = preferences.getStringSet("leaderboard", new HashSet<>());
            for (final String s : leaderboard) {
                FinishedGameInfo player = gson.fromJson(s, FinishedGameInfo.class);
                finishedGames.add(player);
            }
            Collections.sort(finishedGames);
        }
        LeaderboardAdapter adapter = new LeaderboardAdapter(finishedGames, layoutInflater);
        leaderListView.setAdapter(adapter);
    }

    private List<FinishedGameInfo> updateLeaderboard() {
        List<FinishedGameInfo> games = new ArrayList<>();
        Set<String> leaderboard = preferences.getStringSet("leaderboard", new HashSet<>());
        Gson gson = new Gson();
        games.add(finishedGame);
        System.out.println(leaderboard);
        for (final String s : leaderboard) {
            FinishedGameInfo player = gson.fromJson(s, FinishedGameInfo.class);
            games.add(player);
        }
        leaderboard = new HashSet<>();
        for (final FinishedGameInfo game : games) {
            leaderboard.add(gson.toJson(game));
        }
        preferences.edit().putStringSet("leaderboard", leaderboard).apply();
        Collections.sort(games);
        int end = games.size() >= 5 ? 5 : games.size();
        return games.subList(0, end);
    }
}
