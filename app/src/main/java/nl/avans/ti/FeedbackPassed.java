package nl.avans.ti;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.Medal.Attraction;
import nl.avans.ti.Medal.LoadAttractionsJSON;
import nl.avans.ti.Questions.Question;
import nl.avans.ti.R;

public class FeedbackPassed extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_passed);
        Question question = (Question) getIntent().getSerializableExtra("Question");

        updateMedals(question);
    }

    public void backToHome(View view)
    {
        Connect.getConnect().getStartQuiz().backToStart();
    }

    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
        //TODO Connect.getConnect().getStartQuiz().backToStart();
    }


    private void updateMedals(Question question)
    {
        LinkedList<Attraction> attractions = LoadAttractionsJSON.getInstance(this).getAttractions();

        System.out.println(attractions.size() + "size of atractions");
        int i = 0;
        for (Attraction attraction : attractions)
        {
            String name = attraction.getName().toLowerCase();
            String catogorie = question.getCatogorie().toLowerCase();

            if (name.equals(catogorie))
            {
                System.out.println("Hoegaboega");
                if (!attraction.getHasCheckpointOne())
                {
                    System.out.println("hey 1");
                    attraction.setHasCheckpointOne(true);
                }
                else if (attraction.getHasCheckpointOne() && !attraction.getHasCheckpointTwo())
                {
                    System.out.println("hey 2");
                    attraction.setHasCheckpointTwo(true);
                }
                else if (attraction.getHasCheckpointOne() && attraction.getHasCheckpointTwo() && !attraction.getHasCheckpointThree())
                {
                    System.out.println("hey 3");
                    attraction.setHasCheckpointThree(true);
                    attraction.setHasMedal(true);
                }

                System.out.println("it is something" + attraction.getHasCheckpointOne());
                attraction.checkImage();

                System.out.println(attraction.toString() + " " + i);
            }

            i++;
        }


        System.out.println("Hoi Hey");
        LoadAttractionsJSON.getInstance(this).save(attractions);
        System.out.println("Hey hoi");


    }

}