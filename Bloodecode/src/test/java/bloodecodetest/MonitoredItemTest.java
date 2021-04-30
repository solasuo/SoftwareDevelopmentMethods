
package bloodecodetest;

import bloodecode.MonitoredItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class MonitoredItemTest {
    
    MonitoredItem monitored;
    
    @Before
    public void setUp() {
        monitored = new MonitoredItem("LDL", 3, "Eat less animal fat");
    }
    
    @Test
    public void toStringIsCorrect() {
        assertEquals("Item to be followed: LDL, last value: 3, needed actions: Eat less animal fat", monitored.toString());
    }
}
