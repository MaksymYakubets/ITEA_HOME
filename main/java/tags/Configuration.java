package tags;


public class Configuration {
    private DescriptorRefs descriptorRefs;
    private Archive archive;

    public Configuration() {
        descriptorRefs = new DescriptorRefs();
        archive = new Archive();
    }

    public DescriptorRefs getDescriptorRefs() {
        return descriptorRefs;
    }

    public Archive getArchive() {
        return archive;
    }

    @Override
    public String toString() {
        return "\n" +
                "                          " + "[descriptorRefs " + descriptorRefs + "\n" +
                "                          " + "descriptorRefs]" + "\n" +
                "                          " + "[archive" + archive + "\n" +
                "                          " + "archive]"
                ;
    }
}
