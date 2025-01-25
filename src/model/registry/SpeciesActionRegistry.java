package src.model.registry;

import src.model.actions.Action;
import src.model.actions.binary.BiteAction;
import src.model.actions.binary.StompAction;
import src.model.actions.unary.BarkAction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SpeciesActionRegistry {
    private static final Map<String, Map<String, Action>> speciesActions = new HashMap<>();

    static {
        // Define default Dog actions
        Map<String, Action> dogActions = new HashMap<>();
        dogActions.put("Bark", new BarkAction());
        dogActions.put("Bite", new BiteAction());
        speciesActions.put("Dog", Collections.unmodifiableMap(dogActions));

        // Define default Dinosaur actions
        Map<String, Action> dinoActions = new HashMap<>();
        dinoActions.put("Stomp", new StompAction());
        speciesActions.put("Dinosaur", Collections.unmodifiableMap(dinoActions));
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