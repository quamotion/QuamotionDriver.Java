package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BartSaintGermain on 6/27/2016.
 */
public class Application
{
    /// <summary>
    /// Gets or sets the application icon.
    /// </summary>
    @SerializedName("AppId")
    private String appId;

    /// <summary>
    /// Gets or sets a user-friendly display name of the application.
    /// </summary>
    @SerializedName("DisplayName")
    private String displayName;

    /// <summary>
    /// Gets or sets the version of this app.
    /// </summary>
    @SerializedName("Version")
    private String version;

    /// <summary>
    /// Gets or sets the version display name of this app.
    /// </summary>
    @SerializedName("VersionDisplayName")
    private String versionDisplayName;


    /// <summary>
    /// Gets or sets the configuration (type of devices) on which this app can run.
    /// </summary>
    @SerializedName("SupportedConfigurations")
    private DeviceConfiguration supportedConfigurations;

    public DeviceConfiguration getSupportedConfigurations() {
        return supportedConfigurations;
    }

    public void setSupportedConfigurations(DeviceConfiguration supportedConfigurations) {
        this.supportedConfigurations = supportedConfigurations;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionDisplayName() {
        return versionDisplayName;
    }

    public void setVersionDisplayName(String versionDisplayName) {
        this.versionDisplayName = versionDisplayName;
    }
}
