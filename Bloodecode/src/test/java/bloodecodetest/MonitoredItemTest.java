
package bloodecodetest;

import bloodecode.app.MonitoredItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;


public class MonitoredItemTest {
    
    @Autowired
    MonitoredItem monitored;
    
    @Before
    public void setUp() {
        monitored = new MonitoredItem("LDL", 3.0, "Eat less animal fat");
    }
    
    @Test
    public void toStringIsCorrect() {
        assertEquals("Item to be followed: LDL, last value: 3.0, needed actions: Eat less animal fat", monitored.toString());
    }
}
