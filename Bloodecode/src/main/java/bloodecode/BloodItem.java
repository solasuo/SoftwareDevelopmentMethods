package bloodecode;

public class BloodItem {
    public String abbreviation;
    public String explanation;
    public String range;
    public String low;
    public String high;

    public BloodItem(String abbreviation, String explanation, String range, String low, String high) {
        this.abbreviation = abbreviation;
        this.explanation = explanation;
        this.range = range;
        this.low = low;
        this.high = high;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getRange() {
        return range;
    }

    public String getLow() {
        return low;
    }

    public String getHigh() {
        return high;
    }
    
    @Override
    public String toString() {
        return abbreviation + ": " + explanation + ". Normal range: " + range;
    }
}
