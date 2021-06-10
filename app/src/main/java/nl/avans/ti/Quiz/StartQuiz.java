package nl.avans.ti.Quiz;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    public StartQuiz(Connect connect, List<Question> questions, MainActivity app) {
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

    public void addConnection()
    {
        if (!alreadyConnected)
        {
            connect.setAdress(connect.getAdress() + code);
        }
        System.out.println(connect.getAdress());
        connect.subscribeToTopic();
        connect.publishMessage("connect");

        Timer timer = new Timer();



        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < messages.size();i++){
                    if (messages.get(i).equals("accepted")){
                        app.gotoWaitingscreen();
                        System.out.println("werkt dit?");
                        setAlreadyConnected(true);
                        break;
                    }
                }
                if (!alreadyConnected){
                    removeConnection();
                }
            }
        };
        timer.schedule(task, 8000);


        System.out.println("doei");

    }

    public void removeConnection()
    {
        System.out.println("why");
        connect.unsubscribeToTopic();
        setAlreadyConnected(false);
        setCode("");
    }





    public Question getQuestion() {
        CodeDecryption decryption = new CodeDecryption(this.code);
        ArrayList<Question> questionsForAttraction = new ArrayList<>();

        for (Question q : this.questions) {
            if (q.getId() == Integer.parseInt(decryption.getAttraction())) {
                questionsForAttraction.add(q);

            }
        }


        int position = Integer.parseInt(decryption.getQuestion()) % questionsForAttraction.size();


        Question question = questionsForAttraction.get(position);

        question.setShuffle(decryption.getCombination());
        return question;
    }


    public void receiveMessage(MqttMessage message){
    //todo decide what message does what (after the quiz layout is made)
        Log.d("StartQuiz", "receiveMessage: " + message.toString());

        this.messages.add(message.toString());

        if (message.toString().equals("start")){
            app.startQuizWithIntent();
        }

//        switch (message.toString()){
//            case("A") :
//                break;
//            case("B") :
//                break;
//            case("C") :
//                break;
//            case("D") :
//                break;
//
//            case("Start") :
//                app.startQuizWithIntent();
//                break;
//
//
//
//        }

    }


}
