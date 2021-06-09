package nl.avans.ti.Quiz;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

import nl.avans.ti.MQTT.CodeDecryption;
import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.MainActivity;
import nl.avans.ti.Questions.Question;

public class StartQuiz {
    private MainActivity app;
    private Connect connect;
    private String code;
    private List<Question> questions;
    private boolean alreadyConnected;

    public StartQuiz(Connect connect, List<Question> questions) {
        this.alreadyConnected = false;
        this.connect = connect;
        this.code = code;
        this.questions = questions;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAlreadyConnected(boolean alreadyConnected) {
        this.alreadyConnected = alreadyConnected;
    }

    public boolean isAlreadyConnected() {
        return alreadyConnected;
    }

    public void addConnection(){
        if (!alreadyConnected){
        connect.setAdress(connect.getAdress() + code);
        }
        System.out.println(connect.getAdress());
        connect.subscribeToTopic();
        connect.publishMessage("test");
        setAlreadyConnected(true);
    }

    public void removeConnection() {
        connect.unsubscribeToTopic();
    }

    public Question getQuestion() {
        CodeDecryption decryption = new CodeDecryption(Integer.parseInt(this.code));
        ArrayList<Question> questionsForAttraction = new ArrayList<>();

        for (Question q : this.questions) {
            if (q.getId() == Integer.parseInt(decryption.getAttraction())) {
                questionsForAttraction.add(q);
            }
        }

        int position = Integer.parseInt(decryption.getQuestion());

        return questionsForAttraction.get(position);
    }

    public void receiveMessage(MqttMessage message){
    //todo decide what message does what (after the quiz layout is made)

    }



}
