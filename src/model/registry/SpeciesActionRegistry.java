package src.model.registry;

import src.model.actions.Action;
import src.model.actions.binary.BiteAction;
import src.model.actions.binary.StompAction;
import src.model.actions.unary.BarkAction;
import src.model.individual.Dinosaur;
import src.model.individual.Dog;

import java.util.HashMap;
import java.util.Map;

public class SpeciesActionRegistry {
    private static final Map<String, Map<String, Action>> speciesActions = new HashMap<>();

    static {
        // Define default Dog actions
        speciesActions.put(Dog.class.getSimpleName(), Map.of("Bark", new BarkAction(), "Bite", new BiteAction()));

        // Define default Dinosaur actions
        speciesActions.put(Dinosaur.class.getSimpleName(), Map.of("Stomp", new StompAction()));
    }

    // Get all actions for a species
    public static Map<String, Action> getActionsForSpecies(String species) {
        return speciesActions.getOrDefault(species, Map.of());
    }

    // Add new actions globally for a species
    public static void addAction(String species, String actionName, Action action) {
        speciesActions.computeIfAbsent(species, k -> new HashMap<>()).put(actionName, action);
    }
}