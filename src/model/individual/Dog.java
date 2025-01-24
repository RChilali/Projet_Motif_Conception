package src.model.individual;

import src.model.stats.Stats;
import src.model.registry.SpeciesActionRegistry;
import src.model.actions.Action;
import java.util.Map;

public class Dog extends Individual {
    private char gender;

    public Dog(String id, String name, Stats stats, char gender) {
        super(id, name, "Dog", stats);
        this.gender = gender;
    }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    @Override
    public void performAction(String actionName, Individual target) {
        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies("Dog");
        Action action = actions.get(actionName);
        if (action != null && action.validate(this, target)) {
            action.execute(this, target);
        }
    }

    @Override
    public String toString() {
        return String.format("Dog [ID: %s, Name: %s, Gender: %c]", getId(), getName(), gender);
    }
}