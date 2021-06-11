package nl.avans.ti.Medal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.storage.StorageManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import nl.avans.ti.Json.JsonFromData;
import nl.avans.ti.MainActivity;

public class LoadAttractionsJSON {
    private LinkedList<Attraction> attractions;
    private String json;
    private static LoadAttractionsJSON loadAttractionsJSON;

    private LoadAttractionsJSON(AppCompatActivity appCompatActivity) {
        this.attractions = new LinkedList<>();
        json = JsonFromData.JsonDataFromAsset(appCompatActivity, "attractions.json");
    }

    public static LoadAttractionsJSON getInstance(AppCompatActivity appCompatActivity) {
        if (loadAttractionsJSON == null)
        {
            return new LoadAttractionsJSON(appCompatActivity);
        }
        return loadAttractionsJSON;
    }

    public void setAttractions(LinkedList<Attraction> attractions) {
        this.attractions = attractions;
    }

    public LinkedList<Attraction> getAttractions() {
        load();
        return attractions;
    }

    private void load()
    {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("attractions");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                String name = userData.getString("name");
                String attractionImageName = userData.getString("imageName");
                boolean hasMedal = userData.getBoolean("hasMedal");
                boolean hasFirstCheck = userData.getBoolean("hasFirstCheckpoint");
                boolean hasSecondCheck = userData.getBoolean("hasSecondCheckpoint");
                boolean hasThirdCheck = userData.getBoolean("hasThirdCheckpoint");

                this.attractions.add(new Attraction( attractionImageName, name, hasMedal, hasFirstCheck, hasSecondCheck, hasThirdCheck));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void save(LinkedList<Attraction> attractions) {
 //       load();
        SaveDataToAsset(attractions);


        for (Attraction attraction : getAttractions())
        {
            System.out.println("please make it stop");
            Log.d("LoadAttractionsJSON", " " + attraction.toString());
        }




    }

    private void SaveDataToAsset(LinkedList<Attraction> attractionLinkedList) {

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("attractions");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                System.out.println(userData);
                System.out.println("sgderfhjkl");
                Attraction attraction = attractionLinkedList.get(i);
                System.out.println(attractionLinkedList.size());
                userData.put("hasMedal", attraction.getHasMedal());
                userData.put("hasFirstCheckpoint", attraction.getHasCheckpointOne());
                userData.put("hasSecondCheckpoint", attraction.getHasCheckpointTwo());
                userData.put("hasThirdCheckpoint", attraction.getHasCheckpointThree());
//                writeFileOnInternalStorage(, "attractions.json", json);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
//        File dir = new File(mcoContext.getFilesDir(), "mydir");
//        if(!dir.exists()){
//            dir.mkdir();
//        }
//
//        try {
//            File gpxfile = new File(dir, sFileName);
//            FileWriter writer = new FileWriter(gpxfile);
//            writer.append(sBody);
//            writer.flush();
//            writer.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}
