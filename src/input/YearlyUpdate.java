package input;

import enums.Category;
import enums.CityStrategyEnum;
import objects.Child;
import objects.City;
import objects.Gift;
import simulation.GiftMachine;

import java.util.ArrayList;
import java.util.Comparator;

public final class YearlyUpdate {

    private Double newSantaBudget;
    private ArrayList<Gift> newGifts = new ArrayList<>();
    private ArrayList<Child> newChildren = new ArrayList<>();
    private ArrayList<Child> childrenUpdates  = new ArrayList<>();
    private CityStrategyEnum strategy;

    /**
     * This method applies the necessary changes to the simulation data
     * and children's list, following the passing of a year.
     *
     * @param simulationData contains the input data
     * @param yearlyUpdate contains the changes that have to be applied
     * @param children contains the list of processed children
     */
    public static void processAnnualChanges(final SimulationData simulationData,
                                            final YearlyUpdate yearlyUpdate,
                                            ArrayList<Child> children) {

        simulationData.setSantaBudget(yearlyUpdate.getNewSantaBudget()); //updated santa budged

        for (Child child : children) {
            child.setAge(child.getAge() + 1); //aged all children
        }

        for (Child child : yearlyUpdate.getNewChildren()) {
            child.getNiceScoreHistory().add(child.getNiceScore());
            child.setAverageScore();
        }

        children.addAll(yearlyUpdate.newChildren); //added new children

        Child.removeAdults(children);

        for (Child childUpdate : yearlyUpdate.getChildrenUpdates()) { //for every child update
            for (Child child : children) {
                if (child.getId() == childUpdate.getId()) { //if the child is found
                    if (childUpdate.getNiceScore() != null &&  childUpdate.getNiceScore() >= 0) {

                        ArrayList<Double> history = new ArrayList<>(child.getNiceScoreHistory());
                        history.add(childUpdate.getNiceScore()); //add new score to the score list

                        child.setNiceScoreHistory(history);
                    }
                    //add new preferences
                    child.getGiftsPreferences().addAll(0, childUpdate.getGiftsPreferences());
                    //remove duplicate preferences
                    ArrayList<Category> noDuplicates = new ArrayList<>();
                    for (Category category : child.getGiftsPreferences()) {
                        if (!noDuplicates.contains(category)) {
                            noDuplicates.add(category);
                        }
                    }
                    child.setGiftsPreferences(noDuplicates);

                    //change elf
                    child.setElf(childUpdate.getElf());
                }
            }
        }
        //add new santa gifts
        simulationData.getInitialData().getSantaGiftsList().addAll(yearlyUpdate.newGifts);

        //recalculate average score, budget, and assign gifts
        Child.childAverageScoreCalculator(children);
        Child.childBudgetCalculator(children, simulationData.getSantaBudget());

        //apply gift giving strategy
        if (yearlyUpdate.getStrategy().equals(CityStrategyEnum.NICE_SCORE)) {
            Comparator<Child> compareByScore =
                    Comparator.comparing(Child::getAverageScore, Comparator.reverseOrder());
            children.sort(compareByScore);
        } else if (yearlyUpdate.getStrategy().equals(CityStrategyEnum.NICE_SCORE_CITY)) {
            simulationData.setSortedCitiesByScore(children);
            children = sortChildrenByCities(simulationData.getSortedCitiesByScore());
        } else {
            sortChildrenById(children);
        }

        //assign gifts and reorder list based on ID
        GiftMachine.assignGifts(simulationData, children);
        sortChildrenById(children);
    }

    private static ArrayList<Child> sortChildrenByCities(
            final ArrayList<City> sortedCitiesByScore) {

        ArrayList<Child> children = new ArrayList<>();
        for (City city : sortedCitiesByScore) {
            sortChildrenById(city.getChildren());
            children.addAll(city.getChildren());
        }

        return children;
    }

    private static void sortChildrenById(final ArrayList<Child> children) {
        Comparator<Child> compareById = Comparator.comparing(Child::getId);
        children.sort(compareById);
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<Child> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final ArrayList<Child> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(final CityStrategyEnum strategy) {
        this.strategy = strategy;
    }
}
