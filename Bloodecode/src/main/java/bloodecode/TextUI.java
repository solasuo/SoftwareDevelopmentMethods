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
    
    @Autowired
    SelfMonitor selfmonitor;
    
  
    public void start(Scanner reader) throws SQLException {
        System.out.println("The application currently supports basic blood count tests: "
                + "B-Leuk, B-Hb, B-Hkr, B-Eryt, E-MCV, E-RDW, E-MCH, E-MCHC and B-Trom");
        Solver solver = new Solver("codefile.csv"); 
        solver.itemize();
        while (true) {
            System.out.println("Choose action");
            System.out.println("x - quit");
            System.out.println("1 - search for an item");
            System.out.println("2 - find reasons for abnormal values");
            System.out.println("3 - enter your own notes");
            System.out.println("4 - find a note");
            System.out.println("5 - list your notes");
            System.out.println("6 - delete a note");
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
                System.out.println("Enter abbreviation you want to know about");
                String abb = reader.nextLine().trim();
                System.out.println(solver.findCauses(abb));
            }
            if (command.toLowerCase().equals("3")) {
                System.out.println("Enter description of the item");
                String description = reader.nextLine().trim(); 
                System.out.println("Enter your value");
                int value = Integer.parseInt(reader.nextLine());
                System.out.println("Enter the needed actions");
                String actions = reader.nextLine();
                System.out.println(selfmonitor.addNote(description, value, actions));
            }
            if (command.toLowerCase().equals("4")) {
                System.out.println("Enter the key of your note");
                int key = Integer.parseInt(reader.nextLine());
                System.out.println(selfmonitor.readNote(key));
            }
            if (command.toLowerCase().equals("5")) {
                selfmonitor.listNotes();
            }
            if (command.toLowerCase().equals("6")) {
                System.out.println("Enter the key of the note you want to delete");
                int key = Integer.parseInt(reader.nextLine());
                selfmonitor.deleteNote(key);
            }
        }
    }   
}
