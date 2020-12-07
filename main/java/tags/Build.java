package tags;

import java.util.ArrayList;
import java.util.List;

public class Build {
    private List<Plugin> plugins;

    public Build() {
        plugins = new ArrayList<>();
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    @Override
    public String toString() {
        return "\n" +
                "     " + "[pluginManagement" +  "\n" +
                "          " + "[plugins" +  "\n" + plugins +  "\n" +
                "          " + "plugins]" +  "\n" +
                "     " +"pluginManagement]"  +  "\n";
       }
}
