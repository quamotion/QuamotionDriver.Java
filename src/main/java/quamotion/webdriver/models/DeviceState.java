package quamotion.webdriver.models;

/**
 * Created by BartSaintGermain on 6/27/2016.
 */
public enum DeviceState {
    /// <summary>
    /// The device is connected to the PC, but the device has not authorized remote control.
    /// This requires authorizing adb (Android) or pairing the device with the PC (iOS).
    /// </summary>
    Unauthorized,

    /// <summary>
    /// The device is stopped.
    /// </summary>
    Stopped,

    /// <summary>
    /// The device is starting.
    /// </summary>
    Starting,

    /// <summary>
    /// The device is running.
    /// </summary>
    Started,

    /// <summary>
    /// The device is stopping.
    /// </summary>
    Stopping,
}
