package itea;

public class RinatAkhmetov {
    private int money;

    public RinatAkhmetov(int money) {
        this.money = money;
        System.out.println("Rinatov Ahmetov initial balance: " + money);
    }

    public synchronized int takeMoney() {
        int mained = Math.min(money, 3);
        money = money - mained;
        System.out.println("Rinat Ahmetov has " + money);
        return mained;
    }

    public int getMoney() {
        return money;
    }
}