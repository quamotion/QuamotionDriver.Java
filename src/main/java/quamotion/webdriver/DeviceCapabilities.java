package quamotion.webdriver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by BartSaintGermain on 9/29/2017.
 */
public class DeviceCapabilities extends MobileCapabilities
{
    public DeviceCapabilities(String deviceId)
    {
        this(deviceId, true);
    }

    public DeviceCapabilities(String deviceId, boolean reuseExistingSession)
    {
        super("Device",deviceId, reuseExistingSession);
    }
}