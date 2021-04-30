
package bloodecodetest;

import bloodecode.Solver;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolverTest {
    
    Solver solver;      
   
    @Before
    public void setUp() {
        solver = new Solver("codefile.csv");
        solver.itemize();
    }
    
    @Test
    public void allItemsAreRead() {
        assertEquals(10, solver.bloodItems.size());
    }  
    
    @Test
    public void itemDescriptionIsCorrect() {
        assertEquals("thrombocyte or blood cell platelet count", solver.findMeaning("b-trom").getExplanation());
    }    
}
