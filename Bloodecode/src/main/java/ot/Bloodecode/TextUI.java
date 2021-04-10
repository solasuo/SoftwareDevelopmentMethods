package ot.Bloodecode;

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
                System.out.println(findMeaning(abb, solver));
            }
        }
    }  
    
    public BloodItem findMeaning(String abb, Solver solver) {
        if (abb.toUpperCase().equals("B-LEUK")) {
            return (solver.getBloodItems().get(0));
        }         
        if (abb.toUpperCase().equals("B-HB")) {
            return (solver.getBloodItems().get(1));
        }                
        if (abb.toUpperCase().equals("B-HKR")) {
            return (solver.getBloodItems().get(2));
        }                
        if (abb.toUpperCase().equals("B-ERYT")) {
            return (solver.getBloodItems().get(3));
        }                
        if (abb.toUpperCase().equals("E-MCV")) {
            return (solver.getBloodItems().get(4));
        }                
        if (abb.toUpperCase().equals("E-RDW")) {
            return (solver.getBloodItems().get(5));
        }                
        if (abb.toUpperCase().equals("E-MCH")) {
            return (solver.getBloodItems().get(6));
        }                
        if (abb.toUpperCase().equals("E-MCHC")) {
            return (solver.getBloodItems().get(7));
        }                
        if (abb.toUpperCase().equals("B-TROM")) {
            return (solver.getBloodItems().get(8));
        }  
        else {
            return (solver.getBloodItems().get(9));
        }
    }
}
