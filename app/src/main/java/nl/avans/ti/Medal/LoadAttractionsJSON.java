package nl.avans.ti.Medal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import nl.avans.ti.Json.JsonFromData;

public class LoadAttractionsJSON
{
    private String json;
    private static LoadAttractionsJSON loadAttractionsJSON;

    private static final String TAG = LoadAttractionsJSON.class.getName();
    private AppCompatActivity appCompatActivity;

    private static final String jsonSaveKey = "JsonAttractions";


    private LoadAttractionsJSON(AppCompatActivity appCompatActivity)
    {
        this.appCompatActivity = appCompatActivity;

        save(getAttractions());
    }

    /**
     * Method which checks if there is already an loaded JSOn file, else
     * it will create a new file from teh attractions template
     * @param appCompatActivity
     * @return
     */
    public static LoadAttractionsJSON getInstance(AppCompatActivity appCompatActivity)
    {

        if (loadAttractionsJSON == null)
        {
            loadAttractionsJSON = new LoadAttractionsJSON(appCompatActivity);
        }
        return loadAttractionsJSON;
    }


    public LinkedList<Attraction> getAttractions()
    {
        load();
        return loadFromJson();
    }

    /**
     * Method which loads a previously saved JSON file
     */
    private void load()
    {
        SharedPreferences sharedPref = appCompatActivity.getPreferences(Context.MODE_PRIVATE);

        String defaultValue = JsonFromData.JsonDataFromAsset(appCompatActivity, "attractions.json");
        json = sharedPref.getString(jsonSaveKey, defaultValue);

        //use this for a reset
        //json = JsonFromData.JsonDataFromAsset(appCompatActivity, "attractions.json");
    }


    /**
     * Method which gets all the data for all the attractions
     * so the Recyclerview and medals can be loaded correctly
     * @return
     */
    private LinkedList<Attraction> loadFromJson()
    {
        LinkedList<Attraction> attractions = new LinkedList<>();

        try
        {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("attractions");

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject userData = jsonArray.getJSONObject(i);
                String name = userData.getString("name");
                String attractionImageName = userData.getString("imageName");
                String iconNameFalse = userData.getString("iconFalse");
                String iconNameTrue = userData.getString("iconTrue");
                boolean hasMedal = userData.getBoolean("hasMedal");
                boolean hasFirstCheck = userData.getBoolean("hasFirstCheckpoint");
                boolean hasSecondCheck = userData.getBoolean("hasSecondCheckpoint");
                boolean hasThirdCheck = userData.getBoolean("hasThirdCheckpoint");

                attractions.add(new Attraction(attractionImageName, name, hasMedal, hasFirstCheck, hasSecondCheck, hasThirdCheck, iconNameFalse, iconNameTrue));
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return attractions;
    }

    /**
     * method which saves the JSON to a SharedPreference so it will be
     * loaded when the app is opened again
     * @param attractions
     */
    public void save(LinkedList<Attraction> attractions)
    {
        SaveDataToAsset(attractions);

        SharedPreferences sharedPref = appCompatActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(jsonSaveKey, json);
        editor.apply();
    }

    /**
     * Method which saves the current state of each
     * attraction to a nes JSON object
     * @param attractionLinkedList
     */
    private void SaveDataToAsset(LinkedList<Attraction> attractionLinkedList)
    {
        try
        {
            Log.d(TAG, "SaveDataToAsset: old json: \n" + json);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("attractions");

            for (int i = 0; i < jsonArray.length(); i++)
            {

                JSONObject userData = jsonArray.getJSONObject(i);
                Log.d(TAG, "SaveDataToAsset: user data is");
                System.out.println("sgderfhjkl");
                Attraction attraction = attractionLinkedList.get(i);
                System.out.println(attractionLinkedList.size());
                userData.put("hasMedal", attraction.getHasMedal());
                userData.put("hasFirstCheckpoint", attraction.getHasCheckpointOne());
                userData.put("hasSecondCheckpoint", attraction.getHasCheckpointTwo());
                userData.put("hasThirdCheckpoint", attraction.getHasCheckpointThree());
            }

            json = jsonObject.toString();
            Log.d(TAG, "SaveDataToAsset: new json: \n" + json);


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
