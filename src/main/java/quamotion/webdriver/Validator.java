package quamotion.webdriver;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by BartSaintGermain on 7/17/2017.
 */
public interface Validator
{
    void assertEqual(Object expected, Object actual, String successMessage, String failureMessage) throws IOException;

    void assertNotEqual(Object expected, Object actual, String successMessage, String failureMessage) throws IOException;

    void assertNotEmpty(Collection collection,  String successMessage, String failureMessage) throws IOException;

    void assertEmpty(Collection collection, String successMessage, String failureMessage) throws IOException;
}
