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

    private MenuHandler menuHandler;
    private LoadAttractionsJSON load;
    private LinkedList<Attraction> attractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);





        load = LoadAttractionsJSON.getInstance(this);
        this.attractions = load.getAttractions();

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
    protected void onDestroy() {
        super.onDestroy();

        load.save();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}