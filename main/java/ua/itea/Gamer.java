package ua.itea;

import java.util.concurrent.Callable;

public class Gamer implements Callable<Boolean> {
    private final String name;
    private final DotaServer dotaServer;

    public Gamer(String name, DotaServer dotaServer) {
        this.name = name;
        this.dotaServer = dotaServer;
    }

    public String getName() {
        return name;
    }

    @Override
    public Boolean call() throws Exception {
        return dotaServer.connect(name);
    }
}
