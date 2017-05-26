package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by BartSaintGermain on 5/26/2017.
 */
public class ElementAction {

    @SerializedName("activityName")
    private String activityName;

    @SerializedName("actionName")
    private ActionName actionName;

    @SerializedName("elementId")
    private String elementId;

    @SerializedName("elements")
    private ArrayList<Widget> elements;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ActionName getActionName() {
        return actionName;
    }

    public void setActionName(ActionName actionName) {
        this.actionName = actionName;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public ArrayList<Widget> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Widget> elements) {
        this.elements = elements;
    }
}
