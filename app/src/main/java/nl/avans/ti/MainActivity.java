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

import java.util.List;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Quiz.StartQuiz;
import nl.avans.ti.Questions.Question;
import nl.avans.ti.Questions.QuestionsLoader;

public class MainActivity extends AppCompatActivity
{
    private Connect connect;

    EditText editText;
    MenuHandler menuHandler;
    private StartQuiz startQuiz;
    List<Question> questions;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuestionsLoader questionsLoader = new QuestionsLoader(this);
        questions = questionsLoader.jsonRead();


        editText = findViewById(R.id.editTextNumberSigned);
        menuHandler = new MenuHandler(this);
        menuHandler.start();
        this.connect = Connect.getConnect(this);
        this.startQuiz = connect.getStartQuiz();

//        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
//        startActivity(intent);

        if (this.startQuiz.isAlreadyConnected())
        {
            startQuizWithIntent();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onButtonPressed(View view)
    {
        String enteredCode = editText.getText().toString();

        if (enteredCode.length() < 6)
        {
            Toast.makeText(view.getContext(), R.string.errorAboutCodeLength, Toast.LENGTH_LONG).show();
            Log.d(String.valueOf(view.getTag()), "value entered didn't meet the requirments");
        }

        String code = enteredCode;

        if (!this.startQuiz.isAlreadyConnected()){

            this.startQuiz.setCode(code);
            this.startQuiz.addConnection();
        }
        System.out.println(startQuiz.isAlreadyConnected());

        startQuizWithIntent();
    }



    public void startQuizWithIntent()
    {
        Intent intent = new Intent();
        intent.putExtra("placeholder", startQuiz.getCode());


        Log.d(this.getAttributionTag(), "startQuizWithIntent: Starting new activity");



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}