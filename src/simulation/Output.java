package simulation;

import java.util.ArrayList;

public final class Output {

    private ArrayList<RoundData> annualChildren = new ArrayList<>();

    public ArrayList<RoundData> getAnnualChildren() {
        return annualChildren;
    }
    public void setAnnualChildren(final ArrayList<RoundData> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
