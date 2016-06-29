package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BartSaintGermain on 6/27/2016.
 */
public class DeviceConfiguration
{
    /// <summary>
    /// Gets or sets the operating system.
    /// </summary>
    @SerializedName("OperatingSystem")
    private String operatingSystem;

    /// <summary>
    /// Gets or sets the version of the operating system.
    /// </summary>
    @SerializedName("OperatingSystemVersion")
    private String operatingSystemVersion;

    /// <summary>
    /// Gets or sets the type of processor.
    /// </summary>
    @SerializedName("CpuType")
    private String cpuType;

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }
}
