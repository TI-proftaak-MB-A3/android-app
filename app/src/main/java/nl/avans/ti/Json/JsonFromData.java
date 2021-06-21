package nl.avans.ti.Json;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class JsonFromData
{


   public static String JsonDataFromAsset(AppCompatActivity appCompatActivity, String file)
    {
        String json = "";
        try
        {
            InputStream inputStream = appCompatActivity.getAssets().open(file);
            int sizeOffFile = inputStream.available();
            byte[] bufferData = new byte[sizeOffFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return json;
    }

}
