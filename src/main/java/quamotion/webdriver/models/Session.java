package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;
import org.openqa.selenium.Capabilities;

/**
 * Created by BartSaintGermain on 9/7/2016.
 */
public class Session
{
    /// <summary>
    /// Gets the ID of this session.
    /// </summary>
    @SerializedName("Id")
    private String id;

    /// <summary>
    /// Gets the time at which the session was created.
    /// </summary>
    @SerializedName("CreatedAt")
    private String createdAt;

    /// <summary>
    /// Gets the device to which the session is attached.
    /// </summary>
    @SerializedName("Device")
    private Device device;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
