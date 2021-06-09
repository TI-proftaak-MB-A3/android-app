package nl.avans.ti.questions;

import java.util.ArrayList;
import java.util.Collections;

public class Question
{
    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;
    private String catogorie;
    private int id;


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

    public int getId()
    {
        return id;
    }
}
