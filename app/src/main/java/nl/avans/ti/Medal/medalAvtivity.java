package nl.avans.ti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class medalAvtivity extends AppCompatActivity {
    private RecyclerView medalView;
    private MedalListAdapter medalListAdapter;
    private LinkedList<Attraction> attractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);

        this.medalView = findViewById(R.id.medalView);
    }
}