package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;
import org.openqa.selenium.remote.SessionId;

/**
 * Created by BartSaintGermain on 9/7/2016.
 */
public class WebdriverResponse <V>
{
    /// <summary>
    /// Gets the response JSON value.
    /// </summary>
    @SerializedName("value")
    private V value;

    /// <summary>
    /// Gets the an opaque handle used by the server to determine where to route session-specific commands.
    /// </summary>
    @SerializedName("sessionId")
    private String sessionId;

    /// <summary>
    /// Gets the status code summarizing the result of the command. A non-zero value indicates that the command failed.
    /// </summary>
    @SerializedName("status")
    private Integer status;

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}