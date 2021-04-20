package bloodecode;

public class BloodItem {
    public String abbreviation;
    public String explanation;
    public String range;

    public BloodItem(String abbreviation, String explanation, String range) {
        this.abbreviation = abbreviation;
        this.explanation = explanation;
        this.range = range;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
    
    @Override
    public String toString() {
        return abbreviation + ": " + explanation + ". Normal range: " + range;
    }
}
