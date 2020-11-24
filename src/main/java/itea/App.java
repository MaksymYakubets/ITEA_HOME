package itea;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        RinatAkhmetov rinatAkhmetov = new RinatAkhmetov(1000);
        List<Mainer> mainers = new ArrayList<>();

        for (int i = 1; rinatAkhmetov.getMoney() > 0; i++) {
            Mainer mainer = new Mainer("John_" + i, rinatAkhmetov);
            mainers.add(mainer);
            new Thread(mainer).start();

            if (i > 5) {
                TimeUnit.SECONDS.sleep(10);
            }
        }

        System.out.println("==================================== Finita la comedia Rinat!!! =========================================");
        System.out.println("Rinat Akhmetov final result: " + rinatAkhmetov.getMoney());
        mainers.stream().map(mainer -> mainer.getName() + " has " + mainer.getGold()).forEach(System.out::println);
    }
}
