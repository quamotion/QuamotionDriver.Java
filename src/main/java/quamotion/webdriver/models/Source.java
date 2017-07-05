package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by BartSaintGermain on 5/15/2017.
 */
public class Source
{
    @SerializedName("sessionId")
    private String  sessionId;

    @SerializedName("status")
    private int status;

    @SerializedName("value")
    private ArrayList<Widget> root;
}
