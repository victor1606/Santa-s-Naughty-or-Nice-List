package simulation;

import common.Constants;
import input.SimulationData;
import input.YearlyUpdate;
import json.InputReader;
import json.OutputWriter;
import objects.Child;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Simulation {

    private Simulation() {
    }

    /**
     * This method reads the JSON input data for every test file, calls the necessary methods
     * for processing round zero + the rest of the rounds, then writes the final processed
     * children's list to the appropriate JSON output file.
     *
     */

    public static void startSimulation() throws IOException {
        for (int i = 1; i <= Constants.TESTS_NUMBER; ++i) {
            File inFile = new File(Constants.INPUT_PATH + i
                    + Constants.FILE_EXTENSION);
            File outFile = new File(Constants.OUTPUT_PATH + i
                    + Constants.FILE_EXTENSION);

            Output output = new Output();
            RoundData roundData = new RoundData();

            SimulationData simulationData = InputReader.readData(inFile);

            processRoundZero(simulationData);

            ArrayList<Child> clone = cloneChildList(simulationData.getInitialData().getChildren());

            roundData.setChildren(clone);
            output.getAnnualChildren().add(roundData);

            for (int year = 0; year < simulationData.getNumberOfYears(); ++year) {
                ArrayList<Child> reClone = cloneChildList(clone);

                YearlyUpdate.processAnnualChanges(simulationData,
                        simulationData.getAnnualChanges().get(year), reClone);

                OutputWriter.writeOutput(reClone, outFile, output);

                clone = cloneChildList(reClone);
            }
        }
    }

    /**
     * This method is used to process the round zero changes to the initial input
     *
     * @param simulationData contains the initial input
     */
    public static void processRoundZero(final SimulationData simulationData) {
        Child.setInitialNiceness(simulationData.getInitialData().getChildren());
        Child.removeAdults(simulationData.getInitialData().getChildren());

        Child.childBudgetCalculator(simulationData.getInitialData().getChildren(),
                simulationData.getSantaBudget());

        simulationData.setSortedCitiesByScore(simulationData.getInitialData().getChildren());

        GiftMachine.assignGifts(simulationData,
                simulationData.getInitialData().getChildren());
    }

    /**
     * This method is used to deep copy children lists.
     *
     * @param children contains the list that is cloned
     */
    private static ArrayList<Child> cloneChildList(final ArrayList<Child> children) {
        ArrayList<Child> newList = new ArrayList<>();
        for (Child child : children) {
            newList.add(new Child(child));
        }
        return newList;
    }
}
