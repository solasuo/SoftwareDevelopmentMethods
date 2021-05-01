package bloodecode;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SelfMonitor  {

    @Autowired
    JdbcTemplate jdbcTemplate;    
    
    @Autowired
    MonitoredItemDao miDao;
    
    public SelfMonitor() {

    }
   
    public String addNote(String description, int value, String actions) throws SQLException {
        MonitoredItem item = new MonitoredItem(description, value, actions);
        miDao.create(item);
        return ("Added note: " + item);
    }  
    
    public MonitoredItem readNote(Integer key) throws SQLException {
        int num = howManyNotes();
        if (num >= key) {
            MonitoredItem item = miDao.read(key);
            return item;
        } else {
            System.out.println("Key not found.");
            return null;
        }
    }
    
    public void listNotes() throws SQLException {
        List notes = miDao.list();
        if (notes.size() > 0) {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println(notes.get(i));           
            }
        } else {
            System.out.println("You have no saved notes");
        }                   
    }  
    
    public void deleteNote(int key) throws SQLException {
        int num = howManyNotes();
        if (num >= key) {
            miDao.delete(key);            
        } else {
            System.out.println("Key not found.");
        }       
    }
    
    public int howManyNotes() {
        int rowCount = this.jdbcTemplate.queryForObject("SELECT COUNT(*) from Monitor", Integer.class);
        return rowCount;
    }
}
