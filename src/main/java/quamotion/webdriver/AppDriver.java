package quamotion.webdriver;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.*;
import quamotion.webdriver.models.*;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by BartSaintGermain on 6/24/2016.
 */
public class AppDriver extends RemoteWebDriver implements HasTouchScreen {
    public static String BaseUrl = "http://localhost:17894/wd/hub";
    public static QMCommandExecutor qmCommandExecutor;
    public TouchScreen touchScreen;

    private QMKeyboard keyboard;
    private Validator validator;

    static {
        try {
            qmCommandExecutor = new QMCommandExecutor(new URL(BaseUrl));
        } catch (MalformedURLException exc) {
            throw new IllegalStateException("Error while initializing the quamotion.webdriver.QMCommandExecutor", exc);
        }
    }

    public AppDriver(MobileCapabilities capabilities) throws IOException, InterruptedException {
        super(new URL(BaseUrl), capabilities);

        this.touchScreen = new RemoteTouchScreen(this.getExecuteMethod());
        this.keyboard = new QMKeyboard(this);

        // add default validator
        AggregateValidator validator = new AggregateValidator();
        Validator reportValidator = new ReportValidator(this);
        validator.AddValidator(reportValidator);
        this.setValidator(validator);
    }

    public void waitUntilReady() throws IOException, InterruptedException {
        while (!isReady().isReady()) {
            Thread.sleep(1000);
        }
    }

