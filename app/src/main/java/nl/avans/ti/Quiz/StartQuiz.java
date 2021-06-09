package nl.avans.ti.Quiz;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import nl.avans.ti.MQTT.Connect;
import nl.avans.ti.MainActivity;

public class StartQuiz
{
    private MainActivity app;
    private Connect connect;
    private String code;
    private boolean alreadyConnected;

    public StartQuiz(Connect connect)
    {
        this.alreadyConnected = false;
        this.connect = connect;
        this.code = code;
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
        connect.publishMessage("test");
        setAlreadyConnected(true);
    }

    public void removeConnection()
    {
        connect.unsubscribeToTopic();
    }

    public void receiveMessage(MqttMessage message)
    {
        //todo decide what message does what (after the quiz layout is made)
        Log.d("StartQuiz", "receiveMessage: " + message.toString());

    }


}
