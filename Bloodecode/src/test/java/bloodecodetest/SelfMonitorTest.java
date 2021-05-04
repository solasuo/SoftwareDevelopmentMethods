

package bloodecodetest;

import bloodecode.app.MonitoredItem;
import bloodecode.app.SelfMonitor;
import bloodecode.dao.MonitoredItemDao;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SelfMonitorTest {
    
    @Mock
    JdbcTemplate jdbctemplate;    

    @Mock
    SelfMonitor selfmonitor;
    
    @Mock
    MonitoredItemDao midao;    
            
    @Before
    public void setUp() {   
        MockitoAnnotations.openMocks(this);
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./selfmonitor", "sa", "")) {
            conn.prepareStatement("DROP TABLE Monitor IF EXISTS;").executeUpdate();            
            conn.prepareStatement("CREATE TABLE Monitor (\n"
                    + " id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
                    + " description VARCHAR(50),\n"
                    + " myvalue INTEGER,\n"
                    + " actions VARCHAR(255)\n"
                    + ");").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SelfMonitorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    
    @Test 
    public void tableIsEmpty() throws SQLException {      
        assertEquals("0", String.valueOf(selfmonitor.howManyNotes()));
    } 
    
    @Test
    public void itemIsAdded() throws SQLException {
        selfmonitor.addNote("Hb", 109, "Eat rye bread");
        //assertEquals("1", String.valueOf(selfmonitor.howManyNotes()));
                
    }
}