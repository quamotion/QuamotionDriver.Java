package quamotion.webdriver;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by BartSaintGermain on 7/17/2017.
 */
public class ReportValidator implements Validator {
    private AppDriver iVarDriver;

    private void setDriver(AppDriver driver) {
        this.iVarDriver = driver;
    }

    private AppDriver getDriver()
    {
        return this.iVarDriver;
    }

    public ReportValidator(AppDriver driver)
    {
        this.setDriver(driver);
    }

    public void assertEqual(Object expected, Object actual, String successMessage, String failureMessage) throws IOException
    {
        boolean success = expected.equals(actual);

        this.getDriver().reportStatus(success, success ? String.format(successMessage, expected, actual): String.format(failureMessage, expected, actual));
    }

    public void assertNotEqual(Object expected, Object actual, String successMessage, String failureMessage) throws IOException
    {
        boolean success = !expected.equals(actual);

        this.getDriver().reportStatus(success, success ? String.format(successMessage, expected, actual): String.format(failureMessage, expected, actual));
    }


    public void assertNotEmpty(Collection collection, String successMessage, String failureMessage) throws IOException
    {
        boolean success = collection != null && !collection.isEmpty();

        this.getDriver().reportStatus(success, success ? String.format(successMessage, collection): String.format(failureMessage, collection));
    }

    public void assertEmpty(Collection collection, String successMessage, String failureMessage) throws IOException
    {
        boolean success = collection == null || !collection.isEmpty();

        this.getDriver().reportStatus(success, success ? String.format(successMessage, collection): String.format(failureMessage, collection));
    }
}
