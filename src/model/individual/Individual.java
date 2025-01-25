package src.model.individual;

import src.model.actions.Action;
import src.model.registry.SpeciesActionRegistry;
import src.model.stats.Stats;

import java.util.Map;

public abstract class Individual {
    private final String id;
    private final String name;
    private final String species;
    private Stats stats;

    public Individual(String id, String name, String species, Stats stats) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.stats = stats;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean performAction(String actionName, Individual target) {
        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(species);
        Action action = actions.get(actionName);

        if (action != null && action.validate(this, target)) {
            action.execute(this, target);
            return true;
        }

        return false;
    }

    public boolean performAction(String actionName) {
        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(species);
        Action action = actions.get(actionName);

        if (action != null && action.validate(this, null)) {
            action.execute(this, null);
            return true;
        }

        return false;
    }

}