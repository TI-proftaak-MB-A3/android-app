package nl.avans.ti.Medal;

import android.content.Context;

import java.lang.reflect.Field;

import nl.avans.ti.R;

public class Attraction {
    private String imageName;
    private String name;
    private int checkOneResourceID;
    private int checkTwoResourceID;
    private int checkThreeResourceID;
    private int medalResourceId;


    private Boolean hasMedal;

    private Boolean hasCheckpointOne;

    private Boolean hasCheckpointTwo;

    private Boolean hasCheckpointThree;

    public Attraction(String imageName, String name, Boolean hasMedal, Boolean hasFirstCheck, Boolean hasSecondCheck, Boolean hasThirdCheck) {
        this.name = name;
        this.imageName = imageName;

        this.hasMedal = hasMedal;
        this.hasCheckpointOne = hasFirstCheck;
        this.hasCheckpointTwo = hasSecondCheck;
        this.hasCheckpointThree = hasThirdCheck;


        checkImage();
    }

    private void checkImage() {
        if (hasMedal) {
            medalResourceId = R.drawable.medal_completed;
        } else {
            medalResourceId = R.drawable.medal_progres;
        }

        if (hasCheckpointOne) {
            checkOneResourceID = R.drawable.check_complete;
        } else {
            checkOneResourceID = R.drawable.check_progres;
        }

        if (hasCheckpointTwo) {
            checkTwoResourceID = R.drawable.check_complete;
        } else {
            checkTwoResourceID = R.drawable.check_progres;
        }

        if (hasCheckpointThree) {
            checkThreeResourceID = R.drawable.check_complete;
        } else {
            checkThreeResourceID = R.drawable.check_progres;
        }
    }

    public String getName() {
        return name;
    }

    public int getCheckOneResourceID() {
        return checkOneResourceID;
    }

    public int getCheckTwoResourceID() {
        return this.checkTwoResourceID;
    }

    public int getCheckThreeResourceID() {
        return this.checkThreeResourceID;
    }

    public int getMedalResourceId() {
        return medalResourceId;
    }

    public int getAttractionResourceID() {
        this.imageName = this.imageName.replace(".jpg", "");
        this.imageName = this.imageName.trim();
        int imageID = 0;

        try {
            Class<R.drawable> drawableClass = R.drawable.class;
            Field image = drawableClass.getDeclaredField(this.imageName);
            imageID = image.getInt(drawableClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageID;
    }

    public Boolean getHasMedal() {
        return hasMedal;
    }

    public Boolean getHasCheckpointOne() {
        return hasCheckpointOne;
    }

    public Boolean getHasCheckpointTwo() {
        return hasCheckpointTwo;
    }

    public Boolean getHasCheckpointThree() {
        return hasCheckpointThree;
    }

}
