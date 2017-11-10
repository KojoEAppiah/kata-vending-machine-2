public class VendingMachine {

    private double currentCoinsValue;

    public static final int NICKEL = 0;

    VendingMachine(){
        this.currentCoinsValue = 0.00;
    }

    public void addCoin(int coin){
        this.currentCoinsValue += 0.05;
    }

    public double getCurrentCoinsValue(){
        return this.currentCoinsValue;
    }
}
