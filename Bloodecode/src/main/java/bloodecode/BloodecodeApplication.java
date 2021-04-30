package bloodecode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BloodecodeApplication implements CommandLineRunner {        
   
    public static void main(String[] args) {
        SpringApplication.run(BloodecodeApplication.class, args);
    }
    
    @Autowired
    TextUI textUi;
    
    @Autowired
    JdbcTemplate jdbctemplate;
    
    @Override
    public void run(String... args) throws Exception {
        createTable();
        Scanner reader = new Scanner(System.in);
        textUi.start(reader);
    }      
    
    public void createTable() {     
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./selfmonitor", "sa", "")) {
            conn.prepareStatement("DROP TABLE Monitor IF EXISTS;").executeUpdate();            
            conn.prepareStatement("CREATE TABLE Monitor (\n"
                    + " id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
                    + " description VARCHAR(50),\n"
                    + " myvalue INTEGER,\n"
                    + " actions VARCHAR(255)\n"
                    + ");").executeUpdate();
            System.out.println("created");
        } catch (SQLException ex) {
            Logger.getLogger(BloodecodeApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
}
