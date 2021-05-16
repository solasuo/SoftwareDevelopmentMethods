package bloodecode.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Solver is based on a csv file which contains information fetched from Terveyskirjasto.fi and other 
 * reliable sources. It provides information about blood test items.
 */
public class Solver {
    public ArrayList<String> rows;
    public String[] rowItems;
    public HashMap<String, BloodItem> bloodItems;
    
    /**
     * The constructor reads the file into rows.
     * @param file  csv file
     */
    public Solver(String file) {
        bloodItems = new HashMap<>();
        try {
            rows = Files.lines(Paths.get(file)).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException ex) {
            System.out.println("Could not read from " + file);
        }
    }  
    
    /**
     * Converts rows into BloodItem objects
     */
    public void itemize() {
        for (int i = 1; i < rows.size(); i++) {
            rowItems = rows.get(i).split(";");
            BloodItem item = new BloodItem(rowItems[0], rowItems[1], rowItems[2], rowItems[3], rowItems[4]);
            bloodItems.put(rowItems[0].toUpperCase(), item);
        }
    }
    
    /**
     * Provides explanation of an abbreviation and normal range of its values.
     * @param abb  Abbreviation in a blood test, such as E-mch or B-hkr.
     * Case insensitive.
     * @return  BloodItem
     */
    public BloodItem findMeaning(String abb) {
        if (bloodItems.containsKey(abb.toUpperCase())) {
            return (bloodItems.get(abb.toUpperCase()));
        } else {
            return (bloodItems.get("UNKNOWN"));
        }
    }
    
    /**
     * Provides possible explanations for abnormally high or low values.
     * @param abb  Abbreviation in a blood test, case insensitive.
     * @return  values of high and low of the bloodItem.
     */
    public String findCauses(String abb) {
        BloodItem item;
        if (bloodItems.containsKey(abb.toUpperCase())) {
            item = bloodItems.get(abb.toUpperCase());
        } else {
            item = bloodItems.get("UNKNOWN");
        }      
        return "Reasons for low values: " + item.getLow() + ". Reasons for high values: " + item.getHigh();
    }
}
