package quamotion.webdriver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by BartSaintGermain on 6/24/2016.
 */
public class AppCapabilities  extends DesiredCapabilities
{

    public AppCapabilities(String deviceId, String appId)
    {
        this(deviceId, appId, true);
    }

    public AppCapabilities(String deviceId, String appId, boolean clearApplicationSettings)
    {
        this(deviceId, appId, null, clearApplicationSettings);
    }

    public AppCapabilities(String deviceId, String appId,String appVersion, boolean clearApplicationSettings)
    {
        this.setCapability("deviceId", deviceId);
        this.setCapability("applicationType", "Native");
        this.setCapability("appId", appId);
        if(appVersion != null)
        {
            this.setCapability("appVersion", appVersion);
        }
        this.setCapability("clearApplicationSettings", clearApplicationSettings);
    }

    public void reuseSession(boolean reuseSession)
    {
        this.setCapability("reuseExistingSession", reuseSession);
    }
}