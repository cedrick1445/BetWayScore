package ced.cedie.cedrick.betwayscore.viewactivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ced.cedie.cedrick.betwayscore.MainActivity;
import ced.cedie.cedrick.betwayscore.R;
import ced.cedie.cedrick.betwayscore.adapter.AdapterOnClick;
import ced.cedie.cedrick.betwayscore.adapter.TeamAdapter;
import ced.cedie.cedrick.betwayscore.model.TeamsItem;
import ced.cedie.cedrick.betwayscore.network.Connection;
import ced.cedie.cedrick.betwayscore.viewmodel.ViewModels;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BasketTeamView extends AppCompatActivity implements AdapterOnClick, View.OnClickListener {
    static RecyclerView bteamRecycler;
    private final List<TeamsItem> basketTeams = new ArrayList<>();
    RecyclerView.LayoutManager btLayout;
    private TeamAdapter teamAdapter;
    ViewModels viewBTModels;
    Connection connectionBT;
    Dialog dialogBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketteamview);
        connectionBT = new Connection();
        if (connectionBT.isConnected(this)){
            observeBT();
        }
        TextView textView4 = findViewById(R.id.click_scoreB);
        textView4.setOnClickListener(this);
    }

    private void initBTeamsR(List<TeamsItem> btaa){
        bteamRecycler = findViewById(R.id.teamBRecycleView);
        bteamRecycler.setHasFixedSize(true);
        btLayout = new LinearLayoutManager(this);
        bteamRecycler.setLayoutManager(btLayout);
        teamAdapter = new TeamAdapter(this, btaa, this);
        bteamRecycler.setAdapter(teamAdapter);
    }

    private void observeBT() {
        viewBTModels = new ViewModelProvider(this).get(ViewModels.class);
        viewBTModels.getBTeams();
        viewBTModels.getTeamList().observe(this, databt ->{
            if (databt.size() == 0){
                Log.d("TeamList","Bakit wala din to laman please");
            }else {
                basketTeams.addAll(databt);
                initBTeamsR(databt);
                teamAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onBackPressed() {
        Intent intentH = new Intent(this, MainActivity.class);
        startActivity(intentH);
    }

    @Override
    public void onAdapterClick(int positon) {
        dialogBT = new Dialog(this);
        dialogBT.setContentView(R.layout.dialog_team);
        dialogBT.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView titleTeam = dialogBT.findViewById(R.id.title_nameD);
        ImageView jerseyTeam = dialogBT.findViewById(R.id.team_jersey);
        ImageView badgeLogo = dialogBT.findViewById(R.id.team_badge);
        TextView descTeam = dialogBT.findViewById(R.id.team_desc);
        CardView closeDia = dialogBT.findViewById(R.id.team_dialog_close);

        TeamsItem bTeam = basketTeams.get(positon);

        Picasso.get().load(bTeam.getStrTeamLogo()).into(titleTeam);
        Picasso.get().load(bTeam.getStrTeamJersey()).into(jerseyTeam);
        Picasso.get().load(bTeam.getStrTeamBadge()).into(badgeLogo);
        descTeam.setText(bTeam.getStrDescriptionEN());
        dialogBT.show();
        closeDia.setOnClickListener(v -> dialogBT.dismiss());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.click_scoreB) {
            Intent mIntent = new Intent(getApplication(), BasketScoreView.class);
            startActivity(mIntent);
        }
    }
}