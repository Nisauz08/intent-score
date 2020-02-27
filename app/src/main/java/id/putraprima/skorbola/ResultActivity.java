package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MatchActivity.CEK_KEY;

public class ResultActivity extends AppCompatActivity {
    private TextView homeScore;
    private TextView awayScore;

    private TextView cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        cek = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            cek.setText(extras.getString(CEK_KEY));
        }


    }
}
