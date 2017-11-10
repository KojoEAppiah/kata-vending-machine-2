public class VendingMachine {

    private double[] coinValues;
    private double currentCoinsValue;

    public static final int NICKEL = 0;
    public static final int DIME = 1;

    VendingMachine(){
        this.coinValues = new double[2];
        this.currentCoinsValue = 0.00;

        this.coinValues[NICKEL] = 0.05;
        this.coinValues[DIME] = 0.10;

    }

    public void addCoin(int coin){
        this.currentCoinsValue += this.coinValues[coin];
    }

    public double getCurrentCoinsValue(){
        return this.currentCoinsValue;
    }
}
