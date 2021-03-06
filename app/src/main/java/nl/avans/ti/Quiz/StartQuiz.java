package nl.avans.ti.Quiz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nl.avans.ti.FeedbackFailed;
import nl.avans.ti.FeedbackPassed;
import nl.avans.ti.MQTT.CodeDecryption;
import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.MainActivity;
import nl.avans.ti.Questions.Question;

public class StartQuiz
{
    private MainActivity app;
    private Connect connect;
    private String code;
    private List<Question> questions;
    private boolean alreadyConnected;
    private ArrayList<String> messages;



    private boolean quizShown;

    private boolean tryingToConnect;

    public static interface AnswerChecker
    {
        boolean checkAnswer(String string);
        Question getQuestion();
    }

    private AnswerChecker answerChecker;

    public void setAnswerChecker(AnswerChecker answerChecker)
    {
        this.answerChecker = answerChecker;
    }

    public StartQuiz(Connect connect, List<Question> questions, MainActivity app)
    {
        this.app = app;
        this.alreadyConnected = false;
        this.connect = connect;
        this.code = code;
        this.questions = questions;
        this.messages = new ArrayList<>();
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public void setAlreadyConnected(boolean alreadyConnected)
    {
        this.alreadyConnected = alreadyConnected;
    }

    public boolean isAlreadyConnected()
    {
        return alreadyConnected;
    }


    /**
     * Tries to add the connection with the topic of the code, if the arduino doen't answer in 8 seconds it automaticaly disconnects
     */
    public void addConnection()
    {
        tryingToConnect = true;
        if (!alreadyConnected)
        {
            connect.setAdress(connect.getDefaultAdress() + code);
        }
        System.out.println(connect.getAdress());
        connect.subscribeToTopic();
        connect.publishMessage("connect");

        Timer timer = new Timer();


        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if (!alreadyConnected)
                {
                    removeConnection();
                }
            }
        };
        timer.schedule(task, 8000);

    }

    public boolean isTryingToConnect()
    {
        return tryingToConnect;
    }

    /**
     * disconnects from the subscribed topic and sets the default address for the next connection
     */
    public void removeConnection()
    {
        connect.unsubscribeToTopic();
        setAlreadyConnected(false);
        setCode("");
        tryingToConnect = false;
        quizShown = false;
        answerChecker = null;
    }

    /**
     * gets the question from the code on the arduino
     * @return  Question question
     */
    public Question getQuestion()
    {
        CodeDecryption decryption = new CodeDecryption(this.code);
        ArrayList<Question> questionsForAttraction = new ArrayList<>();

        for (Question q : this.questions)
        {
            if (q.getId() == Integer.parseInt(decryption.getAttraction()))
            {
                questionsForAttraction.add(q);

            }
        }


        int position = Integer.parseInt(decryption.getQuestion()) % questionsForAttraction.size();


        Question question = questionsForAttraction.get(position);

        question.setShuffle(decryption.getCombination());
        return question;
    }

    /**
     * reads the given message from the MQTT server and starts the corresponding method
     * @param message
     */
    public void receiveMessage(MqttMessage message)
    {

        String recievedMessage = message.toString().trim();
        Log.d("StartQuiz", "receiveMessage: " + recievedMessage);


        if (recievedMessage.equals("accepted"))
        {
            app.gotoWaitingscreen();
            setAlreadyConnected(true);
        }

        if (alreadyConnected)
        {
            if (recievedMessage.equals("start"))
            {
                app.startQuizWithIntent();
                quizShown = true;
            }
        }


        if (quizShown)
        {
//            if (recievedMessage.equals("time_out"))
//            {
//                //methods to go back to main and close connection aka backToStart
//                Log.d("StartQuiz", "Timed out");
//
//                Intent intent;
//                Context baseContext = app.getBaseContext();
//
//                    intent = new Intent(baseContext, FeedbackFailed.class);
//
//                intent.putExtra("Question",answerChecker.getQuestion());
//                app.startActivity(intent);
//            }
//            else
//            {

                if ("ABCDE".contains(recievedMessage.toUpperCase()))
                {
                    boolean isCorrectAnswer = answerChecker.checkAnswer(recievedMessage);
                    Question question = answerChecker.getQuestion();
                    System.out.println(question);

                    if (isCorrectAnswer)
                    {
                        connect.publishMessage("correct");
                    }
                    else
                    {
                        connect.publishMessage("incorrect");
                    }

                    removeConnection();
                    showAnswerScreen(isCorrectAnswer,question);

                }
            //}

        }
    }


    //Goes to the multiple choice screen when the quiz is started
    public void showAnswerScreen(boolean answeredCorrect, Question question)
    {
        Intent intent;
        Context baseContext = app.getBaseContext();
        if (answeredCorrect)
        {
            intent = new Intent(baseContext, FeedbackPassed.class);
        }
        else
        {
            intent = new Intent(baseContext, FeedbackFailed.class);
        }

        intent.putExtra("Question",question);
        app.startActivity(intent);
    }


    //implement method to go back to start
    public void backToStart()
    {
        Intent intent = new Intent(app.getBaseContext(), MainActivity.class);
        app.startActivity(intent);
    }


}