    public void clearText() throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.clearText, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, this.getSessionId().toString())));
    }

    public void scrollToMarked(WebElement element, String marked) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        String elementId = remoteWebElement.getId();
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.scrollTo, ImmutableMap.<String, String>of(
                QMCommandExecutor.sessionId, this.getSessionId().toString(),
                QMCommandExecutor.elementId, elementId,
                "using", "xpath",
                "value", "//*[@marked='" + marked + "']")));
    }

    public void scrollTo(WebElement element, String xpath) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        String elementId = remoteWebElement.getId();
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.scrollTo, ImmutableMap.<String, String>of(
                QMCommandExecutor.sessionId, this.getSessionId().toString(),
                QMCommandExecutor.elementId, elementId,
                "using", "xpath",
                "value", xpath)));
    }

    public void scrollUpTo(WebElement element, String xpath) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        String elementId = remoteWebElement.getId();
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.scrollTo, ImmutableMap.<String, String>of(
                QMCommandExecutor.sessionId, this.getSessionId().toString(),
                QMCommandExecutor.elementId, elementId,
                "using", "xpath",
                "value", xpath,
                "direction", "Up")));
    }

    public Source getSource() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.source, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, this.getSessionId().toString())), Source.class);
    }

    public String getPageSource() {
        Gson gson = new GsonBuilder().create();
        try {
            return gson.toJson(this.getSource());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String[] getAlertButtons() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getAlertButtons, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, this.getSessionId().toString())), GetAlertButtonsResponse.class).getValue();
    }

    public void clickAlertButton(String buttonName) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.clickAlertButton, ImmutableMap.<String, String>of(
            QMCommandExecutor.sessionId, this.getSessionId().toString(),
            QMCommandExecutor.alertButtonName, buttonName)));

    }

    public void flickByCoordinate(WebElement element, int x, int y, int xOffset, int yOffset, int speed) throws IOException {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        String elementId = remoteWebElement.getId();
        builder.put(QMCommandExecutor.sessionId, this.getSessionId().toString())
                .put("element", elementId)
                .put("xCoordinate", Integer.toString(x))
                .put("yCoordinate", Integer.toString(y))
                .put("xoffset", Integer.toString(xOffset))
                .put("yoffset", Integer.toString(yOffset))
                .put("speed", Integer.toString(speed));
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.flickCoordinate, builder.build()));
    }

    public void scrollDownTo(WebElement element, String xpath) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        String elementId = remoteWebElement.getId();
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.scrollTo, ImmutableMap.<String, String>of(
                QMCommandExecutor.sessionId, this.getSessionId().toString(),
                QMCommandExecutor.elementId, elementId,
                "using", "xpath",
                "value", xpath,
                "direction", "Down")));
    }

    public void clickByCoordinate(int x, int y) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.clickByCoordinates, ImmutableMap.<String, String>of(
                QMCommandExecutor.sessionId, this.getSessionId().toString(),
                "x", Integer.toString(x),
                "y", Integer.toString(y))));
    }

    public Validator getValidator() {
        return this.validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public QMKeyboard getKeyboard() {
        return keyboard;
    }

    protected ExecuteMethod getExecuteMethod() {
        return super.getExecuteMethod();
    }

    public static void removeSession(Session session) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.removeSession, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, session.getId())));
    }

    public static void removeSession(String sessionId) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.removeSession, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId)));
    }

    public static Session[] getSessions(String deviceId) throws IOException {
        Session[] sessions = getSessions();
        return Arrays.stream(getSessions()).filter(session -> session.getDevice().getUniqueId().equals(deviceId)).toArray(Session[]::new);
    }

    public WebElement findElementByMarked(String marked) {
        return this.findElement(By.xpath("*[@marked='" + marked + "']"));
    }

    public static void removeAllSessions(String deviceId) throws IOException {
        Session[] sessions = getSessions(deviceId);
        for (Session session : sessions) {
            removeSession(session);
        }
    }

    public Status isReady() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.isReady, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, this.getSessionId().toString())), Status.class);
    }

    public static Device getDeviceInformation(String deviceId) throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getDeviceInformation, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId)), Device.class);
    }

    public static void killApp(String deviceId, String appId) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.killApp, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static Device[] getDevices() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getDevices), Device[].class);
    }

    public static Application[] getApplications() throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getApplications), Application[].class);
    }

    public static Application[] getInstalledApplications(String deviceId) throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getInstalledApplications, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId)), Application[].class);
    }

    public static void deleteApplication(String deviceId, String appId) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.deleteApplication, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static void deleteSettings(String deviceId, String appId) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.deleteSettings, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static void installApplication(String deviceId, String appId) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.installApplication, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId)));
    }

    public static void installApplication(String deviceId, String appId, String appVersion) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.installApplication2, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId, QMCommandExecutor.appVersion, appVersion)));
    }

    public static void rebootDevice(String deviceId) throws IOException {
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.rebootDevice, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId)));
    }

    public static void launchApplication(String deviceId, String appId, String[] arguments) throws IOException {
        Gson gson = new Gson();
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.launchApplication, ImmutableMap.<String, String>of(QMCommandExecutor.deviceId, deviceId, QMCommandExecutor.appId, appId, "data",gson.toJson(arguments))));
    }

    public static Session[] getSessions() throws IOException {
        GetSessionsResponse response = qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getSessions), GetSessionsResponse.class);
        return (Session[]) response.getValue();
    }

    public Object getProperty(WebElement webElement, String propertyName) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) webElement;
        String elementId = remoteWebElement.getId();
        String sessionId = this.getSessionId().toString();
        Response response = qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getProperty, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId, QMCommandExecutor.elementId, elementId, QMCommandExecutor.propertyName, propertyName)), Response.class);

        return response.getValue();
    }

    public void setProperty(WebElement webElement, String propertyName, Object propertyValue) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) webElement;
        String elementId = remoteWebElement.getId();
        String sessionId = this.getSessionId().toString();

        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getProperty, ImmutableMap.<String, Object>of(QMCommandExecutor.sessionId, sessionId, QMCommandExecutor.elementId, elementId, QMCommandExecutor.propertyName, propertyName,  QMCommandExecutor.propertyName, propertyValue)), Response.class);
    }

    public Map getProperties(WebElement webElement) throws IOException {
        RemoteWebElement remoteWebElement = (RemoteWebElement) webElement;
        String elementId = remoteWebElement.getId();
        String sessionId = this.getSessionId().toString();
        Response response = qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getProperties, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId, QMCommandExecutor.elementId, elementId)), Response.class);

        return (Map) response.getValue();
    }

    public PerformanceMetric[] getPerformanceData() throws IOException
    {
        String sessionId = this.getSessionId().toString();
        GetPerformanceDataResponse response = qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.getPerformanceData, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId)), GetPerformanceDataResponse.class);

        return response.getValue();
    }

    public void reportStatus(boolean success, String message) throws IOException {
        String sessionId = this.getSessionId().toString();
        qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.reportStatus, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, sessionId, QMCommandExecutor.success, Boolean.toString(success), QMCommandExecutor.message, message)));
    }

    public ElementAction findElementByCoordinate(int x, int y) throws IOException {
        return qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.elementByCoordinates, ImmutableMap.<String, String>of(
                QMCommandExecutor.sessionId, this.getSessionId().toString(),
                "x", Integer.toString(x),
                "y", Integer.toString(y))),
                FindElementByCoordinateResponse.class).getValue();
    }

    @Override
    public TouchScreen getTouch() {
        return this.touchScreen;
    }
}
