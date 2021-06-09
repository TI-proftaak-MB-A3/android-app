package nl.avans.ti.questions;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import nl.avans.ti.Json.JsonFromData;

public class QuestionsLoader
{
    AppCompatActivity appCompatActivity;


    public QuestionsLoader(AppCompatActivity appCompatActivity)
    {
        this.appCompatActivity = appCompatActivity;
    }


    public ArrayList<Question> jsonRead()
    {
        String stringJson = JsonFromData.JsonDataFromAsset(appCompatActivity, "vragen.json");
        ArrayList<Question> questions = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(stringJson);
            JSONArray questionsJsonArray = jsonObject.getJSONArray("Questions");

            for (int i = 0; i < questionsJsonArray.length(); i++)
            {
                ArrayList<String> answers = new ArrayList<>();

                JSONObject questionObjectJson = questionsJsonArray.getJSONObject(i);

                String vraag = questionObjectJson.getString("Vraag");

                String correctAnswer = questionObjectJson.getString("Correct Antwoord");

                answers.add(correctAnswer);
                answers.add(questionObjectJson.getString("Fout antwoord 1"));
                answers.add(questionObjectJson.getString("Fout antwoord 2"));
                answers.add(questionObjectJson.getString("Fout antwoord 3"));

                String catogorie = questionObjectJson.getString("Catogorie");

                int id = questionObjectJson.getInt("id");
                Question question = new Question(vraag, answers, correctAnswer, catogorie, id);

                Log.d("QuestionsLoader", question.toString());

                questions.add(question);


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return questions;
    }

}
