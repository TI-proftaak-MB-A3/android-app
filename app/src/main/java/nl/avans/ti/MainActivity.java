package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import nl.avans.ti.MQTT.Connect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Connect connection = new Connect(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((v -> {
            connection.publishMessage("test");
            System.out.println("pressed");
        }));

        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener((v -> {
            connection.subscribeToTopic();
        }));


    }
}