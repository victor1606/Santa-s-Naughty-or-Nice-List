package input;

import enums.Cities;
import objects.Child;
import objects.City;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class SimulationData {

    private int numberOfYears;
    private Double santaBudget;
    private SantaData initialData;
    private ArrayList<YearlyUpdate> annualChanges = new ArrayList<>();
    private ArrayList<City> sortedCitiesByScore = new ArrayList<>();

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public SantaData getInitialData() {
        return initialData;
    }

    public void setInitialData(final SantaData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<YearlyUpdate> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final ArrayList<YearlyUpdate> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public ArrayList<City> getSortedCitiesByScore() {
        return sortedCitiesByScore;
    }

    /**
     * Obtains a list of City type objects that contains every child that lives
     * in that particular city. The list is then sorted in descending order
     * based on the cities' average score (and also lexicographically).
     *
     * @param children contains the list of children
     */
    public void setSortedCitiesByScore(final ArrayList<Child> children) {
        LinkedHashMap<Cities, City> cities = new LinkedHashMap<>();

        for (Child child : children) {
            if (!cities.containsKey(child.getCity())) {
                cities.put(child.getCity(), new City(child.getCity(), child.getAverageScore()));
            }
            cities.get(child.getCity()).getChildren().add(child);
            cities.get(child.getCity()).setAverageCityScore();
        }

        ArrayList<City> sortedCities = new ArrayList<>(cities.values());

        sortedCities.sort((city1, city2) -> {
            if (!city1.getAverageCityScore().equals(city2.getAverageCityScore())) {
                return city2.getAverageCityScore().compareTo(city1.getAverageCityScore());
            }
            return city1.getCity().toString().compareTo(city2.getCity().toString());
        });

        this.sortedCitiesByScore = sortedCities;
    }
}
