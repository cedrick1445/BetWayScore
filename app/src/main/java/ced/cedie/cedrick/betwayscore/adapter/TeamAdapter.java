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
import ced.cedie.cedrick.betwayscore.model.TeamsItem;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private final List<TeamsItem> tData;
    Context tContext;
    View t;
    TeamViewHolder teamView;
    private final AdapterOnClick adapterOnClick;

    public TeamAdapter(Context tContext, List<TeamsItem> tData, AdapterOnClick adapterOnClick) {
        this.tContext = tContext;
        this.tData = tData;
        this.adapterOnClick = adapterOnClick;
    }


    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        t = LayoutInflater.from(parent.getContext()).inflate(R.layout.teamslayout, parent, false);
        teamView = new TeamViewHolder(t,adapterOnClick);
        return teamView;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        holder.teamAlternate.setText(tData.get(position).getStrAlternate());
        holder.teamStadium.setText(tData.get(position).getStrStadiumLocation());
        Picasso.get().load(tData.get(position).getStrTeamLogo()).into(holder.teamName);
        Picasso.get().load(tData.get(position).getStrTeamBadge()).into(holder.teamLogo);
        Picasso.get().load(tData.get(position).getStrTeamFanart3()).into(holder.teamBanner);
        holder.teamID.setText(String.valueOf(tData.get(position).getIdTeam()));
    }

    @Override
    public int getItemCount() {
        return tData.size();
    }


    public static class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView teamAlternate, teamStadium, teamID;
        public ImageView teamLogo, teamBanner, teamName;
        AdapterOnClick adapterOnClick;
        public TeamViewHolder(@NonNull View itemView, AdapterOnClick adapterOnClick) {
            super(itemView);
            teamBanner = itemView.findViewById(R.id.teamBanner);
            teamAlternate = itemView.findViewById(R.id.teamAlternate);
            teamStadium = itemView.findViewById(R.id.teamStadium);
            teamName = itemView.findViewById(R.id.teamName);
            teamLogo = itemView.findViewById(R.id.teamLogo);
            teamID = itemView.findViewById(R.id.teamID);

            this.adapterOnClick = adapterOnClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterOnClick.onAdapterClick(getAdapterPosition());

        }
    }

}
