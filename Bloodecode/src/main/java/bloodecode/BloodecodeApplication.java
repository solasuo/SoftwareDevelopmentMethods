package bloodecode;

import bloodecode.app.SelfMonitor;
import bloodecode.app.Solver;
import bloodecode.app.TextUI;
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

/**
 * Includes main class, which starts the application and component scanning.
 */
@SpringBootApplication
public class BloodecodeApplication implements CommandLineRunner {        
   
    public static void main(String[] args) {
        SpringApplication.run(BloodecodeApplication.class, args);
    }
    
    @Autowired
    TextUI textUi;
    
    @Autowired
    SelfMonitor selfmonitor;
    
    /**
     * Calls the method for initiating database, prints information for the user,
     * creates a scanner and starts the user interface.
     * @param args
     * @throws Exception 
     */
    @Override
    public void run(String... args) throws Exception {
        Scanner reader = new Scanner(System.in);
        System.out.println("The application currently supports basic blood count "
                + "tests: B-Leuk, B-Hb, B-Hkr, B-Eryt, E-MCV, E-RDW, E-MCH, "
                + "E-MCHC and B-Trom, plus fP-Gluk, fP-Kol, fP-Kol-HDL, fP-Kol-LDl,"
                + " fP-Trigly and P-TSH."
        );
        Solver solver = new Solver("codefile.csv"); 
        solver.itemize();
        selfmonitor.createTable();
        textUi.start(reader, solver);
    }      
    

}
