package quamotion.webdriver;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.net.MediaType;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LocalLogs;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.NeedsLocalLogs;
import org.openqa.selenium.logging.profiler.HttpProfilerLogEntry;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.internal.ApacheHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by BartSaintGermain on 6/26/2016.
 */
public class QMCommandExecutor implements NeedsLocalLogs
{
    private final HttpClient client;
    private LocalLogs logs;
    private static final BiMap<String, QMCommandInfo> commandInformation;
    private static Gson gson = new GsonBuilder().create();

    // parameters
    public static final String deviceId = "deviceId";
    public static final String appId = "appId";
    public static final String appVersion = "appVersion";
    public static final String sessionId = "sessionId";
    public static final String elementId = "elementId";
    public static final String propertyName = "propertyName";
    public static final String propertyValue = "propertyValue";
    public static final String success = "Success";
    public static final String message = "Message";
    public static final String alertButtonName = "alertButtonName";

    // operations
    public static final String takeScreenshot = "takeScreenshot";
    public static final String getApplications = "getApplications";
    public static final String getDevices = "getDevices";
    public static final String getDeviceInformation = "getDeviceInformation";
    public static final String getInstalledApplications = "getInstalledApplications";
    public static final String deleteApplication = "deleteApplication";
    public static final String deleteSettings = "deleteSettings";
    public static final String launchApplication = "launchApplication";
    public static final String installApplication2 = "installApplication2";
    public static final String installApplication = "installApplication";
    public static final String rebootDevice = "rebootDevice";
    public static final String isReady = "isReady";
    public static final String getProperty = "getProperty";
    public static final String setProperty = "setProperty";
    public static final String reportStatus = "reportStatus";
    public static final String getProperties = "getProperties";
    public static final String clearText = "clear";
    public static final String dismissKeyboard = "dismissKeyboard";
    public static final String scrollTo = "scrollTo";
    public static final String getSessions = "sessions";
    public static final String elementByCoordinates = "elementByCoordinates";
    public static final String clickByCoordinates = "clickByCoordinates";
    public static final String removeSession = "removeSession";
    public static final String source = "source";
    public static final String flickCoordinate = "flickCoordinate";
    public static final String getAlertButtons = "getAlertButtons";
    public static final String clickAlertButton = "clickAlertButton";
    public static final String getPerformanceData = "getPerformanceData";
    public static final String killApp = "killApp";
    public static final String gotoHomeScreen = "gotoHomeScreen";

    static
    {
        commandInformation = HashBiMap.<String, QMCommandInfo>create();
        commandInformation.put(takeScreenshot, new QMCommandInfo(String.format("quamotion/device/:%s/screenshot",deviceId), HttpMethod.GET));
        commandInformation.put(getSessions, new QMCommandInfo(getSessions, HttpMethod.GET));
        commandInformation.put(removeSession, new QMCommandInfo(String.format("session/:%s", sessionId), HttpMethod.DELETE));
        commandInformation.put(getApplications, new QMCommandInfo("quamotion/app", HttpMethod.GET));
        commandInformation.put(getDevices, new QMCommandInfo("quamotion/device", HttpMethod.GET));
        commandInformation.put(getDeviceInformation, new QMCommandInfo(String.format("quamotion/device/:%s",deviceId), HttpMethod.GET));
        commandInformation.put(getInstalledApplications, new QMCommandInfo(String.format("quamotion/device/:%s/app",deviceId), HttpMethod.GET));
        commandInformation.put(deleteApplication, new QMCommandInfo(String.format("quamotion/device/:%s/app/:%s",deviceId, appId), HttpMethod.DELETE));
        commandInformation.put(deleteSettings, new QMCommandInfo(String.format("quamotion/device/:%s/app/:%s/settings",deviceId, appId), HttpMethod.DELETE));
        commandInformation.put(installApplication2, new QMCommandInfo(String.format("quamotion/device/:%s/app/:%s/:%s",deviceId, appId, appVersion), HttpMethod.POST));
        commandInformation.put(installApplication, new QMCommandInfo(String.format("quamotion/device/:%s/app/:%s",deviceId, appId), HttpMethod.POST));
        commandInformation.put(launchApplication, new QMCommandInfo(String.format("quamotion/device/:%s/app/:%s/launch?strict",deviceId, appId), HttpMethod.POST));
        commandInformation.put(rebootDevice, new QMCommandInfo(String.format("quamotion/device/:%s/reboot",deviceId), HttpMethod.POST));
        commandInformation.put(isReady, new QMCommandInfo(String.format("session/:%s/quamotion/isReady",sessionId), HttpMethod.GET));
        commandInformation.put(getProperty, new QMCommandInfo(String.format("session/:%s/element/:%s/property/:%s",sessionId, elementId, propertyName), HttpMethod.GET));
        commandInformation.put(reportStatus, new QMCommandInfo(String.format("session/:%s/report_status",sessionId), HttpMethod.POST));
        commandInformation.put(getProperties, new QMCommandInfo(String.format("session/:%s/element/:%s/property",sessionId, elementId), HttpMethod.GET));
        commandInformation.put(elementByCoordinates, new QMCommandInfo(String.format("session/:%s/quamotion/elementByCoordinates",sessionId), HttpMethod.POST));
        commandInformation.put(clickByCoordinates, new QMCommandInfo(String.format("session/:%s/touch/clickByCoordinate",sessionId), HttpMethod.POST));
        commandInformation.put(clearText, new QMCommandInfo(String.format("session/:%s/quamotion/clear",sessionId), HttpMethod.POST));
        commandInformation.put(scrollTo, new QMCommandInfo(String.format("session/:%s/element/:%s/quamotion/scrollTo",sessionId, elementId), HttpMethod.POST));
        commandInformation.put(dismissKeyboard, new QMCommandInfo(String.format("session/:%s/quamotion/dismissKeyboard",sessionId), HttpMethod.POST));
        commandInformation.put(source, new QMCommandInfo(String.format("session/:%s/source",sessionId), HttpMethod.GET));
        commandInformation.put(flickCoordinate, new QMCommandInfo(String.format("session/:%s/touch/flick",sessionId), HttpMethod.POST));
        commandInformation.put(getAlertButtons, new QMCommandInfo(String.format("session/:%s/alert/buttons",sessionId), HttpMethod.GET));
        commandInformation.put(clickAlertButton, new QMCommandInfo(String.format("session/:%s/alert/click/:%s",sessionId, alertButtonName), HttpMethod.POST));
        commandInformation.put(setProperty, new QMCommandInfo(String.format("session/:%s/element/:%s/property",sessionId, elementId), HttpMethod.POST));
        commandInformation.put(killApp, new QMCommandInfo(String.format("/quamotion/device/:%s/app/:%s/kill?strict",deviceId, appId), HttpMethod.POST));
        commandInformation.put(getPerformanceData, new QMCommandInfo(String.format("session/:%s/quamotion/performance",sessionId), HttpMethod.GET));
        commandInformation.put(gotoHomeScreen, new QMCommandInfo(String.format("session/:%s/wda/homescreen",sessionId), HttpMethod.POST));
    }

