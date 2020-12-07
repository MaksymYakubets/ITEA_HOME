package tags;

import java.util.ArrayList;
import java.util.List;

public class Plugin {
    private String artifactID;
    private String version;
    private Configuration configuration;
    private List<Execution> executions;

    public Plugin() {
        configuration = new Configuration();
        executions = new ArrayList<>();
    }

    public void setArtifactID(String value) {
        this.artifactID = value;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<Execution> getExecutions() {
        return executions;
    }

    @Override
    public String toString() {
        return  "               " + "plugin [" + "\n" +
                "                    " + "[artifactID = '" + artifactID + "' artifactID]" + "\n" +
                "                    " + "[version = '" + version + "' version]" + "\n" +
                "                    " + "[configuration " + configuration + "\n" +
                "                    " + "configuration]" + "\n" +
                "                    " + "[executions" + "\n" + executions + "\n" +
                "                    " + "executions"
                ;
    }
}