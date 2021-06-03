package nl.avans.ti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity
{


    private MenuHandler menuHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);




        menuHandler = new MenuHandler(this);
        menuHandler.start();


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return menuHandler.onOptionsItemSelected(item);
    }
}