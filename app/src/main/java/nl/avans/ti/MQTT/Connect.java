package nl.avans.ti.MQTT;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import nl.avans.ti.MainActivity;
import nl.avans.ti.Questions.Question;
import nl.avans.ti.Quiz.StartQuiz;

public class Connect
{
    private MainActivity app;
    private StartQuiz startQuiz;
    private static final String LOGTAG = MainActivity.class.getName();

    //broker settings
    private static final String BROKER_HOST_URL = "tcp://sendlab.nl:11884";
    private static final String USERNAME = "ti";
    private static final String PASSWORD = "tiavans";

    //client ID for broker
    private static final String CLIENT_ID = "A3_APP_" + UUID.randomUUID().toString();
    private static final int QUALITY_OF_SERVICE = 0;

    public String getAdress()
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    //MQTT subscription topics
    private String adress = "ti/1.4/a3/";

    private String defaultAdress = "ti/1.4/a3/";

    public String getDefaultAdress()
    {
        return defaultAdress;
    }

    private MqttAndroidClient mqttAndroidClient;


    public StartQuiz getStartQuiz()
    {
        return startQuiz;
    }

    private Connect(MainActivity app, List<Question> questionList)
    {
        this.app = app;
        startQuiz = new StartQuiz(this, questionList, this.app);

        this.mqttAndroidClient = new MqttAndroidClient(app.getApplicationContext(), BROKER_HOST_URL, CLIENT_ID);

        this.mqttAndroidClient.setCallback(new MqttCallback()
        {
            @Override
            public void connectionLost(Throwable cause)
            {
                Log.d(LOGTAG, "MQTT client lost connection to broker, cause: " + cause.getLocalizedMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception
            {
                startQuiz.receiveMessage(message);
                Log.d(LOGTAG, "MQTT client delivery complete");

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token)
            {

            }
        });

        connectToBroker(this.mqttAndroidClient, CLIENT_ID);

    }


    private static Connect connect;
    public static Connect getConnect(MainActivity mainActivity, List<Question> questionList)
    {
        if (connect == null)
        {
            connect = new Connect(mainActivity, questionList);
        }

        return connect;
    }

    public static Connect getConnect()
    {
        return connect;
    }



    public void connectToBroker(MqttAndroidClient client, String client_id)
    {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try
        {
            // Try to connect to the MQTT broker
            IMqttToken token = client.connect(options);
            // Set up callbacks for the result
            token.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken)
                {
                    Log.d(LOGTAG, "MQTT client is now connected to MQTT broker");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
                {
                    Log.e(LOGTAG, "MQTT client failed to connect to MQTT broker: " + exception.getLocalizedMessage());
                    Toast toast = Toast.makeText(app.getApplicationContext(), "Failed to connect to MQTT broker", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }
        catch (MqttException e)
        {
            Log.e(LOGTAG, "MQTT exception while connecting to MQTT broker, reason: " + e.getReasonCode() + ", msg: " + e.getMessage() + ", cause: " + e.getCause());
            e.printStackTrace();
        }

    }

    public void disconnectFromBroker(MqttAndroidClient client)
    {
        try
        {
            // Try to disconnect from the MQTT broker
            IMqttToken token = client.disconnect();
            // Set up callbacks to handle the result
            token.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken)
                {
                    Log.d(LOGTAG, "MQTT client is now disconnected from MQTT broker");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
                {
                    Log.e(LOGTAG, "MQTT failed to disconnect from MQTT broker: " + exception.getLocalizedMessage());
                }
            });
        }
        catch (MqttException e)
        {
            Log.e(LOGTAG, "MQTT exception while disconnecting from MQTT broker, reason: " + e.getReasonCode() + ", msg: " + e.getMessage() + ", cause: " + e.getCause());
            e.printStackTrace();
        }
    }

    public void publishMessage(String msg)
    {
        byte[] encodedPayload = new byte[0];
        try
        {
            // Convert the message to a UTF-8 encoded byte array
            encodedPayload = msg.getBytes("UTF-8");

            // Store it in an MqttMessage
            MqttMessage message = new MqttMessage(encodedPayload);

            // Set parameters for the message
            message.setQos(QUALITY_OF_SERVICE);
            message.setRetained(false);
            // Publish the message via the MQTT broker
            this.mqttAndroidClient.publish(adress, message);
            System.out.println("printed");
        }
        catch (UnsupportedEncodingException | MqttException e)
        {
            Log.e(LOGTAG, "MQTT exception while publishing topic to MQTT broker, msg: " + e.getMessage() + ", cause: " + e.getCause());
            e.printStackTrace();
            System.out.println("didn't work");
        }
    }

    public void subscribeToTopic()
    {
        try
        {
            // Try to subscribe to the topic
            IMqttToken token = this.mqttAndroidClient.subscribe(this.adress, QUALITY_OF_SERVICE);
            // Set up callbacks to handle the result
            token.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken)
                {
                    Log.d(LOGTAG, "MQTT client is now subscribed to topic " + adress);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
                {
                    Log.e(LOGTAG, "MQTT failed to subscribe to topic " + adress + " because: " + exception.getLocalizedMessage());
                }
            });
        }
        catch (MqttException e)
        {
            Log.e(LOGTAG, "MQTT exception while subscribing to topic on MQTT broker, reason: " + e.getReasonCode() + ", msg: " + e.getMessage() + ", cause: " + e.getCause());
            e.printStackTrace();
        }
    }

    public void unsubscribeToTopic()
    {
        try
        {
            // Try to unsubscribe to the topic
            IMqttToken token = this.mqttAndroidClient.unsubscribe(adress);
            // Set up callbacks to handle the result
            token.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken)
                {
                    Log.d(LOGTAG, "MQTT client is now unsubscribed to topic " + adress);
                    adress = defaultAdress;
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
                {
                    Log.e(LOGTAG, "MQTT client failed to unsubscribe to topic " + adress + " because: " + exception.getLocalizedMessage());
                }
            });
        }
        catch (MqttException e)
        {
            Log.e(LOGTAG, "MQTT exception while unsubscribing from topic on MQTT broker, reason: " + e.getReasonCode() + ", msg: " + e.getMessage() + ", cause: " + e.getCause());
            e.printStackTrace();
        }
    }
}

