package nl.avans.ti.Medal;

import android.widget.ImageView;

public class Attraction {
    private int attractionResourceID;
    private String name;
    private int checkTrueResourceID;
    private int medalTrueResourceID;

    public Attraction(int attractionResourceID, String name, int checkTrueResourceID, int medalTrueResourceID) {
        this.attractionResourceID = attractionResourceID;
        this.name = name;
        this.checkTrueResourceID = checkTrueResourceID;
        this.medalTrueResourceID = medalTrueResourceID;
    }

    public String getName() {
        return name;
    }

    public int getCheckTrueResourceID() {
        return checkTrueResourceID;
    }

    public int getMedalTrueResourceID() {
        return medalTrueResourceID;
    }

    public int getAttractionResourceID() {
        return attractionResourceID;
    }
}
