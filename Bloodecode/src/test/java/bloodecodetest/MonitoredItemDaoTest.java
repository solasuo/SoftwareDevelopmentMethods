
package bloodecodetest;

import bloodecode.app.MonitoredItem;
import bloodecode.app.SelfMonitor;
import bloodecode.dao.MonitoredItemDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class MonitoredItemDaoTest {
    
    @Mock 
    JdbcTemplate jdbcTemplate;
    
    @Spy
    SelfMonitor selfmonitor;

    @InjectMocks
    MonitoredItemDao midao;    
    
    public MonitoredItemDaoTest() {
    }    
   
    @Before
    public void setUp() {
        selfmonitor.createTable();
    }   
    
    @Test 
    public void noteIsAdded() throws SQLException {
        Mockito.when(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Monitor", Integer.class)).thenReturn(1); 
        MonitoredItem gluc = new MonitoredItem("Glucose", 6.5, "Take insuline on time");
        midao.create(gluc);
        assertEquals("1", String.valueOf(midao.count()));
    }    
    
    @Test
    public void noteCanBeCreated() throws SQLException {
        MonitoredItem hb = new MonitoredItem("Hb", 145.0, "Stop eating meat");
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(),Mockito.anyDouble(), Mockito.anyString())).thenAnswer(invocation -> {
            String description = invocation.getArgument(1);
            Double value = invocation.getArgument(2);
            String actions = invocation.getArgument(3);
            assertEquals(description, hb.getDescription());
            assertEquals(value, hb.getMyValue());
            assertEquals(actions, hb.getActions());
            return null;
        });
        midao.create(hb);
    }
    
    @Test
    public void noteCanBeRead() throws SQLException {
        MonitoredItem hb = new MonitoredItem("Hb", 109.0, "Eat rye bread");
        midao.create(hb);
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(BeanPropertyRowMapper.class), Mockito.anyInt())).thenReturn(hb);
        MonitoredItem readItem = midao.read(1);
        assertEquals("Item to be followed: Hb, last value: 109.0, needed actions: Eat rye bread", readItem.toString());
    }
    
    @Test
    public void noteCanBeDeleted() throws SQLException {
        MonitoredItem chol = new MonitoredItem("Cholesterol", 5.8, "Exercise more");
    }
    
    @Test
    public void notesCanBeListed() throws SQLException {
        List<MonitoredItem> notes = new ArrayList<>();
        MonitoredItem hb = new MonitoredItem("Hb", 109.0, "Eat rye bread");
        MonitoredItem gluc = new MonitoredItem("Glucose", 6.5, "Take insuline on time"); 
        notes.add(hb);
        notes.add(gluc);
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(BeanPropertyRowMapper.class))).thenReturn(notes);
        List<MonitoredItem> result = midao.list();
        assertEquals("Item to be followed: Hb, last value: 109.0, needed actions: Eat rye bread, "
                + "Item to be followed: Glucose, last value: 6.5, needed actions: Take insuline on time", result.get(0) + ", " +result.get(1));
    }      
}    

