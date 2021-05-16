package ced.cedie.cedrick.betwayscore.viewactivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ced.cedie.cedrick.betwayscore.MainActivity;
import ced.cedie.cedrick.betwayscore.R;
import ced.cedie.cedrick.betwayscore.adapter.FootScoreAdapter;
import ced.cedie.cedrick.betwayscore.model.MatchItem;
import ced.cedie.cedrick.betwayscore.network.Connection;
import ced.cedie.cedrick.betwayscore.viewmodel.ViewModels;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FootScoreView extends AppCompatActivity implements View.OnClickListener {
    static RecyclerView recyclerFoot;
    private final List<MatchItem> dataFoot = new ArrayList<>();
    RecyclerView.LayoutManager rfLayout;
    private FootScoreAdapter footAdapter;
    ViewModels viewFModels;
    Connection connectionF;
    RelativeLayout noData;
    private LinearLayout loadingf,contentf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footscoreview);
        connectionF = new Connection();
        if (connectionF.isConnected(this)) {
            footLoading();
            observeFData();
        }
        TextView textView1 = findViewById(R.id.click_teamF);
        textView1.setOnClickListener(this);
    }

    private void footLoading() {
        contentf = findViewById(R.id.content_view);
        loadingf = findViewById(R.id.loading_view);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            loadingf.setVisibility(View.GONE);
            contentf.setVisibility(View.VISIBLE);
        }, 3500);
    }

    private void observeFData() {
        viewFModels = new ViewModelProvider(this).get(ViewModels.class);
        viewFModels.getFootScore();
        viewFModels.getFootList().observe(this, dattaf ->{
            if (dattaf.size() == 0){
                noData.setVisibility(View.VISIBLE);
                Log.d("FootScore", "Ilabas nyo yung score");
            }else {
                dataFoot.addAll(dattaf);
                initFRecycle(dattaf);
                footAdapter.notifyDataSetChanged();
            }
        });
    }
    private void initFRecycle(List<MatchItem> fata){
        noData = findViewById(R.id.noEvent);
        recyclerFoot = findViewById(R.id.scoreRecycleView);
        recyclerFoot.setHasFixedSize(true);
        rfLayout = new LinearLayoutManager(this);
        recyclerFoot.setLayoutManager(rfLayout);
        footAdapter = new FootScoreAdapter(this,fata);
        recyclerFoot.setAdapter(footAdapter);
    }
    public void onBackPressed() {
        Intent intentH = new Intent(this, MainActivity.class);
        startActivity(intentH);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.click_teamF) {
            Intent mIntent = new Intent(getApplication(), FootTeamView.class);
            startActivity(mIntent);
        }
    }
}