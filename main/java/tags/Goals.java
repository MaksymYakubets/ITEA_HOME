package tags;

public class Goals {
    private String goal;

    public void setGoal(String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "                                      "  +
                "[goal = '" + goal + "'  goal]";
    }
}
