import java.util.HashMap;

public class VendingMachine {

    private double[] coinValues;
    private double currentCoinsValue;

    public static final int NICKEL = 0;
    public static final int DIME = 1;
    public static final int QUARTER = 2;

    private HashMap<String, Integer> itemCounts;

    VendingMachine(){
        this.itemCounts = new HashMap<String, Integer>();

        this.coinValues = new double[3];
        this.currentCoinsValue = 0.00;

        this.coinValues[NICKEL] = 0.05;
        this.coinValues[DIME] = 0.10;
        this.coinValues[QUARTER] = 0.25;
    }

    public void addCoin(int coin){
        this.currentCoinsValue += this.coinValues[coin];
    }

    public double getCurrentCoinsValue(){
        return this.currentCoinsValue;
    }

    public boolean canMakeChange() {
        return false;
    }

    public void addItem(String itemName, int amount) {
        if (this.itemCounts.get(itemName) != null)
            this.itemCounts.put(itemName, this.itemCounts.get(itemName) + amount);
        else
            this.itemCounts.put(itemName, amount);
    }

    public int getItemCount(String itemName){
        return itemCounts.get(itemName);
    }
}
