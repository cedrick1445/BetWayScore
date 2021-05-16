package ced.cedie.cedrick.betwayscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ced.cedie.cedrick.betwayscore.R;
import ced.cedie.cedrick.betwayscore.model.DataItem;

public class BasketScoreAdapter extends RecyclerView.Adapter<BasketScoreAdapter.BasketHolder> {
    private final List<DataItem> data;
    Context context;
    View b;
    BasketHolder basketHolder;
    private final AdapterOnClick adapterOnClick;

    public BasketScoreAdapter(Context context, List<DataItem> data, AdapterOnClick adapterOnClick) {
        this.context = context;
        this.data = data;
        this.adapterOnClick = adapterOnClick;
    }

    @NonNull
    @Override
    public BasketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        b = LayoutInflater.from(parent.getContext()).inflate(R.layout.basketball_layout, parent, false);
        basketHolder = new BasketHolder(b,adapterOnClick);
        return basketHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BasketHolder holder, int position) {
        holder.matchIds.setText(String.valueOf(data.get(position).getId()));
        holder.homeTeamName.setText(removeParenthesis(data.get(position).getHomeTeam().getName()));
        holder.homeTeamScore.setText(data.get(position).getHomeScore().getDisplay());
        Picasso.get().load(data.get(position).getHomeTeam().getLogo()).into(holder.homeTeamLogo);

        holder.awayTeamName.setText(removeParenthesis(data.get(position).getAwayTeam().getName()));
        holder.awayTeamScore.setText(data.get(position).getAwayScore().getDisplay());
        Picasso.get().load(data.get(position).getAwayTeam().getLogo()).into(holder.awayTeamLogo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String removeParenthesis(String string) {
        return string.contains("(") ? string.substring(0, string.indexOf("(")) : string;
    }

    public static class BasketHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView homeTeamName;
        TextView homeTeamScore;
        ImageView homeTeamLogo;
        TextView awayTeamName;
        TextView awayTeamScore;
        ImageView awayTeamLogo;
        TextView matchIds;
        AdapterOnClick adapterOnClick;
        public BasketHolder(@NonNull View itemView, AdapterOnClick adapterOnClick) {
            super(itemView);
            homeTeamName = itemView.findViewById(R.id.dashboard_matches_home_team_name);
            homeTeamScore = itemView.findViewById(R.id.dashboard_matches_home_team_score);
            homeTeamLogo = itemView.findViewById(R.id.dashboard_matches_home_team_logo);
            awayTeamName = itemView.findViewById(R.id.dashboard_matches_away_team_name);
            awayTeamScore = itemView.findViewById(R.id.dashboard_matches_away_team_score);
            awayTeamLogo = itemView.findViewById(R.id.dashboard_matches_away_team_logo);
            matchIds = itemView.findViewById(R.id.dashboard_matches_id);

            this.adapterOnClick = adapterOnClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            adapterOnClick.onAdapterClick(getAdapterPosition());
        }

    }
}
