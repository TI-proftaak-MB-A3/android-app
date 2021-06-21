package nl.avans.ti.Questions;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable
{
    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;
    private String catogorie;
    private int id;

    private String shuffle;


    public Question(String question, ArrayList<String> answers, String correctAnswer, String catogorie, int id)
    {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.catogorie = catogorie;
        this.id = id;

    }


    @Override
    public String toString()
    {
        return "Qeustion{" + "question='" + question + '\'' + ", answers=" + answers + ", correctAnswer='" + correctAnswer + '\'' + ", catogorie='" + catogorie + '\'' + ", id=" + id + '}';
    }

    public String getQuestion()
    {
        return question;
    }

    public ArrayList<String> getAnswers()
    {
        return answers;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public String getCatogorie()
    {
        return catogorie;
    }


    public String getShuffle()
    {
        return shuffle;
    }

    public void setShuffle(String shuffle)
    {
        this.shuffle = shuffle;
    }

    public int getId()
    {
        return id;
    }
}
