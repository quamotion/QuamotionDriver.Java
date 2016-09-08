package quamotion.webdriver;

import com.google.common.base.Objects;
import org.openqa.selenium.remote.http.HttpMethod;

/**
 * Created by BartSaintGermain on 6/27/2016.
 */
public class QMCommandInfo
{
    private final String url;
    private final HttpMethod method;

    public QMCommandInfo(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }

    String getUrl() {
        return this.url;
    }

    HttpMethod getMethod() {
        return this.method;
    }

    public boolean equals(Object o) {
        if(!(o instanceof QMCommandInfo)) {
            return false;
        } else {
            QMCommandInfo other = (QMCommandInfo)o;
            return this.method.equals(other.method) && this.url.equals(other.url);
        }
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.method, this.url});
    }
}
