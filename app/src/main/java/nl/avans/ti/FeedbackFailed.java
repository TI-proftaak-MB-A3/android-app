package nl.avans.ti;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Questions.Question;
import nl.avans.ti.R;

public class FeedbackFailed extends AppCompatActivity {
    private TextView answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_failed);

        this.answerView = findViewById(R.id.textview_correct_answer);

        Question question = (Question)getIntent().getSerializableExtra("Question");
        String answer = question.getCorrectAnswer();
        this.answerView.setText(answer);

    }

    public void backToHome(View view) {
        Connect.getConnect().getStartQuiz().backToStart();
    }
}
