package ot.Bloodecode;

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
}
