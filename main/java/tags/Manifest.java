package tags;

public class Manifest {
    private String mainClass;

    public void setMainClass(String value) {
        this.mainClass = value;
    }

    @Override
    public String toString() {
        return "                                      " +
                "[mainClass = '" + mainClass + "'  mainClass]";
    }
}
