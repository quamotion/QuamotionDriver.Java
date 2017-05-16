package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BartSaintGermain on 5/15/2017.
 */
public class Rectangle
{
    @SerializedName("x")
    private int x;

    @SerializedName("y")
    private int y;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
