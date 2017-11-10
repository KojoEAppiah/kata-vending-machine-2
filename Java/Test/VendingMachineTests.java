import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class VendingMachineTests {

    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
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
}