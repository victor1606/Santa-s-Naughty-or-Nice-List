package objects;


import enums.Cities;

import java.util.ArrayList;

public final class City {

    private Double averageCityScore;
    private Cities city;
    private ArrayList<Child> children = new ArrayList<>();
    private ArrayList<Double> scores = new ArrayList<>();

    public City(final Cities city, final Double score) {
        this.city = city;
        scores.add(score);
        setAverageCityScore();
    }

    public Double getAverageCityScore() {
        return averageCityScore;
    }

    /**
     * Sets the city's average score based on the children's scores.
     *
     */
    public void setAverageCityScore() {
        averageCityScore = 0.0;
        for (Child child : children) {
            averageCityScore += child.getAverageScore();
        }
        averageCityScore /= children.size();
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }

    public void setAverageCityScore(final Double averageCityScore) {
        this.averageCityScore = averageCityScore;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public ArrayList<Double> getScores() {
        return scores;
    }

    public void setScores(final ArrayList<Double> scores) {
        this.scores = scores;
    }
}
