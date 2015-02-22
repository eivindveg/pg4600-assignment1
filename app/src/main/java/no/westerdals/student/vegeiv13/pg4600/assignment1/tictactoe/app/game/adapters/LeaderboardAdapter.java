package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.R;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.FinishedGameInfo;
import no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app.game.actors.Player;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.util.List;

public class LeaderboardAdapter extends BaseAdapter {

    private final List<FinishedGameInfo> finishedGames;
    private final LayoutInflater layoutInflater;
    private final DecimalFormat df;
    private final DateTimeFormatter dtf;

    public LeaderboardAdapter(List<FinishedGameInfo> finishedGames, LayoutInflater inflater) {
        this.finishedGames = finishedGames;
        layoutInflater = inflater;
        df = new DecimalFormat("0.00");
        dtf = DateTimeFormat.shortDateTime();
    }

    @Override
    public int getCount() {
        return finishedGames.size();
    }

    @Override
    public Object getItem(final int position) {
        return finishedGames.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final FinishedGameInfo finishedGameInfo = finishedGames.get(position);
        final Player winner = finishedGameInfo.getWinner();
        final LeaderboardRow baseRow;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.leader_board_row, null);
            baseRow = new LeaderboardRow();

            baseRow.name = (TextView) convertView.findViewById(R.id.leaderPlayerName);
            baseRow.score = (TextView) convertView.findViewById(R.id.leaderPlayerTime);
            baseRow.dateTime = (TextView) convertView.findViewById(R.id.leaderDateTime);
            convertView.setTag(baseRow);
        } else {
            baseRow = (LeaderboardRow) convertView.getTag();
        }

        if(winner == null) {
            baseRow.name.setText("Draw");
            baseRow.score.setText("");
        } else {
            baseRow.name.setText(winner.getName());
            String score = "won in " + df.format((double) winner.getElapsedTime() / 1000.0) + " seconds";
            baseRow.score.setText(score);
        }

        baseRow.dateTime.setText(dtf.print(finishedGameInfo.getStamp()));

        return convertView;
    }

    private class LeaderboardRow {
        private TextView name;
        private TextView score;
        private TextView dateTime;
    }
}
