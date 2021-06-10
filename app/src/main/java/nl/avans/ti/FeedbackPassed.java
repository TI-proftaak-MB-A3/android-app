package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Questions.Question;

public class FeedbackPassed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_passed);
        Question question = (Question) getIntent().getSerializableExtra("Question");

    }

    public void backToHome(View view) {
        Connect.getConnect().getStartQuiz().backToStart();
    }

    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
        //TODO Connect.getConnect().getStartQuiz().backToStart();
    }
}