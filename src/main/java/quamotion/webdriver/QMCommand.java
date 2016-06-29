package quamotion.webdriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BartSaintGermain on 6/27/2016.
 */
public class QMCommand
{
    private String name;
    private Map<String, ?> parameters;

    public QMCommand(String name) {
        this(name, new HashMap());
    }

    public QMCommand(String name, Map<String, ?> parameters) {
        this.parameters = parameters;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, ?> getParameters() {
        return (Map)(this.parameters == null?new HashMap():this.parameters);
    }

    public String toString() {
        return "[" + this.name + " " + this.parameters + "]";
    }
}
