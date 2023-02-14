package input;

import objects.Child;
import objects.Gift;
import enums.Cities;

import java.util.ArrayList;

public final class SantaData {

    private ArrayList<Child> children = new ArrayList<>();
    private ArrayList<Gift> santaGiftsList = new ArrayList<>();
    private ArrayList<Cities> cities = new ArrayList<>();

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final ArrayList<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    public ArrayList<Cities> getCities() {
        return cities;
    }

    public void setCities(final ArrayList<Cities> cities) {
        this.cities = cities;
    }
}
