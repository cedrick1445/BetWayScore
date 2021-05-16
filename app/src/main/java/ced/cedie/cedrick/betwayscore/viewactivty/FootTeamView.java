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
public class FootTeamView extends AppCompatActivity implements AdapterOnClick, View.OnClickListener {
    static RecyclerView fteamsRecycler;
    private final List<TeamsItem> footTeam = new ArrayList<>();
    RecyclerView.LayoutManager ftLayout;
    private TeamAdapter teamAdapter;
    ViewModels viewFTModels;
    Connection connectionFT;
    Dialog dialogFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footteamview);
        connectionFT = new Connection();
        if (connectionFT.isConnected(this)){
            observeFT();
        }
        TextView textView2 = findViewById(R.id.click_scoreF);
        textView2.setOnClickListener(this);
    }

    private void initFTeamsR(List<TeamsItem> ftaa){
        fteamsRecycler = findViewById(R.id.teamFRecycleView);
        fteamsRecycler.setHasFixedSize(true);
        ftLayout = new LinearLayoutManager(this);
        fteamsRecycler.setLayoutManager(ftLayout);
        teamAdapter = new TeamAdapter(this,ftaa, this);
        fteamsRecycler.setAdapter(teamAdapter);
    }

    private void observeFT() {
        viewFTModels = new ViewModelProvider(this).get(ViewModels.class);
        viewFTModels.getFootTeam();
        viewFTModels.getTeamList().observe(this, dataft ->{
            if (dataft.size() == 0){
                Log.d("TeamList", "Bakit walang lamaaaan!");
            }else {
                footTeam.addAll(dataft);
                initFTeamsR(dataft);
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
        dialogFT = new Dialog(this);
        dialogFT.setContentView(R.layout.dialog_team);
        dialogFT.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView titleTeam = dialogFT.findViewById(R.id.title_nameD);
        ImageView jerseyTeam = dialogFT.findViewById(R.id.team_jersey);
        ImageView badgeLogo = dialogFT.findViewById(R.id.team_badge);
        TextView descTeam = dialogFT.findViewById(R.id.team_desc);
        CardView closeDia = dialogFT.findViewById(R.id.team_dialog_close);

        TeamsItem fTeam = footTeam.get(positon);

        Picasso.get().load(fTeam.getStrTeamLogo()).into(titleTeam);
        Picasso.get().load(fTeam.getStrTeamJersey()).into(jerseyTeam);
        Picasso.get().load(fTeam.getStrTeamBadge()).into(badgeLogo);
        descTeam.setText(fTeam.getStrDescriptionEN());
        dialogFT.show();
        closeDia.setOnClickListener(v -> dialogFT.dismiss());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.click_scoreF) {
            Intent mIntent = new Intent(getApplication(), FootScoreView.class);
            startActivity(mIntent);
        }
    }
}