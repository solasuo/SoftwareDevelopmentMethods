
package bloodecodetest;

import bloodecode.app.MonitoredItem;
import org.junit.Test;
import static org.junit.Assert.*;

public class MonitoredItemTest {
   
    @Test
    public void toStringIsCorrect() {
        MonitoredItem monitored = new MonitoredItem("LDL", 2.0, "Eat less animal fat");
        assertEquals("Item to be followed: LDL, last value: 2.0, needed actions: Eat less animal fat", monitored.toString());
    }
    
    @Test
    public void compareNotes() {
        MonitoredItem ldl = new MonitoredItem("LDL", 2.0, "Eat less animal fat");
        MonitoredItem hdl = new MonitoredItem("HDL", 4.0, "Eat less animal fat");
        assertFalse(ldl.equals(hdl));
    }    
}
