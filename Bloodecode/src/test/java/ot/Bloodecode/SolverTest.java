
package ot.Bloodecode;

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
        assertEquals(10, solver.getBloodItems().size());
    }  
    

}
