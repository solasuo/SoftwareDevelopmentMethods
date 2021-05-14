package bloodecode.app;

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
  
    public void start(Scanner reader, Solver solver) throws SQLException {
        while (true) {
            System.out.println("Choose action");
            System.out.println("0 - quit");
            System.out.println("1 - search for an item");
            System.out.println("2 - find reasons for abnormal values");
            System.out.println("3 - enter your own notes");
            System.out.println("4 - find a note");
            System.out.println("5 - list your notes");
            System.out.println("6 - delete a note");
            int command = validateCommand(reader.nextLine());          
            if (command == 0) {
                break;
            }
            if (command == 1) {
                System.out.println("Enter abbreviation you want to know about");
                String abb = reader.nextLine().trim();     
                System.out.println(solver.findMeaning(abb));
            }
            if (command == 2) {
                System.out.println("Enter abbreviation you want to know about");
                String abb = reader.nextLine().trim();
                System.out.println(solver.findCauses(abb));
            }
            if (command == 3) {
                System.out.println("Enter description of the item");
                String description = reader.nextLine().trim(); 
                System.out.println("Enter your value");
                double value = 0;
                if (reader.hasNextDouble() || reader.hasNextInt()) {
                   value = Double.parseDouble(reader.nextLine()); 
                } else {
                    System.out.println("Not a valid number, a dummy value will be used");
                    reader.nextLine();
                }    
                System.out.println("Enter the needed actions");
                String actions = reader.nextLine();
                System.out.println(selfmonitor.addNote(description, value, actions));
            }
            if (command == 4) {
                System.out.println("Enter the key of your note");
                int key = selfmonitor.keyCheck(reader.nextLine());
                if (key == 999) {
                    System.out.println("invalid key");
                    reader.nextLine();
                } else {
                    System.out.println(selfmonitor.readNote(key));                              
                }    
            }
            if (command == 5) {
                selfmonitor.listNotes();
            }
            if (command == 6) {
                System.out.println("Enter the key of the note you want to delete");
                int key = selfmonitor.keyCheck(reader.nextLine());
                if (key == 999) {
                    System.out.println("invalid key");
                    reader.nextLine();
                } else {
                    selfmonitor.deleteNote(key);      
                    System.out.println("Note " + key + " deleted");
                }
            } if (command < 0 || command > 6) {
                System.out.println("Invalid command, please try again");
            }
        }
    } 
    int validateCommand(String input) {
        try {
            int command = Integer.parseInt(input);
            return command;
        } catch(NumberFormatException e) {
            return 999;
            
        }
    }
    
}
