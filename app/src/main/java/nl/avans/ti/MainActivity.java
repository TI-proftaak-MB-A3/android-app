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
        this.connect = new Connect(this);
        this.startQuiz = new StartQuiz(this.connect, this.questions);
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


        //todo validate code and start activity
        Intent intent = new Intent();
        String code = enteredCode;
        intent.putExtra("placeholder", enteredCode);


        if (!this.startQuiz.isAlreadyConnected()){
            this.startQuiz.setCode(code);
            this.startQuiz.addConnection();
            Intent intentSend = new Intent(MainActivity.this, QuestionActivity.class);
            intentSend.putExtra("QUESTION", this.startQuiz.getQuestion().getQuestion());
            intentSend.putExtra("ANSWERS", this.startQuiz.getQuestion().getAnswers());
            intentSend.putExtra("RIGHT_ANSWER", this.startQuiz.getQuestion().getCorrectAnswer());
            intentSend.putExtra("CATEGORIE", this.startQuiz.getQuestion().getCatogorie());
            startActivity(intentSend);
        }

        System.out.println(startQuiz.isAlreadyConnected());
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}