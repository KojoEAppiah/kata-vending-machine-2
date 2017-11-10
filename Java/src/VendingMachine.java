import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class VendingMachine {

    private double[] coinValues;
    private double currentCoinsValue;
    private int currentCoins[];
    private double coinReturnValue;
    private int[] coinReturnCoins;
    private Double coinBoxValue;
    private int[] coinBoxCoins;

    public static final int NICKEL = 0;
    public static final int DIME = 1;
    public static final int QUARTER = 2;

    private HashMap<String, Integer> itemCounts;
    private HashMap<String, Double> itemPrices;

    private DisplayScreen displayScreen;

    VendingMachine(){
        this.itemCounts = new HashMap<String, Integer>();
        this.itemPrices = new HashMap<String, Double>();

        this.coinValues = new double[3];

        this.coinBoxCoins = new int[3];
        this.currentCoins = new int[3];
        this.coinReturnCoins = new int[3];
        for(int index = 0; index < 3; index++){
            this.coinBoxCoins[index] = 0;
            this.currentCoins[index] = 0;
            this.coinReturnCoins[index] = 0;
        }

        this.currentCoinsValue = 0.00;
        this.coinBoxValue = 0.00;
        this.coinReturnValue = 0.00;

        this.coinValues[NICKEL] = 0.05;
        this.coinValues[DIME] = 0.10;
        this.coinValues[QUARTER] = 0.25;

        this.displayScreen = new DisplayScreen("EXACT CHANGE ONLY");

    }

    public void addCoin(int coin){
        this.currentCoinsValue += this.coinValues[coin];
        this.displayScreen.setCoinDisplay(currentCoinsValue);
    }

    public double getCurrentCoinsValue(){
        return this.currentCoinsValue;
    }

    public void addItem(String itemName, int amount) {
        if (this.itemCounts.get(itemName) != null)
            this.itemCounts.put(itemName, this.itemCounts.get(itemName) + amount);
        else
            this.itemCounts.put(itemName, amount);
    }

    public void setItemPrice(String itemName, double price) {
        this.itemPrices.put(itemName, price);
    }


    public int getItemCount(String itemName){
        return this.itemCounts.get(itemName);
    }

    public double getItemPrice(String itemName) {
        return this.itemPrices.get(itemName);
    }

    public double getCoinReturnValue() {
        return this.coinReturnValue;
    }

    public int getCoinBoxCoinCount(int coinType) {
        return this.coinBoxCoins[coinType];
    }

    public boolean canMakeChange() {
        return false;
    }

    public void selectItem(String itemName) {
        if (this.currentCoinsValue >= this.itemPrices.get(itemName) && this.itemCounts.get(itemName) != null) {
            this.itemCounts.put(itemName, (this.itemCounts.get(itemName) - 1));
            this.coinReturnValue += this.currentCoinsValue - this.itemPrices.get(itemName);
            this.coinBoxValue += this.itemPrices.get(itemName);
            for(int index = 0; index < 3; index++){
                this.coinBoxCoins[index] += this.currentCoins[index];
                this.currentCoins[index] = 0;
            }
            double changeAmount = new BigDecimal(this.currentCoinsValue - this.itemPrices.get(itemName)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            makeChange(changeAmount);
            this.currentCoinsValue = 0;
            this.displayScreen.setCurrentText("THANK YOU");
        }
    }

    private void makeChange(double changeAmount) {
        this.coinBoxValue -= changeAmount;
        while(changeAmount > 0) {
            if (changeAmount >= 0.25) {
                this.coinBoxCoins[QUARTER]--;
                this.coinReturnCoins[QUARTER]++;
                changeAmount -= 0.25;
            } else if (changeAmount >= 0.10) {
                this.coinBoxCoins[DIME]--;
                this.coinReturnCoins[DIME]++;
                changeAmount -= 0.10;
            } else {
                this.coinBoxCoins[NICKEL]--;
                this.coinReturnCoins[NICKEL]++;
                changeAmount -= 0.05;
            }
        }
    }

    public void fillCoinBox(int nickels, int dimes, int quarters) {
        this.coinBoxCoins[NICKEL] += nickels;
        this.coinBoxCoins[DIME] += dimes;
        this.coinBoxCoins[QUARTER] += quarters;
    }


    public void returnCoins() {
        for(int index = 0; index < 3; index++){
            this.coinReturnCoins[index] += this.currentCoins[index];
            this.currentCoins[index] = 0;
        }
        this.coinReturnValue = this.currentCoinsValue;
        this.currentCoinsValue = 0;
    }

    public String getDisplayScreenText() {
        return this.displayScreen.getCurrentText();
    }
}
