package src.model.individual;

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
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public Stats getStats() { return stats; }

    // Perform an action (to be overridden by subclasses)
    public abstract void performAction(String actionName, Individual target);
    public abstract void performAction(String actionName);
}