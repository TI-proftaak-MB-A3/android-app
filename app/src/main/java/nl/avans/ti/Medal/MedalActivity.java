package nl.avans.ti.Medal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import nl.avans.ti.Allmedals;
import nl.avans.ti.MainActivity;
import nl.avans.ti.MenuHandler;
import nl.avans.ti.R;

public class MedalActivity extends AppCompatActivity
{
    private RecyclerView medalView;
    private MedalListAdapter medalListAdapter;
    private LinkedList<Attraction> attractions = new LinkedList<>();
    private MenuHandler menuHandler;

    private LoadAttractionsJSON load;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);

        load = LoadAttractionsJSON.getInstance(this);
        this.attractions = load.getAttractions();

        this.medalView = findViewById(R.id.medalView);
        this.medalListAdapter = new MedalListAdapter(this, this.attractions);
        this.medalView.setAdapter(this.medalListAdapter);
        this.medalView.setLayoutManager(new LinearLayoutManager(this));

        this.medalView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        menuHandler = new MenuHandler(this);
        menuHandler.start();


        if (hasAllMedals())
        {
            //todo start all medals activity
            Intent intent = new Intent(this, Allmedals.class);
            startActivity(intent);
        }


    }


    public boolean hasAllMedals()
    {
        for (Attraction attraction : attractions)
        {
            if (!attraction.getHasMedal())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }


}