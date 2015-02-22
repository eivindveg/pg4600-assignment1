package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.R;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;

import java.util.List;

public class LeaderboardAdapter extends BaseAdapter {

    private final List<Player> players;
    private LayoutInflater layoutInflater;
    private View baseView;
    private LeaderboardRow baseRow;

    public LeaderboardAdapter(List<Player> players, LayoutInflater inflater) {
        this.players = players;
        layoutInflater = inflater;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(final int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        Player player = players.get(position);
        if(baseView == null) {
            baseView = layoutInflater.inflate(R.layout.leader_board_row, null);
            baseRow = new LeaderboardRow();

            baseRow.name = (TextView) baseView.findViewById(R.id.leaderPlayerName);
            baseRow.score = (TextView) baseView.findViewById(R.id.leaderPlayerTime);
            baseView.setTag(baseRow);
        } else {
            baseRow = (LeaderboardRow) baseView.getTag();
        }

        baseRow.name.setText(player.getName());
        baseRow.score.setText(String.valueOf(player.getElapsedTime() / 1000));


        return baseView;
    }

    private class LeaderboardRow {
        private TextView name;
        private TextView score;
    }
}
