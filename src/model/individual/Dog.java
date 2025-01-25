package src.model.individual;

import src.model.actions.Action;
import src.model.registry.SpeciesActionRegistry;
import src.model.stats.Stats;

import java.util.Map;

public class Dog extends Individual {
    private Gender gender;

    public Dog(String id, String name, Stats stats, Gender gender) {
        super(id, name, "Dog", stats);
        setGender(gender);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void performAction(String actionName, Individual target) {
        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies("Dog");
        Action action = actions.get(actionName);
        if (action != null && action.validate(this, target)) {
            action.execute(this, target);
        }
    }

    public void performAction(String actionName) {
        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies("Dog");
        Action action = actions.get(actionName);
        if (action != null && action.validate(this,null)) {
            action.execute(this,null);
        }
    }

    @Override
    public String toString() {
        return String.format("Dog [ID: %s, Name: %s, Gender: %c]", getId(), getName(), gender);
    }
    public enum Gender {
        male,
        female
    }
}