package nl.avans.ti.Medal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

import nl.avans.ti.R;

public class medalAvtivity extends AppCompatActivity {
    private RecyclerView medalView;
    private MedalListAdapter medalListAdapter;
    private LinkedList<Attraction> attractions = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);

        for (int i = 0; i < 5; i++) {
            this.attractions.add(new Attraction(R.drawable.efteling_vl_hollander, "Vliegende hollander " + i, R.drawable.download, R.drawable.download1));
        }

        this.medalView = findViewById(R.id.medalView);
        this.medalListAdapter = new MedalListAdapter(this, this.attractions);
        this.medalView.setAdapter(this.medalListAdapter);
        this.medalView.setLayoutManager(new LinearLayoutManager(this));

        this.medalView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}