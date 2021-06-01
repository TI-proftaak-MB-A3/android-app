package nl.avans.ti.Medal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

import nl.avans.ti.MenuHandler;
import nl.avans.ti.R;

public class MedalActivity extends AppCompatActivity {
    private RecyclerView medalView;
    private MedalListAdapter medalListAdapter;
    private LinkedList<Attraction> attractions = new LinkedList<>();
    private MenuHandler menuHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("attractions");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userData = jsonArray.getJSONObject(i);
                String name = userData.getString("name");
                int attractionID = Integer.parseInt(userData.getString("attractionResourceId"));
                int checkID = Integer.parseInt(userData.getString("checkResourceId"));
                int medalID = Integer.parseInt(userData.getString("medalResourceId"));

                this.attractions.add(new Attraction(attractionID, name, checkID, medalID));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Temporary until JSOn was added
//        for (int i = 0; i < 5; i++) {
//            this.attractions.add(new Attraction(R.drawable.efteling_vl_hollander, getString(R.string.vliegendeAttractie) + i, R.drawable.check_failed, R.drawable.medal_progres));
//        }

        this.medalView = findViewById(R.id.medalView);
        this.medalListAdapter = new MedalListAdapter(this, this.attractions);
        this.medalView.setAdapter(this.medalListAdapter);
        this.medalView.setLayoutManager(new LinearLayoutManager(this));

        this.medalView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        menuHandler = new MenuHandler(this);
        menuHandler.start();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }

    private String JsonDataFromAsset() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("attractions.json");
            int sizeOffFile = inputStream.available();
            byte[] bufferData = new byte[sizeOffFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}