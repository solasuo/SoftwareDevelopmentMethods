package bloodecode.dao;

import bloodecode.app.MonitoredItem;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Acts as an interface to the database
 */
@Component
public class MonitoredItemDao implements Dao<MonitoredItem, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;    
    
    public MonitoredItemDao() {

    }
   
    @Override
    public void create(MonitoredItem item) throws SQLException {
        jdbcTemplate.update("INSERT INTO Monitor"
            + " (description, myvalue, actions)"
            + " VALUES (?, ?, ?)",
            item.getDescription(),
            item.getMyValue(), 
            item.getActions());   
    }

    @Override
    public MonitoredItem read(Integer key) throws SQLException {
        MonitoredItem item = jdbcTemplate.queryForObject("SELECT * FROM Monitor WHERE id = ?",
            new BeanPropertyRowMapper<>(MonitoredItem.class), key);
        return item;
    }
    
    @Override
    public MonitoredItem update(MonitoredItem item) throws SQLException {
        System.out.println("Please delete the note to be updated and create a new note ");
        return item;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        String delete = "DELETE FROM Monitor where id = ?";
        jdbcTemplate.update(delete, key);
    }

    @Override
    public List<MonitoredItem> list() throws SQLException {
        List notes;
        notes = jdbcTemplate.query("SELECT * FROM Monitor",        
            new BeanPropertyRowMapper<>(MonitoredItem.class));
        return notes;
    }
    
    public Integer count() throws SQLException {
        int notes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Monitor", Integer.class);
        return notes;
    }
}
