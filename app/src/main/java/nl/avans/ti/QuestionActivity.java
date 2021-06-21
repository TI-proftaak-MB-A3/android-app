package nl.avans.ti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Quiz.StartQuiz;

public class QuestionActivity extends AppCompatActivity
{
    private TextView textViewQuestion;
    private TextView textViewOptionA;
    private TextView textViewOptionB;
    private TextView textViewOptionC;
    private TextView textViewOptionD;
    private TextView textViewConnectedTo;


    private int shuffle;
    String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        this.textViewQuestion = findViewById(R.id.textViewQuestion);
        this.textViewOptionA = findViewById(R.id.textViewOptionA);
        this.textViewOptionB = findViewById(R.id.textViewOptionB);
        this.textViewOptionC = findViewById(R.id.textViewOptionC);
        this.textViewOptionD = findViewById(R.id.textViewOptionD);
        this.textViewConnectedTo = findViewById(R.id.textViewConnectedTo);

        ArrayList<TextView> views = new ArrayList<>();
        views.add(this.textViewOptionA);
        views.add(this.textViewOptionB);
        views.add(this.textViewOptionC);
        views.add(this.textViewOptionD);

        Intent getIntent = getIntent();
        String question = getIntent.getStringExtra("QUESTION");
        this.textViewQuestion.setText(question);

        String categorie = getIntent.getStringExtra("CATEGORIE");
        this.textViewConnectedTo.setText(categorie);

        ArrayList<String> answers = getIntent.getStringArrayListExtra("ANSWERS");

        correctAnswer = getIntent.getStringExtra("RIGHT_ANSWER");

        shuffle = Integer.parseInt(getIntent.getStringExtra("SHUFFLE"));


        for (int i = 0; i < views.size(); i++)
        {
            TextView textView = views.get(i);
            String answer = answers.get((shuffle + i) % 4);
            textView.setText(answer);
        }


        Connect.getConnect().getStartQuiz().setAnswerChecker(this::checkAnswer);
    }

    public boolean checkAnswer(String recievedLetter)
    {
        String answer;
        recievedLetter = recievedLetter.toUpperCase();

        switch (recievedLetter)
        {
            case "A":
                answer = textViewOptionA.getText().toString();
                break;
            case "B":
                answer = textViewOptionB.getText().toString();
                break;
            case "C":
                answer = textViewOptionC.getText().toString();
                break;
            case "D":
                answer = textViewOptionD.getText().toString();
                break;
            default:
                return false;
        }

        return correctAnswer.equals(answer);

    }


}