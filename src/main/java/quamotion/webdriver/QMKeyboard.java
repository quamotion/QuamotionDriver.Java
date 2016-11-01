package quamotion.webdriver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteKeyboard;

import java.io.IOException;

/**
 * Created by BartSaintGermain on 10/31/2016.
 */
public class QMKeyboard extends RemoteKeyboard
{
    private AppDriver driver;

    public QMKeyboard(AppDriver driver) {
        super(driver.getExecuteMethod());
        this.driver = driver;
    }

    public void dismiss() throws IOException
    {
        AppDriver.qmCommandExecutor.execute(new QMCommand(QMCommandExecutor.dismissKeyboard, ImmutableMap.<String, String>of(QMCommandExecutor.sessionId, driver.getSessionId().toString())));
    }
}