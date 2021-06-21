package nl.avans.ti.Medal;

import android.content.Context;

import java.lang.reflect.Field;

import nl.avans.ti.R;

public class Attraction {
    private String attractionImageName;
    private String attractionName;
    private String iconImageName;
    private int checkOneResourceID;
    private int checkTwoResourceID;
    private int checkThreeResourceID;
    private int medalResourceId;


    private Boolean hasMedal;

    private Boolean hasCheckpointOne;

    private Boolean hasCheckpointTwo;

    private Boolean hasCheckpointThree;

    public Attraction(String attractionImageName, String attractionName, Boolean hasMedal, Boolean hasFirstCheck, Boolean hasSecondCheck, Boolean hasThirdCheck, String iconNameFalse, String iconNameTrue) {
        this.attractionName = attractionName;
        this.attractionImageName = attractionImageName;

        this.hasMedal = hasMedal;
        this.hasCheckpointOne = hasFirstCheck;
        this.hasCheckpointTwo = hasSecondCheck;
        this.hasCheckpointThree = hasThirdCheck;

        if (hasMedal) {
            this.iconImageName = iconNameTrue;
        } else {
            this.iconImageName = iconNameFalse;
        }

        checkImage();
    }

    private void checkImage() {
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

    public String getAttractionName() {
        return attractionName;
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

    public int getAttractionImageID() {
        this.attractionImageName = this.attractionImageName.replace(".jpg", "");
        this.attractionImageName = this.attractionImageName.trim();
        int imageID = 0;

        try {
            Class<R.drawable> drawableClass = R.drawable.class;
            Field image = drawableClass.getDeclaredField(this.attractionImageName);
            imageID = image.getInt(drawableClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageID;
    }

    public int getIconImageID() {
        this.iconImageName = this.iconImageName.replace(".jpg", "");
        this.iconImageName = this.iconImageName.trim();
        int imageID = 0;

        try {
            Class<R.drawable> drawableClass = R.drawable.class;
            Field image = drawableClass.getDeclaredField(this.iconImageName);
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
    public void setHasMedal(Boolean hasMedal) {
        this.hasMedal = hasMedal;
    }

    public void setHasCheckpointOne(Boolean hasCheckpointOne) {
        this.hasCheckpointOne = hasCheckpointOne;
    }

    public void setHasCheckpointTwo(Boolean hasCheckpointTwo) {
        this.hasCheckpointTwo = hasCheckpointTwo;
    }

    public void setHasCheckpointThree(Boolean hasCheckpointThree) {
        this.hasCheckpointThree = hasCheckpointThree;
    }


    @Override
    public String toString()
    {
        return "Attraction{" + "hasMedal=" + hasMedal + ", hasCheckpointOne=" + hasCheckpointOne + ", hasCheckpointTwo=" + hasCheckpointTwo + ", hasCheckpointThree=" + hasCheckpointThree + '}';
    }
}
