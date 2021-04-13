package bloodecode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Solver {

    public ArrayList<String> rows;
    public String[] rowItems;
    public ArrayList<BloodItem> bloodItems;
    
    public Solver(String file) {
        bloodItems = new ArrayList<>();
        try {
            rows = Files.lines(Paths.get(file)).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException ex) {
            throw new RuntimeException("Could not read from " +file);
        }
    }  
      
    public void print() {
        System.out.println(rows);
    }
    
    public void itemize() {
        for (int i=1; i<rows.size(); i++) {
            rowItems = rows.get(i).split(";");
            BloodItem item = new BloodItem(rowItems[0], rowItems[1], rowItems[2]);
            bloodItems.add(item);
        }
    }

    public ArrayList<BloodItem> getBloodItems() {
        return bloodItems;
    }      
    public BloodItem findMeaning(String abb) {
        if (abb.toUpperCase().equals("B-LEUK")) {
            return (bloodItems.get(0));
        }         
        if (abb.toUpperCase().equals("B-HB")) {
            return (bloodItems.get(1));
        }                
        if (abb.toUpperCase().equals("B-HKR")) {
            return (bloodItems.get(2));
        }                
        if (abb.toUpperCase().equals("B-ERYT")) {
            return (bloodItems.get(3));
        }                
        if (abb.toUpperCase().equals("E-MCV")) {
            return (bloodItems.get(4));
        }                
        if (abb.toUpperCase().equals("E-RDW")) {
            return (bloodItems.get(5));
        }                
        if (abb.toUpperCase().equals("E-MCH")) {
            return (bloodItems.get(6));
        }                
        if (abb.toUpperCase().equals("E-MCHC")) {
            return (bloodItems.get(7));
        }                
        if (abb.toUpperCase().equals("B-TROM")) {
            return (bloodItems.get(8));
        }  
        else {
            return (bloodItems.get(9));
        }
    }
}
