package quamotion.webdriver;

import quamotion.webdriver.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by BartSaintGermain on 7/17/2017.
 */
public class AggregateValidator implements Validator
{
    private ArrayList<Validator> iVarValidators = new ArrayList<Validator>();

    public ArrayList<Validator> getValidators()
    {
        return this.iVarValidators;
    }

    public void AddValidator(Validator validator)
    {
        this.iVarValidators.add(validator);
    }

    public void assertEqual(Object expected, Object actual, String successMessage, String failureMessage) throws IOException
    {
        for (Validator validator: this.getValidators())
        {
            validator.assertEqual(expected, actual, successMessage, failureMessage);
        }
    }

    public void assertNotEqual(Object expected, Object actual, String successMessage, String failureMessage) throws IOException
    {
        for (Validator validator: this.getValidators())
        {
            validator.assertNotEqual(expected, actual, successMessage, failureMessage);
        }
    }

    public void assertNotEmpty(Collection collection, String successMessage, String failureMessage) throws IOException
    {
        for (Validator validator: this.getValidators())
        {
            validator.assertNotEmpty(collection, successMessage, failureMessage);
        }
    }

    public void assertEmpty(Collection collection, String successMessage, String failureMessage) throws IOException
    {
        for (Validator validator: this.getValidators())
        {
            validator.assertEmpty(collection, successMessage, failureMessage);
        }
    }
}
