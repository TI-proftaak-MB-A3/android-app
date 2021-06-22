package nl.avans.ti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Questions.Question;
import nl.avans.ti.Quiz.StartQuiz;

public class QuestionActivity extends AppCompatActivity implements StartQuiz.AnswerChecker
{
    private TextView textViewQuestion;
    private TextView textViewOptionA;
    private TextView textViewOptionB;
    private TextView textViewOptionC;
    private TextView textViewOptionD;
    private TextView textViewConnectedTo;


    private int shuffle;
    String correctAnswer;

    Question question;

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

        question = (Question) getIntent().getSerializableExtra("Question");

        String questionString = question.getQuestion();
        this.textViewQuestion.setText(questionString);

        String categorie = question.getCatogorie();
        this.textViewConnectedTo.setText(categorie);

        ArrayList<String> answers = question.getAnswers();

        correctAnswer = question.getCorrectAnswer();

        shuffle = Integer.parseInt(question.getShuffle());


        for (int i = 0; i < views.size(); i++)
        {
            TextView textView = views.get(i);
            String answer = answers.get((shuffle + i) % 4);
            textView.setText(answer);
        }


        Connect.getConnect().getStartQuiz().setAnswerChecker(this);

    }

    public Question getQuestion()
    {
        return question;
    }


    /**
     * Method which checks if the received letter is equal to
     * the letters A, B, C or D
     * @param recievedLetter
     * @return
     */
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

    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }

}