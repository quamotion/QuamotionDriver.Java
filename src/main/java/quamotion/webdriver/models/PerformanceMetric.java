package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BartSaintGermain on 10/30/2017.
 */
/// <summary>
/// Represents a performance measure.
/// </summary>
public class PerformanceMetric {
    /// the units in which the performance metric is expressed
    @SerializedName("Units")
    private String units;

    /// the name of the performance metric.
    @SerializedName("Name")
    private String name;

    /// the value of the performance metric.
    @SerializedName("Value")
    private double value;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}