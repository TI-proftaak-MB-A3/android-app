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

    private void load()
    {
        SharedPreferences sharedPref = appCompatActivity.getPreferences(Context.MODE_PRIVATE);


        String defaultValue = JsonFromData.JsonDataFromAsset(appCompatActivity, "attractions.json");
        if (sharedPref.contains(jsonSaveKey))
        {
            json = sharedPref.getString(jsonSaveKey, "bingo");
        }
        else
        {
            json = sharedPref.getString(jsonSaveKey, defaultValue);
        }
    }


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
                boolean hasMedal = userData.getBoolean("hasMedal");
                boolean hasFirstCheck = userData.getBoolean("hasFirstCheckpoint");
                boolean hasSecondCheck = userData.getBoolean("hasSecondCheckpoint");
                boolean hasThirdCheck = userData.getBoolean("hasThirdCheckpoint");

                attractions.add(new Attraction(attractionImageName, name, hasMedal, hasFirstCheck, hasSecondCheck, hasThirdCheck));
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return attractions;
    }

    public void save(LinkedList<Attraction> attractions)
    {
        SaveDataToAsset(attractions);

        SharedPreferences sharedPref = appCompatActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(jsonSaveKey, json);
        editor.apply();
    }

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
                //                writeFileOnInternalStorage(, "attractions.json", json);
            }

            json = jsonObject.toString();
            Log.d(TAG, "SaveDataToAsset: new json: \n" + json);


        }
        catch (JSONException e)
        {
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
