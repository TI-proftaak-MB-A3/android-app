package nl.avans.ti.Medal;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import nl.avans.ti.Json.JsonFromData;

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

    public void save() {
        SaveDataToAsset();
    }

    private void SaveDataToAsset() {
        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("attractions");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                userData.put("hasMedal", this.attractions.get(i).getHasMedal());
                userData.put("hasFirstCheckpoint", this.attractions.get(i).getHasCheckpointOne());
                userData.put("hasSecondCheckpoint", this.attractions.get(i).getHasCheckpointTwo());
                userData.put("hasThirdCheckpoint", this.attractions.get(i).getHasCheckpointThree());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
