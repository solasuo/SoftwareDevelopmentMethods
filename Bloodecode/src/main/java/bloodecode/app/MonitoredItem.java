package bloodecode.app;

/**
 * MonitoredItem represents a blood test value that the user wants to monitor,
 * such as too low haemoglobin or too high LDL cholesterol.
 */
public class MonitoredItem {
    String description;
    Double myValue;
    String actions;    
    
    public MonitoredItem() {
        
    }

    public MonitoredItem(String description, Double myValue, String actions) {
        this.description = description;
        this.myValue = myValue;
        this.actions = actions;       
    }

    public String getDescription() {
        return description;
    }

    public Double getMyValue() {
        return myValue;
    }

    public String getActions() {
        return actions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMyValue(Double myValue) {
        this.myValue = myValue;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
      
    @Override
    public String toString() {
        return "Item to be followed: " + this.description + ", last value: " + this.myValue + ", needed actions: " + this.actions;
    }       

}
