package quamotion.webdriver.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BartSaintGermain on 6/28/2016.
 */
public class Status
{
    @SerializedName("sessionId")
    private String sessionId;

    @SerializedName("status")
    private String status;

    @SerializedName("value")
    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
