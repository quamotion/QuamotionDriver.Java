package quamotion.webdriver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by BartSaintGermain on 9/29/2017.
 */
public class MobileCapabilities extends DesiredCapabilities {
    public MobileCapabilities(String applicationType, String deviceId, boolean reuseExistingSession)
    {
        this.setCapability("deviceId", deviceId);
        this.setCapability("applicationType", applicationType);

        this.setCapability("reuseExistingSession", reuseExistingSession);
    }
}
