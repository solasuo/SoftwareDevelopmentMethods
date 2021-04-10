package ot.Bloodecode;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
        
public class TextUITest {
    
    Solver solver;
    TextUI textUi;
    
    @Before
    public void setUp() {
        solver = new Solver("codefile.csv");
        solver.itemize();
        textUi = new TextUI();
    }

    @Test
    public void itemDescriptionIsCorrect() {
        assertEquals("thrombocyte or blood cell platelet count", textUi.findMeaning("b-trom", solver).getExplanation());
    }
}
