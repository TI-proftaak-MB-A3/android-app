package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class feedbackPassed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_passed);
    }

    public void backToHome(View view) {
        Intent openHomeIntent = new Intent(this, MainActivity.class);
        startActivity(openHomeIntent);
    }

    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
        //TODO Connect.getConnect().getStartQuiz().backToStart();
    }
}