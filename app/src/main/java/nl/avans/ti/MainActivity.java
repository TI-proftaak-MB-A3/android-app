package nl.avans.ti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import nl.avans.ti.Medal.MedalActivity;

public class MainActivity extends AppCompatActivity
{

    EditText editText;
    MenuHandler menuHandler;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextNumberSigned);
        menuHandler = new MenuHandler(this);
        menuHandler.start();


    }


    public void onButtonPressed(View view)
    {
        String enteredCode = editText.getText().toString();

        if (enteredCode.length() < 6)
        {
            Toast.makeText(view.getContext(), R.string.errorAboutCodeLength, Toast.LENGTH_LONG).show();
            Log.d(String.valueOf(view.getTag()), "value entered didn't meet the requirments");
        }


        //todo validate code and start activity
        Intent intent = new Intent();
        int code = Integer.parseInt(enteredCode);
        intent.putExtra("placeholder", enteredCode);


    }

    public void openMedals(View view)
    {
        Intent openMedalsIntent = new Intent(this, MedalActivity.class);
        startActivity(openMedalsIntent);
        //MQTT code
        //     protected void onCreate(Bundle savedInstanceState) {
        //         Connect connection = new Connect(this);


        //         super.onCreate(savedInstanceState);
        //         setContentView(R.layout.activity_main);

        //         Button button = findViewById(R.id.button);
        //         button.setOnClickListener((v -> {
        //             connection.publishMessage("test");
        //             System.out.println("pressed");
        //         }));

        //         Button button1 = findViewById(R.id.button2);
        //         button1.setOnClickListener((v -> {
        //             connection.subscribeToTopic();
        //         }));

        //     }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}