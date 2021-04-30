package bloodecode;


public class MonitoredItem {
    String description;
    Integer myValue;
    String actions;    
    
    public MonitoredItem() {
        
    }

    public MonitoredItem(String description, Integer myValue, String actions) {
        this.description = description;
        this.myValue = myValue;
        this.actions = actions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMyValue() {
        return myValue;
    }

    public void setMyValue(Integer myValue) {
        this.myValue = myValue;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
    
    @Override
    public String toString() {
        return "Item to be followed: " + description + ", last value: " + myValue + ", needed actions: " + actions;
    }       

}
