package simulation;

import objects.Child;

import java.util.ArrayList;

public final class RoundData {

    private final ArrayList<Child> children = new ArrayList<>();

    public ArrayList<Child> getChildren() {
        return children;
    }

    /**
     * This method is used to deep copy a list of "Child" objects.
     *
     * @param children contains the children that will be copied
     */
    public void setChildren(final ArrayList<Child> children) {
        for (Child child : children) {
            this.children.add(new Child(child));
        }
    }
}
