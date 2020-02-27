package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String HOMETEAM_KEY ="home_team";
    public static final String AWAYTEAM_KEY ="away_team";
    public static final String HOMELOGO_KEY ="home_logo";
    public static final String AWAYLOGO_KEY ="away_logo";

    private static final String TAG = MainActivity.class.getCanonicalName();
    private Uri imageHome = null;
    private Uri imageAway = null;
    private EditText hometeamInput;
    private EditText awayteamInput;
    private ImageView homelogo;
    private ImageView awaylogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hometeamInput = findViewById(R.id.home_team);
        awayteamInput = findViewById(R.id.away_team);
        homelogo = findViewById(R.id.home_logo);
        awaylogo = findViewById(R.id.away_logo);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == 1) {
            if (data != null) {
                try {
                    imageHome = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageHome);
                    homelogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Gagal memuat Gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if (requestCode == 2) {
            if (data != null) {
                try {
                    imageAway = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageAway);
                    awaylogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Gagal memuat Gambar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleLogoHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleLogoAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    public void handleNext(View view) {
        String home_team = hometeamInput.getText().toString();
        String away_team = awayteamInput.getText().toString();

        if (home_team.isEmpty()){
            hometeamInput.setError("Dilawan Malese, Ayo Ngisi sek!");
        } else if (away_team.isEmpty()){
            awayteamInput.setError("Dilawan Malese, Ayo Ngisi sek!");
        } else {
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra(HOMETEAM_KEY, home_team);
            intent.putExtra(AWAYTEAM_KEY, away_team);
            intent.putExtra(HOMELOGO_KEY, homelogo.toString());
            intent.putExtra(AWAYLOGO_KEY, awaylogo.toString());

            startActivity(intent);
        }
    }
}
