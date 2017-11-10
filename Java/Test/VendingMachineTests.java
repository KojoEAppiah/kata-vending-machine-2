import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class VendingMachineTests {

    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.setItemPrice("Cola", 1.00);
        vendingMachine.setItemPrice("Chips", 0.50);
        vendingMachine.setItemPrice("Candy", 0.65);
    }

    @Test
    public void whenANickelIsInsertedIntoTheVendingMachineItAddSFiveCentsToItsCurrentCoinsValue() {

        vendingMachine.addCoin(VendingMachine.NICKEL);
        assertEquals(0.05, vendingMachine.getCurrentCoinsValue(), 0.0);
    }

    @Test
    public void whenADimeIsInsertedIntoTheVendingMachineItAddSTenCentsToItsCurrentCoinsValue(){

        vendingMachine.addCoin(VendingMachine.DIME);
        assertEquals(0.10, vendingMachine.getCurrentCoinsValue(), 0.0);
    }

    @Test
    public void whenAQuarterIsInsertedIntoTheVendingMachineItAddSTwentyFiveCentsToItsCurrentCoinsValue(){

        vendingMachine.addCoin(VendingMachine.QUARTER);
        assertEquals(0.25, vendingMachine.getCurrentCoinsValue(), 0.0);
    }

    @Test
    public void whenAColaIsAddedToTheVendingMachineItsColaCountIncreasesByTheProvidedAmount(){
        vendingMachine.addItem("Cola", 1);
        assertEquals(1, vendingMachine.getItemCount("Cola"));
    }

    @Test
    public void whenColaPriceIsSetItIsStoredAndReturnedProperly(){
        vendingMachine.setItemPrice("Cola", 1.00);
        assertEquals(1.0, vendingMachine.getItemPrice("Cola"), 0.0);
    }

    @Test
    public void whenChipsAreAddedToTheVendingMachineItsChipCountIncreasesByTheProvidedAmount(){

        vendingMachine.addItem("Chips", 1);
        assertEquals(1, vendingMachine.getItemCount("Chips"));
    }

    @Test
    public void whenChipsPriceIsSetItIsStoredAndReturnedProperly(){
        vendingMachine.setItemPrice("Chips", 0.50);
        assertEquals(0.5, vendingMachine.getItemPrice("Chips"), 0.0);
    }

    @Test
    public void whenCandyIsAddedToTheVendingMachineItsChipCountIncreasesByTheProvidedAmount(){

        vendingMachine.addItem("Candy", 1);
        assertEquals(1, vendingMachine.getItemCount("Candy"));
    }

    @Test
    public void whenCandyPriceIsSetItIsStoredAndReturnedProperly(){
        vendingMachine.setItemPrice("Candy", 0.65);
        assertEquals(0.65, vendingMachine.getItemPrice("Candy"), 0.0);
    }

    @Test
    public void whenCurrentCoinsValueIsGreatEnoughAndItemCountIsGreaterThanZeroAnItemCanBeDispensed(){

        vendingMachine.addItem("Candy", 1);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.selectItem("Candy");
        assertEquals(0, vendingMachine.getItemCount("Candy"));
    }

    @Test
    public void whenItemIsPurchasedProperChangeValueIsReturned(){
        vendingMachine.addItem("Chips", 1);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.selectItem("Chips");
        assertEquals(0.25, vendingMachine.getCoinReturnValue(), 0.0);
    }

    @Test
    public void whenChangeIsMadeCoinsAreMovedFromCoinBoxToCoinReturn(){
        vendingMachine.fillCoinBox(2, 4, 2);

        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addItem("Candy", 1);
        vendingMachine.selectItem("Candy");
        assertEquals(3, vendingMachine.getCoinBoxCoinCount(VendingMachine.DIME));
    }

    @Test
    public void afterCoinReturnIsActivatedCurrentCoinsValueIsTransferredToCoinReturnValue(){
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.NICKEL);
        vendingMachine.returnCoins();

        assertEquals(0.30, vendingMachine.getCoinReturnValue(), 0.0);
    }

    @Test
    public void afterItemIsDispensedDisplayReadsThankYou(){
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);
        vendingMachine.addCoin(VendingMachine.QUARTER);

        vendingMachine.addItem("Cola", 1);
        vendingMachine.selectItem("Cola");
        assertEquals("THANK YOU", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenCoinsAreAddedTheDisplayScreenIsSetCurrentCoinsValue(){
        vendingMachine.addCoin(VendingMachine.QUARTER);
        assertEquals("$0.25", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenVendingMachineIsInitializedWithDisplayScreenIsSetToExactChangeOnly(){

        assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenVendingMachineCanMakeChangedAndNothingHasBeenSelectedDisplayScreenIsSetToInsertCoin(){
        vendingMachine.fillCoinBox(1,4,2);

        assertEquals("INSERT COIN", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenSoldOutItemIsSelectedDisplayReadsSoldOut(){
        vendingMachine.addItem("Candy", 0);
        vendingMachine.selectItem("Candy");
        assertEquals("SOLD OUT", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenTheDisplayReadsSoldOutAndASoldOutItemIsSelectedAgainTheDisplayReadsExactChangeOnly(){
        vendingMachine.addItem("Candy", 0);
        vendingMachine.selectItem("Candy");
        vendingMachine.selectItem("Candy");
        assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenTheDisplayReadsSoldOutAndASoldOutItemIsSelectedAgainWithCanMakeChangeTheDisplayReadsInsertCoin(){
        vendingMachine.fillCoinBox(1,4,2);
        vendingMachine.addItem("Candy", 0);
        vendingMachine.selectItem("Candy");
        vendingMachine.selectItem("Candy");
        assertEquals("INSERT COIN", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenAnItemIsSelectedAndCurrentCoinsValueIsInsufficientTheItemsPriceIsDisplayed(){
        vendingMachine.addItem("Candy", 1);
        vendingMachine.selectItem("Candy");
        assertEquals("PRICE $0.65", vendingMachine.getDisplayScreenText());
    }

    @Test
    public void whenAnChipsAreSelectedAndCurrentCoinsValueIsInsufficientTheItemsPriceIsDisplayed(){
        vendingMachine.addItem("Chips", 1);
        vendingMachine.selectItem("Chips");
        assertEquals("PRICE $0.50", vendingMachine.getDisplayScreenText());
    }
}