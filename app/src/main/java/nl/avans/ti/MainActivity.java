package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nl.avans.ti.Medal.MedalActivity;
import nl.avans.ti.Medal.MedalListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMedals(View view) {
        Intent openMedalsIntent = new Intent(this, MedalActivity.class);
        startActivity(openMedalsIntent);
    }
}