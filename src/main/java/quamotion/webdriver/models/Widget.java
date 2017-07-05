package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BartSaintGermain on 5/15/2017.
 */
public class Widget
{
    @SerializedName("children")
    private ArrayList<Widget> children;

    @SerializedName("className")
    private String className;

    @SerializedName("classNames")
    private ArrayList<String> classNames;

    @SerializedName("id")
    private String id;

    @SerializedName("rectangle")
    private Rectangle rectangle;

    @SerializedName("elementId")
    private String elementId;

    @SerializedName("suggestedXPath")
    private String suggestedXPath;

    @SerializedName("visible")
    private boolean visible;

    @SerializedName("properties")
    private HashMap<String, Object> properties;

    public ArrayList<Widget> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Widget> children) {
        this.children = children;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<String> getClassNames() {
        return classNames;
    }

    public void setClassNames(ArrayList<String> classNames) {
        this.classNames = classNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getSuggestedXPath() {
        return suggestedXPath;
    }

    public void setSuggestedXPath(String suggestedXPath) {
        this.suggestedXPath = suggestedXPath;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
    }
}
