package tags;

public class Execution {
    private String id;
    private String phase;
    private Goals goals;

    public Execution() {
        goals = new Goals();
    }

    public Goals getGoals() {
        return goals;
    }

    public void setID(String value) {
        this.id = value;
    }

    public void setPhase(String value) {
        this.phase = value;
    }

    @Override
    public String toString() {

        return  "                          " + "[execution" + "\n" +
                "                                " + "[id = '" + id + "'  id]" + "\n" +
                "                                " + "[phase = '" + phase + "'  phase]" + "\n" +
                "                                " + "[goals " + "\n" + goals + "\n" +
                "                                " + " goals]" + "\n" +
                "                          " + "execution";
    }
}
