package objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import common.Constants;
import enums.Category;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;

public class Child {

    private int id;
    private String lastName;
    private String firstName;
    private Cities city;
    private int age;
    private ArrayList<Category> giftsPreferences = new ArrayList<>();
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double niceScoreBonus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ElvesType elf;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double niceScore;

    public Child() {

    }

    public Child(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
        this.averageScore = child.getAverageScore();
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.assignedBudget = child.getAssignedBudget();
        this.receivedGifts = new ArrayList<>(child.getReceivedGifts());
        this.niceScore = child.getNiceScore();
        this.niceScoreBonus = child.getNiceScoreBonus();
        this.elf = child.getElf();
    }

    /**
     * This method initialises the score history and average score fields
     * for newly added children.
     *
     * @param children contains the list of new children
     */
    public static void setInitialNiceness(final ArrayList<Child> children) {
        for (Child child : children) {
            child.getNiceScoreHistory().add(child.getNiceScore());
            child.setAverageScore();
        }
    }

    /**
     * This method removes all children who are older than 18.
     *
     * @param children contains the list of children
     */
    public static void removeAdults(final ArrayList<Child> children) {
        children.removeIf(child -> child.getAge() > Constants.ADULT_AGE);
    }

    /**
     * This method sets the average score field for every child in the list.
     *
     * @param children contains the list of children
     */
    public static void childAverageScoreCalculator(final ArrayList<Child> children) {
        for (Child child : children) {
            child.setAverageScore();
        }
    }

    /**
     * This method obtains the budget assigned for every child
     * based on their average score and the available santa budget.
     *
     * @param children contains the list of children
     * @param santaBudget contains the santa budget of the current year
     */
    public static void childBudgetCalculator(final ArrayList<Child> children,
                                             final Double santaBudget) {
        Double budgetUnit = 0.0;

        for (Child child : children) {
                budgetUnit += child.getAverageScore();
        }

        budgetUnit = santaBudget / budgetUnit;

        for (Child child : children) {
            child.setAssignedBudget(budgetUnit);
        }
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public final int getAge() {
        return age;
    }

    public final void setAge(final int age) {
        this.age = age;
    }

    public final Cities getCity() {
        return city;
    }

    public final void setCity(final Cities city) {
        this.city = city;
    }

    public final Double getAverageScore() {
        return averageScore;
    }

    /**
     * This method sets a child's average score based on age and history of scores.
     *
     */
    public final void setAverageScore() {
        Double totalSum = 0.0;

        if (age >= Constants.TEEN_AGE) {
            double iSum = 0.0;
            for (int i = 1; i <= niceScoreHistory.size(); ++i) {
                totalSum += i * niceScoreHistory.get(i - 1);
                iSum += i;
            }
            averageScore = totalSum / iSum;
        } else if (age >= Constants.KID_AGE) {
            for (Double score : niceScoreHistory) {
                totalSum += score;
            }
            averageScore = totalSum / niceScoreHistory.size();
        } else {
            averageScore = Constants.MAX_SCORE;
        }
        averageScore += averageScore * niceScoreBonus / Constants.DOUBLE_100;

        if (averageScore > Constants.MAX_SCORE) {
            averageScore = Constants.MAX_SCORE;
        }
    }

    public final Double getNiceScore() {
        return niceScore;
    }

    public final void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public final Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * This method sets a child's assigned budget based on the already calculated
     * budget unit and the child's elf.
     *
     * @param budgetUnit contains the calculated budget unit
     */
    public final void setAssignedBudget(final Double budgetUnit) {
        assignedBudget = averageScore * budgetUnit;
        if (elf.equals(ElvesType.BLACK)) {
            assignedBudget -= Constants.DOUBLE_30 * assignedBudget / Constants.DOUBLE_100;
        } else if (elf.equals(ElvesType.PINK)) {
            assignedBudget += Constants.DOUBLE_30 * assignedBudget / Constants.DOUBLE_100;
        }
    }

    public final ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public final void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public final ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public final void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public final ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public final Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public final void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public final ElvesType getElf() {
        return elf;
    }

    public final void setElf(final ElvesType elf) {
        this.elf = elf;
    }
}
