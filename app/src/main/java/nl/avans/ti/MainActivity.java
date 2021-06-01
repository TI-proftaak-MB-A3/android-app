package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import nl.avans.ti.MQTT.Connect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Connect connection = new Connect(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}