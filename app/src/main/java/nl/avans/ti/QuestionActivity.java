package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent getIntent = getIntent();

        String question = getIntent.getStringExtra("QUESTION");
        System.out.println(question);
        ArrayList<String> answers = getIntent.getStringArrayListExtra("ANSWERS");

        for (String s : answers) {
            System.out.println(s);
        }

        String correct = getIntent.getStringExtra("RIGHT_ANSWER");
        System.out.println(correct);

    }
}