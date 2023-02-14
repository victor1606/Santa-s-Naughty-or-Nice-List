package simulation;

import enums.Category;
import enums.ElvesType;
import input.SimulationData;
import objects.Child;
import objects.Gift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class GiftMachine {

    private GiftMachine() {
    }

    /**
     * This method assigns gifts to children based on their assigned budget,
     * preferences and available santa budget.
     *
     * @param simulationData contains all the input data
     * @param children contains the list of children
     */
    public static void assignGifts(final SimulationData simulationData,
                                   final ArrayList<Child> children) {

        for (Child child : children) {
            Double budget = child.getAssignedBudget();

            ArrayList<Gift> assignedGifts = new ArrayList<>();

            for (Category category : child.getGiftsPreferences()) {
                Gift gift = getGiftByCategory(simulationData.getInitialData().getSantaGiftsList(),
                        category, budget);
                if (gift != null) {
                    budget -= gift.getPrice();
                    assignedGifts.add(new Gift(gift));
                    gift.setQuantity(gift.getQuantity() - 1);
                }
            }

            //apply yellow elf effect
            if (assignedGifts.isEmpty() && child.getElf().equals(ElvesType.YELLOW)) {
                ArrayList<Gift> possibleGifts = new ArrayList<>();

                for (Gift gift : simulationData.getInitialData().getSantaGiftsList()) {
                    if (gift.getCategory().equals(child.getGiftsPreferences().get(0))) {
                            possibleGifts.add(gift);
                    }
                }

                Comparator<Gift> compareGiftsByPrice = Comparator.comparing(Gift::getPrice);
                possibleGifts.sort(compareGiftsByPrice);

                if (!possibleGifts.isEmpty()) {
                    if (possibleGifts.get(0).getQuantity() != 0) {
                        assignedGifts.add(possibleGifts.get(0));
                        possibleGifts.get(0).setQuantity(possibleGifts.get(0).getQuantity() - 1);
                    }
                }
            }
            child.setReceivedGifts(new ArrayList<>(assignedGifts));
        }
    }

    /**
     * This method returns the first eligible gift from the santa gift list,
     * based on given category and budget.
     *
     * @param santaGiftsList contains all santa gifts
     * @param category contains the wanted gift category
     * @param budget contains the available budget
     */
    public static Gift getGiftByCategory(final ArrayList<Gift> santaGiftsList,
                                         final Category category,
                                         final Double budget) {
        Collections.sort(santaGiftsList);

        for (Gift gift : santaGiftsList) {
            if (gift.getCategory().equals(category)
                    && gift.getPrice() < budget && gift.getQuantity() != 0) {
                return gift;
            }
        }
        return null;
    }
}
