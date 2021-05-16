package bloodecode.app;

import bloodecode.BloodecodeApplication;
import bloodecode.dao.MonitoredItemDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Selfmonitor is a tool for handling user's notes.
 */
@Component
public class SelfMonitor  {

    @Autowired
    MonitoredItemDao miDao;
 
    public SelfMonitor() {

    }    
    
    /**
     * Checks how many notes table "Monitor" contains.
     * @return count done by MonitoredItemDao as int
     * @throws SQLException 
     */  
    public int howManyNotes() throws SQLException {
        return miDao.count();
    }
  
    /**
     * Connects to H2 database and creates a table called Monitor for 
     * inserting notes.
     */         
    public void createTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./selfmonitor", "sa", "")) {
            conn.prepareStatement("DROP TABLE Monitor IF EXISTS;").executeUpdate();            
            conn.prepareStatement("CREATE TABLE Monitor (\n"
                    + " id DOUBLE AUTO_INCREMENT PRIMARY KEY,\n"
                    + " description VARCHAR(50),\n"
                    + " myvalue INTEGER,\n"
                    + " actions VARCHAR(255)\n"
                    + ");").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BloodecodeApplication.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }
    
    /**
     * Checks if user input is an integer
     * @param input User input from TextUi
     * @return input parsed to int or 999 if input is not valid
     */
    public int keyCheck(String input) {
        try {
            int key = Integer.parseInt(input);
            return key;
        } catch (NumberFormatException e) {            
            return 999;
        }
    }
    
    /**
     * Adds a new self-monitoring note to the database.
     * @param description  Name or free-form description of the blooditem to be followed. 
     * @param value  User's latest blood test value.
     * @param actions  Lifestyle or diet changes needed to change the abnormal value.
     * @return Confirmation that the item has been added to the database + toString().
     * @throws SQLException 
     */
    public String addNote(String description, double value, String actions) throws SQLException {
        MonitoredItem item = new MonitoredItem(description, value, actions);
        miDao.create(item);        
        return ("Added note: " + item);
    }  
    
    /**
     * Reads note from the database.
     * @param key  Primary key of the note. 
     * @return  Saved MonitoredItem.toString
     * @throws SQLException 
     */
    public String readNote(Integer key) throws SQLException {
        int notes = miDao.count();
        if (notes >= key) {
            MonitoredItem item = miDao.read(key);
            return item.toString();
        } else {
            return "Key not found.";
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
    public String deleteNote(int key) throws SQLException {
        int notes = miDao.count();
        if (notes >= key) {
            miDao.delete(key);
            return "Note " + key + " deleted.";
        } else {
            return "Key not found.";
        }       
    }
}
