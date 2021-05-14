
package bloodecodetest;

import bloodecode.app.BloodItem;
import bloodecode.app.Solver;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

public class SolverTest {

    public ArrayList<String> rows;
    public String[] rowItems;
    public HashMap<String, BloodItem> bloodItems;
    
    @Autowired
    Solver solver;      
   
    @Before
    public void setUp() {
        solver = new Solver("codefile.csv");
        solver.itemize();
    }
    
    @Test
    public void allItemsAreRead() {
        assertEquals(16, solver.bloodItems.size());
    }  
    
    @Test
    public void itemDescriptionIsCorrect() {
        assertEquals("thrombocyte or blood cell platelet count", solver.findMeaning("b-trom").getExplanation());
    }    
    
    @Test 
    public void noErrorForUnknownItems() {
        BloodItem result = solver.findMeaning("HIV");
        assertEquals("UNKNOWN: this blood item is not supported yet. Normal range: ask your doctor", result.toString());
    }
    
    @Test
    public void noErrorForEmptySearch() {
        assertEquals("Reasons for low values: ask your doctor, this item is not supported yet. Reasons for high values: ask your doctor", solver.findCauses(" "));
    }
}
