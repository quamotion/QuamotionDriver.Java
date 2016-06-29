package quamotion.webdriver.models;

/**
 * Created by BartSaintGermain on 6/28/2016.
 */
public enum SessionStatus
{
    /// <summary>
    /// The session status is unknown.
    /// </summary>
    Unknown,

    /// <summary>
    /// The application is being deployed to the device.
    /// </summary>
    Deploying,

    /// <summary>
    /// Deploying the application to the device failed.
    /// </summary>
    DeployFailed,

    /// <summary>
    /// The application is running on the device.
    /// </summary>
    Running,

    /// <summary>
    /// The session is being deleted.
    /// </summary>
    Stopping
}
