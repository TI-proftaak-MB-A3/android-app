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

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Medal.MedalActivity;
import nl.avans.ti.Quiz.StartQuiz;

public class MainActivity extends AppCompatActivity
{
    private Connect connect;

    EditText editText;
    MenuHandler menuHandler;
    private StartQuiz startQuiz;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextNumberSigned);
        menuHandler = new MenuHandler(this);
        menuHandler.start();
        this.connect = new Connect(this);

    }


    public void onButtonPressed(View view)
    {
        String enteredCode = editText.getText().toString();

        if (enteredCode.length() < 6)
        {
            Toast.makeText(view.getContext(), R.string.errorAboutCodeLength, Toast.LENGTH_LONG).show();
            Log.d(String.valueOf(view.getTag()), "value entered didn't meet the requirments");
        }else
        {
            //todo validate code and start activity
            Intent intent = new Intent();
            int code = Integer.parseInt(enteredCode);
            intent.putExtra("placeholder", enteredCode);

        }



        this.startQuiz = new StartQuiz(this.connect, code);
        this.startQuiz.addConnection();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}