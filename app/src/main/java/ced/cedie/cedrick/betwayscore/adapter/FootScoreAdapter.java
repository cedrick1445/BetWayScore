package ced.cedie.cedrick.betwayscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ced.cedie.cedrick.betwayscore.R;
import ced.cedie.cedrick.betwayscore.model.MatchItem;

public class FootScoreAdapter extends RecyclerView.Adapter<FootScoreAdapter.FootViewHolder> {

    View f;
    FootViewHolder footView;
    Context fContext;
    private final List<MatchItem> fData;

    public FootScoreAdapter(Context fContext, List<MatchItem> fData){
        this.fContext = fContext;
        this.fData = fData;
    }

    @NonNull
    @Override
    public FootViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        f = LayoutInflater.from(parent.getContext()).inflate(R.layout.footscorelayout, parent, false);
        footView = new FootViewHolder(f);
        return footView;
    }

    @Override
    public void onBindViewHolder(@NonNull FootViewHolder holder, int position) {
            holder.itemView.setTag(fData.get(position));
            holder.homeTeam.setText(fData.get(position).getHomeName());
            holder.awayTeam.setText(fData.get(position).getAwayName());
            holder.date.setText(fData.get(position).getAdded());
            holder.score.setText(fData.get(position).getScore());
            holder.location.setText(fData.get(position).getLocation());
            holder.status.setText(fData.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return fData.size();
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        public TextView homeTeam,awayTeam,date,score,location,status;
        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeam = itemView.findViewById(R.id.home);
            awayTeam = itemView.findViewById(R.id.away);
            date = itemView.findViewById(R.id.date);
            score = itemView.findViewById(R.id.score);
            location = itemView.findViewById(R.id.location);
            status = itemView.findViewById(R.id.status);
        }

    }
}

