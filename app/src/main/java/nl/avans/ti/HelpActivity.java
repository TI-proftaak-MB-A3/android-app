package nl.avans.ti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import nl.avans.ti.Fragments.FragmentFive;
import nl.avans.ti.Fragments.FragmentFour;
import nl.avans.ti.Fragments.FragmentOne;
import nl.avans.ti.Fragments.FragmentThree;
import nl.avans.ti.Fragments.FragmentTwo;

public class HelpActivity extends AppCompatActivity
{

    private ViewPager pager;
    private PagerAdapter adapter;

    private MenuHandler menuHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        List<Fragment> list = new ArrayList<>();
        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        list.add(new FragmentThree());
        list.add(new FragmentFour());
        list.add(new FragmentFive());

        //Pager which uses the elp pages so the user can scroll through them
        this.pager = findViewById(R.id.pager);
        adapter = new Slider(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);


        menuHandler = new MenuHandler(this);
        menuHandler.start();


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}