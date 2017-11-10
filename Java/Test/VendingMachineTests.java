import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}