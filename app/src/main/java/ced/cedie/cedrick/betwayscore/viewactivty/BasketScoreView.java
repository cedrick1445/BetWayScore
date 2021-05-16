package ced.cedie.cedrick.betwayscore.viewactivty;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ced.cedie.cedrick.betwayscore.MainActivity;
import ced.cedie.cedrick.betwayscore.R;
import ced.cedie.cedrick.betwayscore.adapter.AdapterOnClick;
import ced.cedie.cedrick.betwayscore.adapter.BasketScoreAdapter;
import ced.cedie.cedrick.betwayscore.model.DataItem;
import ced.cedie.cedrick.betwayscore.network.Connection;
import ced.cedie.cedrick.betwayscore.viewmodel.ViewModels;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BasketScoreView extends AppCompatActivity implements AdapterOnClick, View.OnClickListener {
     static RecyclerView recyclerBasket;
    Dialog matchesDialog;
    DataItem bscore;
    private final List<DataItem> dataModel = new ArrayList<>();
    RecyclerView.LayoutManager rbLayout;
    private BasketScoreAdapter basketScoreAdapter;
    ViewModels viewBModels;
    Connection connectionB;
    RelativeLayout noBasket;
    private LinearLayout loadingB,contentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketscoreview);
        connectionB = new Connection();
        if (connectionB.isConnected(this)) {
            basketLoading();
            observeData();
        }
        TextView textView3 = findViewById(R.id.click_teamB);
        textView3.setOnClickListener(this);
    }

    private void basketLoading() {
        contentB = findViewById(R.id.content_BView);
        loadingB = findViewById(R.id.loading_BView);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            loadingB.setVisibility(View.GONE);
            contentB.setVisibility(View.VISIBLE);
        }, 3000);
    }

    private void observeData() {
        viewBModels = new ViewModelProvider(this).get(ViewModels.class);
        viewBModels.getBasketScore();
        viewBModels.getBasketList().observe(this, dataaas -> {
            if (dataaas.size() == 0) {
                Log.d("BasketScore", "Taena what happen");
                noBasket.setVisibility(View.VISIBLE);
            } else {
                dataModel.addAll(dataaas);
                initRecycle(dataaas);
                basketScoreAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecycle(List<DataItem> dataa) {
        noBasket = findViewById(R.id.noBasket);
        recyclerBasket = findViewById(R.id.basketRecycleView);
        recyclerBasket.setHasFixedSize(true);
        rbLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerBasket.setLayoutManager(rbLayout);
        basketScoreAdapter = new BasketScoreAdapter(this, dataa, this);
        recyclerBasket.setAdapter(basketScoreAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intentH = new Intent(this, MainActivity.class);
        startActivity(intentH);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onAdapterClick(int position) {

        matchesDialog = new Dialog(this);
        matchesDialog.setContentView(R.layout.dialog_basketball);
        matchesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView homeTeamName = matchesDialog.findViewById(R.id.dialog_event_team_home_name);
        ImageView homeTeamLogo = matchesDialog.findViewById(R.id.dialog_event_team_home_logo);
        TextView awayTeamName = matchesDialog.findViewById(R.id.dialog_event_team_away_name);
        ImageView awayTeamLogo = matchesDialog.findViewById(R.id.dialog_event_team_away_logo);
        TextView scores = matchesDialog.findViewById(R.id.dialog_event_team_scores);
        TextView homeFirst = matchesDialog.findViewById(R.id.dialog_event_home_first);
        TextView homeSecond = matchesDialog.findViewById(R.id.dialog_event_home_second);
        TextView homeThird = matchesDialog.findViewById(R.id.dialog_event_home_third);
        TextView homeFourth = matchesDialog.findViewById(R.id.dialog_event_home_fourth);
        TextView awayFirst = matchesDialog.findViewById(R.id.dialog_event_away_first);
        TextView awaySecond = matchesDialog.findViewById(R.id.dialog_event_away_second);
        TextView awayThird = matchesDialog.findViewById(R.id.dialog_event_away_third);
        TextView awayFourth = matchesDialog.findViewById(R.id.dialog_event_away_fourth);
        CardView close = matchesDialog.findViewById(R.id.dialog_event_close);

        TextView homeFt = matchesDialog.findViewById(R.id.dialog_event_home_ft);
        TextView homeFg = matchesDialog.findViewById(R.id.dialog_event_home_fg);
        TextView home2pt = matchesDialog.findViewById(R.id.dialog_event_home_two);
        TextView home3pt = matchesDialog.findViewById(R.id.dialog_event_home_three);
        TextView homeFouls = matchesDialog.findViewById(R.id.dialog_event_home_fouls);
        TextView awayFt = matchesDialog.findViewById(R.id.dialog_event_away_ft);
        TextView awayFg = matchesDialog.findViewById(R.id.dialog_event_away_fg);
        TextView away2pt = matchesDialog.findViewById(R.id.dialog_event_away_two);
        TextView away3pt = matchesDialog.findViewById(R.id.dialog_event_away_three);
        TextView awayFouls = matchesDialog.findViewById(R.id.dialog_event_away_fouls);

        bscore = dataModel.get(position);

        scores.setText(bscore.getHomeScore().getDisplay() + " - " + bscore.getAwayScore().getDisplay());
        homeTeamName.setText(removeParenthesis(bscore.getHomeTeam().getName()));
        Picasso.get().load(bscore.getHomeTeam().getLogo()).into(homeTeamLogo);
        awayTeamName.setText(removeParenthesis(bscore.getAwayTeam().getName()));
        Picasso.get().load(bscore.getAwayTeam().getLogo()).into(awayTeamLogo);

        homeFirst.setText(bscore.getHomeScore().getPeriod1());
        homeSecond.setText(nullable(bscore.getHomeScore().getPeriod2()));
        homeThird.setText(nullable(bscore.getHomeScore().getPeriod3()));
        homeFourth.setText(nullable(bscore.getHomeScore().getPeriod4()));
        awayFirst.setText(nullable(bscore.getAwayScore().getPeriod1()));
        awaySecond.setText(nullable(bscore.getAwayScore().getPeriod2()));
        awayThird.setText(nullable(bscore.getAwayScore().getPeriod3()));
        awayFourth.setText(nullable(bscore.getAwayScore().getPeriod4()));

        if (bscore.getMainStat() != null) {
            if (bscore.getMainStat().getFreeThrows() != null) {
                homeFt.setText(nullable(bscore.getMainStat().getFreeThrows().getHome()));
                awayFt.setText(nullable(bscore.getMainStat().getFreeThrows().getAway()));
            }
            if (bscore.getMainStat().getFieldGoals() != null) {
                homeFg.setText(nullable(bscore.getMainStat().getFieldGoals().getHome()));
                awayFg.setText(nullable(bscore.getMainStat().getFieldGoals().getAway()));
            }
            if (bscore.getMainStat().getJsonMember2Pointers() != null) {
                home2pt.setText(nullable(bscore.getMainStat().getJsonMember2Pointers().getHome()));
                away2pt.setText(nullable(bscore.getMainStat().getJsonMember2Pointers().getAway()));
            }
            if (bscore.getMainStat().getJsonMember3Pointers() != null) {
                home3pt.setText(nullable(bscore.getMainStat().getJsonMember3Pointers().getHome()));
                away3pt.setText(nullable(bscore.getMainStat().getJsonMember3Pointers().getAway()));
            }
            if (bscore.getMainStat().getFouls() != null) {
                homeFouls.setText(nullable(bscore.getMainStat().getFouls().getHome()));
                awayFouls.setText(nullable(bscore.getMainStat().getFouls().getAway()));
            }
        }
        matchesDialog.show();
        close.setOnClickListener(v -> matchesDialog.dismiss());
    }

    public String removeParenthesis(String string) {
        return string.contains("(") ? string.substring(0, string.indexOf("(")) : string;
    }

    public String nullable(String string) {
        return string != null ? string : "-";
    }

    public String nullable(Integer string) {
        return string == null ? "-" : String.valueOf(string);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.click_teamB) {
            Intent mIntent = new Intent(getApplicationContext(), BasketTeamView.class);
            startActivity(mIntent);
        }
    }
}

