package nl.avans.ti;


import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Objects;

import nl.avans.ti.Medal.MedalActivity;

public class MenuHandler
{

    AppCompatActivity appCompatActivity;
    HashMap<String, Class<? extends AppCompatActivity>> classHashMap;

    public MenuHandler(AppCompatActivity appCompatActivity)
    {

        this.appCompatActivity = appCompatActivity;
    }

    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    public void start()
    {
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        DrawerLayout drawerLayout = appCompatActivity.findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(appCompatActivity, drawerLayout, R.string.nav_open, R.string.nav_close);
        navigationView = appCompatActivity.findViewById(R.id.hamburger);
        classHashMap = new HashMap<>();

        //todo keep this up to date with future
        classHashMap.put(appCompatActivity.getString(R.string.play_game), MainActivity.class);
        classHashMap.put(appCompatActivity.getString(R.string.medals), MedalActivity.class);


        navigationView.setNavigationItemSelectedListener(item ->
        {
            CharSequence title = item.getTitle();
            String titleAsString = title.toString();

            if (classHashMap.containsKey(titleAsString))
            {
                Class<? extends AppCompatActivity> aClass = classHashMap.get(titleAsString);

                if (appCompatActivity.getClass() == aClass)
                {
                    Log.d("Navigation Bar", "start: allready in this menu");
                }
                else
                {
                    Intent openItemIntent = new Intent(appCompatActivity, aClass);
                    appCompatActivity.startActivity(openItemIntent);
                }
            }else
            {
                Log.d("Navigation Bar", "start: no action linked to this menuItem");
            }


            return false;
        });

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(appCompatActivity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return appCompatActivity.onOptionsItemSelected(item);
    }


}
