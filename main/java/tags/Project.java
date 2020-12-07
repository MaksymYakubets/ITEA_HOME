package tags;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String xmlnsAttr;
    private String xmlnsXsiAttr;
    private String xsiSchemaLocationAttr;
    private String modelVersion;
    private String groupID;
    private String artifactID;
    private String version;
    private String name;
    private String url;
    private Properties properties;
    private List<Dependency> dependencies;
    private Build build;


    public Project() {
        properties = new Properties();
        dependencies = new ArrayList<>();
        build = new Build();
    }

    public void setModelVersion(String value) {
        this.modelVersion = value;
    }

    public void setGroupID(String value) {
        this.groupID = value;
    }

    public void setArtifactID(String value) {
        this.artifactID = value;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public void setName(String value) {
        this.name = value;
    }

    public void setURL(String value) {
        this.url = value;
    }

    public Properties getProperties() {
        return properties;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public Build getBuild() {
        return build;
    }

    public void setXmlnsAttr(String xmlnsAttr) {
        this.xmlnsAttr = xmlnsAttr;
    }

    public void setXmlnsXsiAttr(String xmlnsXsiAttr) {
        this.xmlnsXsiAttr = xmlnsXsiAttr;
    }

    public void setXsiSchemaLocationAttr(String xsiSchemaLocationAttr) {
        this.xsiSchemaLocationAttr = xsiSchemaLocationAttr;
    }

    @Override
    public String toString() {
        return "[Project " + '\n' +
                "     " + "[xmlnsAttr = '" + xmlnsAttr + "' xmlnsAttr]" + "\n" +
                "     " + "[xmlnsXsiAttr = '" + xmlnsXsiAttr + "' xmlnsXsiAttr]" + "\n" +
                "     " + "[xsiSchemaLocationAttr = '" + xsiSchemaLocationAttr + "' xsiSchemaLocationAttr]" + "\n" +
                "     " + "[modelVersion = '" + modelVersion + "' modelVersion]" + "\n" + "\n" +
                "     " + "[groupID = '" + groupID + "' groupID]"  + "\n" +
                "     " + "[artifactID = '" + artifactID + "' artifactID]" + "\n" +
                "     " + "[version = '" + version + "' version]" + "\n" + "\n" +
                "     " + "[name = '" + name + "' name]" + "\n" +
                "     " + "[url = '" + url + "' url]" + "\n" + "\n" +
                "[properties " + properties + " properties]" + "\n" + "\n" +
                "[dependencies " + dependencies + "\n" + "dependencies]" + "\n" + "\n" +
                "[build " + build + " build]" + "\n" + "\n" +
                "Project]"
                ;
    }

}
