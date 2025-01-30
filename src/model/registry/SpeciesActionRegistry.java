package src.model.registry;

import src.model.actions.Action;
import src.model.actions.binary.BiteAction;
import src.model.actions.binary.StompAction;
import src.model.actions.unary.BarkAction;
import src.model.actions.unary.RestAction;
import src.model.individual.Dinosaur;
import src.model.individual.Dog;
import src.model.stats.Stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Classe qui enregistre et fournit les actions disponibles pour différentes espèces.
 */
public class SpeciesActionRegistry {
    private static final Map<String, Map<String, Action>> speciesActions = new HashMap<>();

    static {
        speciesActions.put(Dog.class.getSimpleName(),
                Map.of(BarkAction.getActionName(), new BarkAction(new Stats(-5, -2, -1)),
                        RestAction.getActionName(), new RestAction(new Stats(20, 5, 5)),
                        BiteAction.getActionName(), new BiteAction(new Stats(-10, -2, -2),
                                new Stats(0, -2, -2))
                )
        );

        speciesActions.put(Dinosaur.class.getSimpleName(),
                Map.of(RestAction.getActionName(), new RestAction(new Stats(20, 5, 5)),
                        StompAction.getActionName(), new StompAction(new Stats(-20, -10, 0),
                                new Stats(0, -3, -1))
                ));
    }

    /**
     * Renvoie toutes les actions associées à une espèce donnée.
     *
     * @param species Le nom de l'espèce.
     * @return Une map contenant les actions associées à l'espèce spécifiée.
     */
    public static Map<String, Action> getActionsForSpecies(String species) {
        return speciesActions.getOrDefault(species, Map.of());
    }

    /**
     * Renvoie une chaîne de caractères listant toutes les actions disponibles pour une espèce donnée.
     *
     * @param species Le nom de l'espèce.
     * @return Une chaîne de caractères contenant la liste des actions de l'espèce.
     */
    public static String getSpeciesActions(String species) {

        if (speciesActions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(species).append(" :\n| ");
        Map<String, Action> actionsForSpecies = getActionsForSpecies(species);
        Set<String> actionNames = actionsForSpecies.keySet();
        for (String actionName : actionNames) {
            sb.append(actionName).append(" | ");
        }
        sb.append("\n");

        return sb.toString();
    }
}