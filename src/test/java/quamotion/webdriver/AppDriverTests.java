package quamotion.webdriver;

import org.junit.Test;
import quamotion.webdriver.models.Source;
import quamotion.webdriver.models.Widget;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by BartSaintGermain on 9/7/2016.
 */
public class AppDriverTests
{
    @Test
    public void getDevicesTest() throws IOException, InterruptedException {
        AppDriver.getDevices();
        AppCapabilities capabilities = new AppCapabilities("FUH7N16709007219", "demo.quamotion.Acquaint","151", true);
        capabilities.reuseSession(true);
        AppDriver driver = new AppDriver(capabilities);
        driver.waitUntilReady();

        Source source = driver.getSource();
    }
}
