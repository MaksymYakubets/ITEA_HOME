package tags;

public class Archive {
    private Manifest manifest;

    public Archive() {
        manifest = new Manifest();
    }

    public Manifest getManifest() {
        return manifest;
    }

    @Override
    public String toString() {
        return "\n" +
                "                                " + "[manifest" + "\n" +
                manifest + "\n" +
                "                                " + " manifest]" ;
    }
}