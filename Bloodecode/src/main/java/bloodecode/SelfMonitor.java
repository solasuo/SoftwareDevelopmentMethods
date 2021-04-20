package bloodecode;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SelfMonitor  {
    String description;
    Integer myValue;
    String actions;

    @Autowired
    JdbcTemplate jdbctemplate;    
    
    public SelfMonitor() {
        
    }

    public SelfMonitor(String description, Integer myValue, String actions) {
        this.description = description;
        this.myValue = myValue;
        this.actions = actions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String item) {
        this.description = item;
    }

    public Integer getMyValue() {
        return myValue;
    }

    public void setMyValue(Integer myValue) {
        this.myValue = myValue;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
    
    public void addNote() throws SQLException {
        jdbctemplate.update("INSERT INTO Monitor"
            + " (description, myvalue, actions)"
            + " VALUES (?, ?, ?)",
            this.description,
            this.myValue, 
            this.actions);
    }    
    
    @Override
    public String toString() {
        return "Item to be followed: " + description + ", last value: " + myValue + ", needed actions: " + actions;
    }
    
}
