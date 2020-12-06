package ua.itea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    private static final int THREADPOOL = 10;
    private static final double TIMEOUT = 15000;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREADPOOL, target -> {
            Thread thread = new Thread(target);
            thread.setDaemon(true);
            return thread;
        });
        DotaServer dotaServer = new DotaServer(TIMEOUT);

        String[] gamersNames = {"Storm Spirit", "Necrophos", "Dark Willow", "Pudge", "Bounty Hunter", "Brewmaster", "Rubick", "Death Prophet", "Oracle", "Windranger"};
        Map<String, Future<Boolean>> gamersConnections = new HashMap<>();

        for (String gamerName : gamersNames) {
            Gamer gamer = new Gamer(gamerName, dotaServer);
            gamersConnections.put(gamerName, executor.submit(gamer));
        }



        for (Map.Entry<String, Future<Boolean>> gamerConnection : gamersConnections.entrySet()) {
            String gamerName = gamerConnection.getKey();
            try {
                boolean isGamerConnected = gamerConnection.getValue().get();
                if (!isGamerConnected) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("Gamer '" + gamerName + "' was not connected");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("Connection lost. This game is safe to leave");
                    return;
                }

            } catch (InterruptedException | ExecutionException e) {
                System.out.println("==========================================");
                System.out.println("Connection lost. This game is safe to leave");
            } finally {
                dotaServer.shutdown();
                executor.shutdown();
            }
        }
        System.out.println("==========================================");
        System.out.println("All gamers are connected. GAME IS STARTED!");
    }
}