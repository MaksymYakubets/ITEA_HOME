package tags;

public class Dependency {
    private String groupID;
    private String artifactID;
    private String version;
    private String scope;

    public void setGroupID(String value) {
        this.groupID = value;
    }

    public void setArtifactID(String value) {
        this.artifactID = value;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public void setScope(String value) {
        this.scope = value;
    }

    @Override
    public String toString() {
        return  "\n" + "  " + "[dependency " + "\n" +
                "     " + "[groupID = '" + groupID + "' groupID] " + "\n" +
                "     " + "[artifactID = '" + artifactID + "' artifactID] " + "\n" +
                "     " + "[version = '" + version + "' version] " + "\n" +
                "     " + "[scope = '" + scope + "' scope] " + "\n" +
                "dependency] ";
    }
}
