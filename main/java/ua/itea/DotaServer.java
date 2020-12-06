package ua.itea;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class DotaServer {

    private static final int THREADPOOL = 10;

    private final double timeout;
    private final ExecutorService executor;
    private final Set<String> gamers;

    public DotaServer(double timeout) {
        this.timeout = timeout;
        executor = Executors.newFixedThreadPool(THREADPOOL, target -> {
            Thread thread = new Thread(target);
            thread.setDaemon(true);
            return thread;
        });
        gamers = new HashSet<>();
    }

    public boolean connect(String name) {
        try {
            executor.submit(() -> connectGamer(name)).get((long) timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return false;
        }
        System.out.println("'" + name + "' successfully connected to DotaServer");
        return gamers.add(name);
    }

    public void connectGamer(String name) {
        int connectTime = (int) (5000 + Math.random() * 14999);
        System.out.println("'" + name + "' try to connect to server. Connect time: " + connectTime + " miliseconds");
        try {
            TimeUnit.MILLISECONDS.sleep(connectTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=============================================================");
    }

    public void shutdown() {
        executor.shutdown();
    }
}
