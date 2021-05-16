package ced.cedie.cedrick.betwayscore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import ced.cedie.cedrick.betwayscore.viewactivty.BasketScoreView;
import ced.cedie.cedrick.betwayscore.viewactivty.FootScoreView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int counter;
    LinearLayout homeOne;
    LinearLayout homeTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instruction();
        homeOne = findViewById(R.id.footballHome);
        homeOne.setOnClickListener(this);
        homeTwo = findViewById(R.id.basketBallHome);
        homeTwo.setOnClickListener(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.status_color_home));
    }
    private void instruction() {
        new ShowcaseView.Builder(MainActivity.this)
                .setTarget( new ViewTarget( (findViewById(R.id.footballHome))))
                .setContentTitle("Sports")
                .setContentText("Click the views to see the live match and teams")
                .hideOnTouchOutside()
                .build();
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.footballHome:
                Intent mIntent = new Intent(getApplication(), FootScoreView.class);
                startActivity(mIntent);
                break;
            case R.id.basketBallHome:
                Intent cIntent = new Intent(getApplication(), BasketScoreView.class);
                startActivity(cIntent);
                break;
        }
    }
    public void onBackPressed() {
        if (counter == 0) {
            counter = 1;
            Toast.makeText(this, "Press Again to Exit!", Toast.LENGTH_SHORT).show();
        } else {
            super.finishAffinity();
        }
    }
}