package tags;

public class DescriptorRefs {
    private String descriptorRef;

    public void setDescriptorRef(String descriptorRef) {
        this.descriptorRef = descriptorRef;
    }

    @Override
    public String toString() {
        return "\n" +
                "                                " + "[descriptorRef = '" +
                 descriptorRef +  "'   descriptorRef]";
    }
}

