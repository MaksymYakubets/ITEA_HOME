package itea;

public class Mainer implements Runnable {
    private String name;
    private int gold;
    private RinatAkhmetov rinatAkhmetov;

    public Mainer(String name, RinatAkhmetov rinatAkhmetov) {
        this.gold = 0;
        this.name = name;
        this.rinatAkhmetov = rinatAkhmetov;
    }

    @Override
    public void run() {
        while (true) {
            int money = rinatAkhmetov.takeMoney();
            gold = gold + money;
            System.out.println(name + " get " + gold);

            if (money < 3) {
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }
}
