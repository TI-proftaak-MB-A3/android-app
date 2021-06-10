package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import nl.avans.ti.R;

public class WaitingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);
    }
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back", Toast.LENGTH_SHORT).show();
    }
}