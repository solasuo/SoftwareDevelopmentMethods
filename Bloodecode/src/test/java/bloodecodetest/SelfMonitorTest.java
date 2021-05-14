
package bloodecodetest;

import bloodecode.app.MonitoredItem;
import bloodecode.app.SelfMonitor;
import bloodecode.dao.MonitoredItemDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SelfMonitorTest {
    
    @Mock
    MonitoredItemDao midao;       
    
    @InjectMocks
    SelfMonitor selfmonitor;
            
    @Before
    public void setUp() {   
        selfmonitor.createTable();
    }      
    
    @Test
    public void invalidKeysAreHandled() {
        assertEquals("999", String.valueOf(selfmonitor.keyCheck("three")));
    }
    
    public void validKeysAreHandled() {
        assertEquals("1", String.valueOf(selfmonitor.keyCheck("1")));
    }
    
    @Test 
    public void tableIsEmpty() throws SQLException {      
        assertEquals("0", String.valueOf(selfmonitor.howManyNotes()));
    } 
    
    @Test
    public void itemIsAdded() throws SQLException {
        Mockito.when(midao.count()).thenReturn(1);
        selfmonitor.addNote("Hb", 109.0, "Eat rye bread");
        assertEquals("1", String.valueOf(selfmonitor.howManyNotes()));        
    }
    
    @Test 
    public void ItemCanBeRead() throws SQLException {
        MonitoredItem item = new MonitoredItem("Cholesterol", 5.8, "Exercise more");
        Mockito.when(midao.count()).thenReturn(1);
        Mockito.when(midao.read(1)).thenReturn(item);
        selfmonitor.addNote("Cholesterol", 5.8, "Exercise more");
        MonitoredItem chol = selfmonitor.readNote(1);
        assertEquals("Item to be followed: Cholesterol, last value: 5.8, needed actions: Exercise more", chol.toString());
    }
    
    @Test 
    public void notesCanBeListed() throws SQLException {
        List<MonitoredItem> notes = new ArrayList<>();
        MonitoredItem hb = new MonitoredItem("Hb", 109.0, "Eat rye bread");
        MonitoredItem gluc = new MonitoredItem("Glucose", 6.5, "Take insuline on time"); 
        notes.add(hb);
        notes.add(gluc);
        Mockito.when(midao.list()).thenReturn(notes);
        selfmonitor.listNotes();
        verify(midao, times(1)).list();        
    }
    
    @Test
    public void noErrorForEmptyTable() throws SQLException {
        List<MonitoredItem> notes = new ArrayList<>();
        Mockito.when(midao.list()).thenReturn(notes);
        selfmonitor.listNotes();
        verify(midao, times(1)).list();        
    }
    
    @Test
    public void noteCanBeDeleted() throws SQLException {
        Mockito.when(midao.count()).thenReturn(1);
        selfmonitor.addNote("Glucose", 6.5, "Take insuline on time");
        assertEquals("Note 1 deleted.", selfmonitor.deleteNote(1));
    }
}
