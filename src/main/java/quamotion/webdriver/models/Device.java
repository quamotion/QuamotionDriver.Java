package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;
import org.openqa.selenium.Rectangle;

/**
 * Created by BartSaintGermain on 6/27/2016.
 */
public class Device
{

    /// <summary>
    /// Gets a <see cref="string"/> that represents the manufacturer of the device.
    /// </summary>
    @SerializedName("manufacturer")
    private String manufacturer;

    /// <summary>
    /// Gets a <see cref="string"/> that represents the serial number of the device.
    /// </summary>
    @SerializedName("serialNumber")
    private String serialNumber;

    /// <summary>
    /// Gets a <see cref="string"/> that uniquely identifies the device with the device provider.
    /// </summary>
    @SerializedName("uniqueId")
    private String uniqueId;

    /// <summary>
    /// Gets the <see cref="string"/> of the provider that owns the device.
    /// </summary>
    @SerializedName("providerId")
    private String providerId;

    /// <summary>
    /// Gets a <see cref="string"/> that represents the model of the device.
    /// </summary>
    @SerializedName("model")
    private String model;

    /// <summary>
    /// Gets or sets a name that identifies the device.
    /// </summary>
    @SerializedName("name")
    private String name;

    /// <summary>
    /// Gets the height and width of the view port (screen) of the mobile device.
    /// The viewport is the <see cref="Resolution"/> with the <see cref="DeviceRotation"/> applied.
    /// </summary>
    @SerializedName("viewPort")
    private Rectangle viewPort;

    /// <summary>
    /// Gets or sets the rotation of the device.
    /// </summary>
    @SerializedName("deviceRotation")
    private DeviceRotation deviceRotation;

    /// <summary>
    /// Gets or sets the resolution of the mobile device.
    /// </summary>
    @SerializedName("resolution")
    private Rectangle resolution;

    /// <summary>
    /// Gets information about the device, such as the operating system
    /// and the CPU type.
    /// </summary>
    @SerializedName("configuration")
    private DeviceConfiguration configuration;

    /// <summary>
    /// Gets the state of the device.
    /// </summary>
    @SerializedName("state")
    private DeviceState state;

    /// <summary>
    /// Gets the type of the device.
    /// </summary>
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getViewPort() {
        return viewPort;
    }

    public void setViewPort(Rectangle viewPort) {
        this.viewPort = viewPort;
    }

    public DeviceRotation getDeviceRotation() {
        return deviceRotation;
    }

    public void setDeviceRotation(DeviceRotation deviceRotation) {
        this.deviceRotation = deviceRotation;
    }

    public Rectangle getResolution() {
        return resolution;
    }

    public void setResolution(Rectangle resolution) {
        this.resolution = resolution;
    }

    public DeviceConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(DeviceConfiguration configuration) {
        this.configuration = configuration;
    }

    public DeviceState getState() {
        return state;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }
}
