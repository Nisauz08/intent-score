package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.AWAYLOGO_KEY;
import static id.putraprima.skorbola.MainActivity.AWAYTEAM_KEY;
import static id.putraprima.skorbola.MainActivity.HOMELOGO_KEY;
import static id.putraprima.skorbola.MainActivity.HOMETEAM_KEY;

public class MatchActivity extends AppCompatActivity {
    private Uri uriHome;
    private Uri uriAway;
    private ImageView imageView;
    private ImageView imageView2;
    private TextView home_teamText;
    private TextView away_teamText;
    private int scoreHome=0;
    private int scoreAway=0;
    private TextView homeScore;
    private TextView awayScore;
    

    public static final String CEK_KEY = "cek";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        home_teamText = findViewById(R.id.txt_home);
        away_teamText = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);

        imageView = findViewById(R.id.home_logo);
        imageView2 = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            home_teamText.setText(extras.getString(HOMETEAM_KEY));
            away_teamText.setText(extras.getString(AWAYTEAM_KEY));
            uriHome = Uri.parse(extras.getString(HOMELOGO_KEY));
            uriAway = Uri.parse(extras.getString(AWAYLOGO_KEY));

            Bitmap bitmapHome = null;
            Bitmap bitmapAway = null;

            try {
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriHome);
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriAway);
            } catch (IOException e){
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmapHome);
            imageView2.setImageBitmap(bitmapAway);
        }
    }

    public void handleCekHasil(View view) {
        String cek = null;
        Intent intent = new Intent(this, ResultActivity.class);
        if (scoreHome > scoreAway){
            cek = home_teamText.getText().toString() + "Tim Lu Menang !";
        } else if (scoreAway > scoreHome){
            cek = away_teamText.getText().toString() + "Tim Lu Menang !";
        } else {
            cek = (away_teamText.getText().toString() + home_teamText.getText().toString() + "Hahaha Seri");
        }
        intent.putExtra(CEK_KEY, cek);
        startActivity(intent);
    }

    public void handleHomeScore(View view) {
        scoreHome ++ ;
        homeScore.setText(String.valueOf(scoreHome));
    }

    public void handleAwayScore(View view) {
        scoreAway ++;
        awayScore.setText(String.valueOf(scoreAway));
    }

}
