package bloodecode;

import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TextUI { 

    @Autowired
    JdbcTemplate jdbctemplate;   
  
    public void start(Scanner reader) throws SQLException {
        System.out.println("The application currently supports basic blood count tests: "
                + "B-Leuk, B-Hb, B-Hkr, B-Eryt, E-MCV, E-RDW, E-MCH, E-MCHC and B-Trom");
        Solver solver = new Solver("codefile.csv"); 
        solver.itemize();
        while (true) {
            System.out.println("Choose action");
            System.out.println("x - quit");
            System.out.println("1 - search for an item");
            System.out.println("2 - enter your own notes");
            String command = reader.nextLine().trim();          
            if (command.toLowerCase().equals("x")) {
                break;
            }
            if (command.toLowerCase().equals("1")) {
                System.out.println("Enter abbreviation you want to know about");
                String abb = reader.nextLine().trim();     
                System.out.println(solver.findMeaning(abb));
            }
            if (command.toLowerCase().equals("2")) {
                System.out.println("Enter description of the item");
                String description = reader.nextLine().trim(); 
                System.out.println("Enter your value");
                int value = Integer.parseInt(reader.nextLine());
                System.out.println("Enter the needed actions");
                String actions = reader.nextLine();
                SelfMonitor item = new SelfMonitor(description, value, actions);
                addNote(item);
                System.out.println("Added note: " + item);
            }
        }
    }  
    //En monen päivän väännön jälkeenkään saa metodeja toimimaan käyttöliittymän ulkopuolella. Esim. tämän metodin pitäisi
    // olla SelfMonitor-luokassa. Ei toimi myöskään Daoja käyttäen.
    // Tästä syystä myös testaus seisoo.
    
    public void addNote(SelfMonitor item) throws SQLException {
        jdbctemplate.update("INSERT INTO Monitor"
            + " (description, myvalue, actions)"
            + " VALUES (?, ?, ?)",
            item.getDescription(),
            item.getMyValue(),
            item.getActions());
    }        
}
