package quamotion.webdriver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import quamotion.webdriver.models.Application;
import quamotion.webdriver.models.Device;
import quamotion.webdriver.models.Status;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by BartSaintGermain on 6/24/2016.
 */
public class AppDriver extends RemoteWebDriver
{
    public static String BaseUrl = "http://localhost:17894/wd/hub";
    public static QMCommandExecutor qmCommandExecutor;

    static
    {
        try
        {
            qmCommandExecutor = new QMCommandExecutor(new URL(BaseUrl));
        }
        catch(MalformedURLException exc)
        {
            throw new IllegalStateException("Error while initializing the quamotion.webdriver.QMCommandExecutor", exc);
        }
    }

    public AppDriver(AppCapabilities capabilities) throws IOException, InterruptedException {
        super(new URL(BaseUrl), capabilities);
    }

    public void WaitUntilReady() throws IOException, InterruptedException {
        while(!isReady().isReady())
        {
            Thread.sleep(1000);
        }
    }

    public Status isReady() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.isReady, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, this.getSessionId().toString())),Status.class);
    }

    public static Device getDeviceInformation(String deviceId) throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getDeviceInformation, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId)), Device.class);
    }

    public static Device[] getDevices() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getDevices), Device[].class);
    }

    public static Application[] getApplications() throws IOException{
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getApplications), Application[].class);
    }

    public static Application[] getInstalledApplications(String deviceId) throws IOException{
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getInstalledApplications, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId)), Application[].class);
    }

    public static void deleteApplication(String deviceId, String appId) throws IOException{
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.deleteApplication, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static void deleteSettings(String deviceId, String appId) throws IOException{
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.deleteSettings, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static void installApplication(String deviceId, String appId) throws IOException{
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.installApplication, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static void installApplication(String deviceId, String appId, String appVersion) throws IOException{
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.installApplication2, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId, QMCommandExecutor.appVersion, appVersion)));
    }

    public static void rebootDevice(String deviceId) throws IOException{
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.rebootDevice, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId)));
    }

    public Object getProperty(String sessionId, String elementId, String propertyName) throws IOException{
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getProperty, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId, QMCommandExecutor.elementId, elementId, QMCommandExecutor.propertyName, propertyName)), Device.class);
    }
    public Object getProperty(WebElement webElement, String propertyName) throws IOException{
        RemoteWebElement remoteWebElement = (RemoteWebElement)webElement;
        String elementId = remoteWebElement.getId();
        String sessionId = this.getSessionId().toString();
        Response response = qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getProperty, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId, QMCommandExecutor.elementId, elementId, QMCommandExecutor.propertyName, propertyName)), Response.class);

        return ((Map)response.getValue()).get("Result");
    }
}
