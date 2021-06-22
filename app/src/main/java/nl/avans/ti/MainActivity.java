package nl.avans.ti;


import android.content.Intent;
import android.os.Build;
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
import nl.avans.ti.Questions.Question;
import nl.avans.ti.Questions.QuestionsLoader;
import nl.avans.ti.Quiz.StartQuiz;

public class MainActivity extends AppCompatActivity
{


    private Connect connect;

    EditText editText;
    MenuHandler menuHandler;
    List<Question> questions;
    private StartQuiz startQuiz;

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
        this.connect = Connect.getConnect(this, questions);
        this.startQuiz = connect.getStartQuiz();

        if (this.startQuiz.isAlreadyConnected())
        {
            startQuizWithIntent();
        }


    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    /**
     * Method which checks if the code is correct and after that checks if
     * the button has been pressed before or not, if not, it will add the code
     * to the startQuiz class and adds an connection
     * @param view
     */
    public void onButtonPressed(View view)
    {
        String enteredCode = editText.getText().toString();

        if (enteredCode.length() < 6)
        {
            Toast.makeText(view.getContext(), R.string.errorAboutCodeLength, Toast.LENGTH_LONG).show();
            Log.d(String.valueOf(view.getTag()), "value entered didn't meet the requirments");
        }
        else
        {

            if (!this.startQuiz.isTryingToConnect())
            {
                if (!this.startQuiz.isAlreadyConnected()  /*|| !this.startQuiz.getCode().equals(enteredCode)*/)
                {
                    this.startQuiz.setCode(enteredCode);
                    this.startQuiz.addConnection();
                }
            }
            else
            {
                Toast.makeText(view.getContext(), R.string.errorConnection, Toast.LENGTH_LONG).show();
            }

            System.out.println(startQuiz.isAlreadyConnected());
        }

    }


    /**
     * Method which send the question as an extra and starts the
     * QuestionActivity using an Intent
     */
    public void startQuizWithIntent()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            Log.d(this.getAttributionTag(), "startQuizWithIntent: Starting new activity");
        }

        Question question = this.startQuiz.getQuestion();

        Intent intentSend = new Intent(MainActivity.this, QuestionActivity.class);

        intentSend.putExtra("Question",question);
        startActivity(intentSend);

        System.out.println(startQuiz.isAlreadyConnected());
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }

    /**
     * Intent which opens the waiting screen
     */
    public void gotoWaitingscreen()
    {
        Intent intent = new Intent(this, WaitingScreen.class);
        startActivity(intent);


    }
}