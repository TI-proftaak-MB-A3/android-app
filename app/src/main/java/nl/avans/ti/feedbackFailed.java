package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class feedbackFailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_failed);
    }

    public void backToHome(View view) {
        Intent openHomeIntent = new Intent(this, MainActivity.class);
        startActivity(openHomeIntent);
    }
}