    public QMCommandExecutor() {
        this(ImmutableMap.<String, QMCommandInfo>of(), null);
    }

    public QMCommandExecutor(URL addressOfRemoteServer) {
        this(ImmutableMap.<String, QMCommandInfo>of(), addressOfRemoteServer);
    }

    public QMCommandExecutor(Map<String, QMCommandInfo> additionalCommands, URL addressOfRemoteServer){
        this.logs = LocalLogs.getNullLogger();

        try {
            URL remoteServer = addressOfRemoteServer == null?new URL("http://localhost:17894/wd/hub"):addressOfRemoteServer;
            ApacheHttpClient.Factory clientFactory = new ApacheHttpClient.Factory();
            this.client = clientFactory.createClient(remoteServer);
        } catch (MalformedURLException var6) {
            throw new WebDriverException(var6);
        }

        commandInformation.putAll(additionalCommands);
    }

    protected void defineCommand(String commandName, QMCommandInfo info) {
        Preconditions.checkNotNull(commandName);
        Preconditions.checkNotNull(info);
        this.commandInformation.put(commandName, info);
    }

    public void setLocalLogs(LocalLogs logs) {
        this.logs = logs;
    }

    private void log(String logType, LogEntry entry) {
        this.logs.addEntry(logType, entry);
    }

    public <R> R execute(QMCommand command, Class<R> resultType) throws IOException
    {
        HttpRequest httpRequest = encode(command);

        this.log("profiler", new HttpProfilerLogEntry(command.getName(), true));
        HttpResponse e = this.client.execute(httpRequest, true);
        this.log("profiler", new HttpProfilerLogEntry(command.getName(), false));
        R response;
        try
        {
            response = gson.fromJson(e.getContentString().trim(), resultType);
        }
        catch(Exception exc)
        {
            throw new IllegalStateException("the given response type does not match the response: " + e.getContentString());
        }

        return response;
    }

    public void execute(QMCommand command) throws IOException
    {
        HttpRequest httpRequest = encode(command);

        this.log("profiler", new HttpProfilerLogEntry(command.getName(), true));
        HttpResponse e = this.client.execute(httpRequest, true);
        this.log("profiler", new HttpProfilerLogEntry(command.getName(), false));

        if("quit".equals(command.getName())) {
            this.client.close();
        }
    }

    public static HttpRequest encode(QMCommand command) throws UnsupportedEncodingException {
        QMCommandInfo commandInfo = commandInformation.get(command.getName());
        if(commandInfo == null) {
            throw new UnsupportedCommandException(command.getName());
        } else {
            String uri = buildUri(command, commandInfo);
            HttpRequest request = new HttpRequest(commandInfo.getMethod(), uri);
            if(HttpMethod.POST == commandInfo.getMethod()) {
                String content = gson.toJson(command.getParameters());

                if(command.getParameters().containsKey("data"))
                {
                    content = (String)command.getParameters().get("data");
                }

                byte[] data = content.getBytes(Charsets.UTF_8.name());
                request.setHeader("Content-Type", MediaType.JSON_UTF_8.toString());

                request.setHeader("Content-Length", String.valueOf(data.length));
                request.setContent(data);
            }

            if(HttpMethod.GET == commandInfo.getMethod()) {
                request.setHeader("Cache-Control", "no-cache");
            }

            return request;
        }
    }

    public static String buildUri(QMCommand command, QMCommandInfo commandInfo){
        StringBuilder builder = new StringBuilder();
        Splitter pathSplitter = Splitter.on('/').omitEmptyStrings();
        Iterator<String> segments = pathSplitter.split(commandInfo.getUrl()).iterator();

        while(segments.hasNext()) {
            String part = (String)segments.next();
            builder.append("/");
            if(part.startsWith(":")) {
                Object value = command.getParameters().get(part.substring(1));
                String urlValue = Urls.urlEncode(String.valueOf(value));
                builder.append(urlValue);
            } else {
                builder.append(part);
            }
        }

        return builder.toString();
    }
}

