package bloodecode;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TextUI {    
  
    public void start(Scanner reader) {
        System.out.println("The application currently supports basic blood count tests: "
                + "B-Leuk, B-Hb, B-Hkr, B-Eryt, E-MCV, E-RDW, E-MCH, E-MCHC and B-Trom");
        Solver solver = new Solver("codefile.csv"); 
        solver.itemize();
        while (true) {
            System.out.println("Choose action");
            System.out.println("x - quit");
            System.out.println("1 - search for an item");
            String command = reader.nextLine().trim();          
            if (command.toLowerCase().equals("x")) {
                break;
            }
            else {
                System.out.println("Enter abbreviation you want to know about");
                String abb = reader.nextLine().trim();     
                System.out.println(solver.findMeaning(abb));
            }
        }
    }  
    

}
