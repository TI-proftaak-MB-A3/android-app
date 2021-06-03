package nl.avans.ti.Quiz;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.MainActivity;

public class StartQuiz {
    private MainActivity app;
    private Connect connect;
    private String code;
    private boolean alreadyConnected;

    public StartQuiz(Connect connect,String code) {
        this.alreadyConnected = false;
        this.connect = connect;
        this.code = code;
    }

    public void addConnection(){
        if (!alreadyConnected){
        connect.setAdress(connect.getAdress() + code);
        }
        System.out.println(connect.getAdress());
        connect.subscribeToTopic();
        connect.publishMessage("test");
        this.alreadyConnected = true;
    }

    public void removeConnection() {
        connect.unsubscribeToTopic();
    }

    public void receiveMessage(MqttMessage message){
    //todo decide what message does what (after the quiz layout is made)

    }



}
