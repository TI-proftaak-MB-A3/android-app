package nl.avans.ti.Medal;

import android.widget.ImageView;

public class Attraction {
    private int attractionResourceID;
    private String name;
    private int checkTrueResourceID;
    private int medalTrueResourceID;

    private Boolean hasMedal = false;
    private Boolean failedMedal = false;

    private Boolean hasCheckpointOne = false;
    private Boolean failedCheckpointOne = false;

    private Boolean hasCheckpointTwo = false;
    private Boolean failedCheckpointTwo = false;

    private Boolean hasCheckpointThree = false;
    private Boolean failedCheckpointThree = false;

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
        System.out.println(medalTrueResourceID);
        return medalTrueResourceID;
    }

    public int getAttractionResourceID() {
        return attractionResourceID;
    }

}
