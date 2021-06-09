package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewOptionA;
    private TextView textViewOptionB;
    private TextView textViewOptionC;
    private TextView textViewOptionD;
    private TextView textViewConnectedTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        String correct = getIntent.getStringExtra("RIGHT_ANSWER");

        for (TextView view : views) {
            int pos = (int) Math.floor(Math.random() * answers.size());
            view.setText(answers.get(pos));
            answers.remove(pos);
        }

    }
}