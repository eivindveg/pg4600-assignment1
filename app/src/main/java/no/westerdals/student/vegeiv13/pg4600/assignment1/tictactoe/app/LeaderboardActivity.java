package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import com.google.gson.Gson;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.adapters.LeaderboardAdapter;

import java.util.*;

public class LeaderboardActivity extends BaseActivity {

    private Player winner;
    private SharedPreferences preferences;

    private ListView leaderListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        LayoutInflater layoutInflater = getLayoutInflater();

        leaderListView = (ListView) layoutInflater.inflate(R.layout.leader_board, mContentFrame).findViewById(R.id.leaderListView);
        preferences = getPreferences(MODE_PRIVATE);

        winner = (Player) intent.getSerializableExtra("winner");

        List<Player> players;
        if(winner == null) {
            Gson gson = new Gson();
            players = new ArrayList<>();
            Set<String> leaderboard = preferences.getStringSet("leaderboard", new HashSet<>());
            for (final String s : leaderboard) {
                Player player = gson.fromJson(s, Player.class);
                players.add(player);
            }
        } else {
            players = updateLeaderboard();
        }
        LeaderboardAdapter adapter = new LeaderboardAdapter(players, layoutInflater);
        leaderListView.setAdapter(adapter);
    }

    private List<Player> updateLeaderboard() {
        Map<Long, Player> tempMap = new TreeMap<>();
        Set<String> leaderboard = preferences.getStringSet("leaderboard", new HashSet<>());
        Gson gson = new Gson();
        tempMap.put(winner.getElapsedTime(), winner);
        for (final String s : leaderboard) {
            Player player = gson.fromJson(s, Player.class);
            tempMap.put(player.getElapsedTime(), player);
        }
        leaderboard = new TreeSet<>();
        for (final Player player : tempMap.values()) {
            leaderboard.add(gson.toJson(player));
        }
        preferences.edit().putStringSet("leaderboard", leaderboard).apply();
        return new ArrayList<>(tempMap.values());
    }
}
