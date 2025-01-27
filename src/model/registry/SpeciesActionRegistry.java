package src.model.registry;

import src.model.actions.Action;
import src.model.actions.binary.BiteAction;
import src.model.actions.binary.StompAction;
import src.model.actions.unary.BarkAction;
import src.model.actions.unary.RestAction;
import src.model.individual.Dinosaur;
import src.model.individual.Dog;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 */
public class SpeciesActionRegistry {
    private static final Map<String, Map<String, Action>> speciesActions = new HashMap<>();

    static {
        speciesActions.put(Dog.class.getSimpleName(),
                Map.of("aboyer", new BarkAction(5),
                        "mordre", new BiteAction(10, 5),
                        "dormir", new RestAction(20))
        );

        speciesActions.put(Dinosaur.class.getSimpleName(),
                Map.of("écraser", new StompAction(15, 1),
                        "dormir", new RestAction(20)));
    }

    /**
     * Renvoie toutes les actions d'une espèce.
     */
    public static Map<String, Action> getActionsForSpecies(String species) {
        return speciesActions.getOrDefault(species, Map.of());
    }

    /**
     * Renvoie la chaine de caractères contenant toutes les actions par espèce.
     */
    public static String getSpeciesActions() {

        if (speciesActions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Set<String> speciesNames = speciesActions.keySet();
        for (String speciesName : speciesNames) {
            Map<String, Action> actionsMap = speciesActions.get(speciesName);
            sb.append(speciesName).append(" : | ");
            Set<String> actionNames = actionsMap.keySet();
            for (String actionName : actionNames) {
                sb.append(actionName).append(" | ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Renvoie la chaine de caractères contenant toutes les actions d'une espèce.
     */
    public static String getSpeciesActions(String species) {

        if (speciesActions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(species).append(" : | ");
        Map<String, Action> actionsForSpecies = getActionsForSpecies(species);
        Set<String> actionNames = actionsForSpecies.keySet();
        for (String actionName : actionNames) {
            sb.append(actionName).append(" | ");
        }
        sb.append("\n");

        return sb.toString();
    }
}