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


        for (Attraction attraction : attractions)
        {
            String name = attraction.getName().toLowerCase();
            String catogorie = question.getCatogorie().toLowerCase();

            if (name.equals(catogorie))
            {
                System.out.println("Hoegaboega");
            }


        }




//            if (a.getName().toLowerCase().equals(question.getCatogorie().toLowerCase()))
//            {
//                if (!a.getHasCheckpointOne())
//                {
//                    a.setHasCheckpointOne(true);
//                }
//                else if (a.getHasCheckpointOne() && !a.getHasCheckpointTwo())
//                {
//                    a.setHasCheckpointTwo(true);
//                }
//                else if (a.getHasCheckpointOne() && a.getHasCheckpointTwo() && !a.getHasCheckpointThree())
//                {
//                    a.setHasCheckpointThree(true);
//                    a.setHasMedal(true);
//                }
//            }


        LoadAttractionsJSON.getInstance(this).save();
    }

}