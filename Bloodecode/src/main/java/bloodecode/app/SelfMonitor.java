package bloodecode.app;

import bloodecode.dao.MonitoredItemDao;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Selfmonitor is a tool for handling user's notes.
 */
@Component
public class SelfMonitor  {

    @Autowired
    JdbcTemplate jdbcTemplate;    
    
    @Autowired
    MonitoredItemDao miDao;
    
    public SelfMonitor() {

    }
    /**
     * Adds a new self-monitoring note to the database.
     * @param description  Name or free-form description of the blooditem to be followed. 
     * @param value  User's latest blood test value.
     * @param actions  Lifestyle or diet changes needed to change the abnormal value.
     * @return Confirmation that the item has been added to the database + toString().
     * @throws SQLException 
     */
    public String addNote(String description, int value, String actions) throws SQLException {
        MonitoredItem item = new MonitoredItem(description, value, actions);
        miDao.create(item);
        return ("Added note: " + item);
    }  
    
    /**
     * Reads note from the database.
     * @param key  Primary key of the note. 
     * @return  Saved MonitoredItem
     * @throws SQLException 
     */
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
    
    /**
     * List saved notes and their keys.
     * @throws SQLException 
     */
    public void listNotes() throws SQLException {
        List notes = miDao.list();
        if (notes.size() > 0) {
            for (int i = 0; i < notes.size(); i++) {
                int key = i + 1;
                System.out.println("Key: " + key + ", " + notes.get(i));           
            }
        } else {
            System.out.println("You have no saved notes");
        }                   
    }  
    
    /**
     * Deletes note from the database.
     * @param key  Primary key of the note.
     * @throws SQLException 
     */
    public void deleteNote(int key) throws SQLException {
        int num = howManyNotes();
        if (num >= key) {
            miDao.delete(key);            
        } else {
            System.out.println("Key not found.");
        }       
    }
    
    /**
     * Counts notes in the database.
     * @return  Number of notes.
     */
    public int howManyNotes() {
        int rowCount = this.jdbcTemplate.queryForObject("SELECT COUNT(*) from Monitor", Integer.class);
        return rowCount;
    }
}
