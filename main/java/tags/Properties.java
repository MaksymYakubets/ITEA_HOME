package tags;

public class Properties {
    private String projectBuildSourceEncoding;  // project.build.sourceEncoding
    private String mavenCompilerSource;         // maven.compiler.source
    private String mavenCompilerTarget;         // maven.compiler.target

    public void setProjectBuildSourceEncoding(String value) {
        this.projectBuildSourceEncoding = value;
    }

    public void setMavenCompilerSource(String value) {
        this.mavenCompilerSource = value;
    }

    public void setMavenCompilerTarget(String value) {
        this.mavenCompilerTarget = value;
    }

    @Override
    public String toString() {
        return "\n" +
                "     " + "[projectBuildSourceEncoding = '" + projectBuildSourceEncoding + "'  projectBuildSourceEncoding]" + "\n" +
                "     " + "[mavenCompilerSource = '" + mavenCompilerSource + "'  projectBuildSourceEncoding]" + "\n" +
                "     " + "[mavenCompilerTarget = '" + mavenCompilerTarget + "'  projectBuildSourceEncoding]" + "\n"
                ;
    }
}
