package nl.avans.ti.Quiz;


import android.util.JsonReader;

import java.util.ArrayList;
import java.util.Collections;

public class Questions {
    private String Question;
    private String AnswerOne;
    private String AnswerTwo;
    private String AnswerThree;
    private String AnswerFour;
    private String Correct;

    public Questions(String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correct) {
        Question = question;
        AnswerOne = answerOne;
        AnswerTwo = answerTwo;
        AnswerThree = answerThree;
        AnswerFour = answerFour;
        Correct = correct;

    }


    public ArrayList<String> randomQuestions(){
        ArrayList<String> output = new ArrayList<>();

        output.add(AnswerOne);
        output.add(AnswerTwo);
        output.add(AnswerThree);
        output.add(AnswerFour);

        Collections.shuffle(output);

        return output;
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnswerOne() {
        return AnswerOne;
    }

    public String getAnswerTwo() {
        return AnswerTwo;
    }

    public String getAnswerThree() {
        return AnswerThree;
    }

    public String getAnswerFour() {
        return AnswerFour;
    }

    public String getCorrect() {
        return Correct;
    }
}
