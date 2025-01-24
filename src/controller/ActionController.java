package src.controller;

import src.model.individual.Individual;
import src.model.registry.SpeciesActionRegistry;
import src.model.actions.Action;
import src.view.SimulationView;
import java.util.Map;


public class ActionController {
    private SimulationView view;

    public ActionController(SimulationView view) {
        this.view = view;
    }

    // Validate and execute an action
    public void executeAction(String actorId, String actionName, String targetId) {
        Individual actor = view.getIndividualById(actorId);
        Individual target = view.getIndividualById(targetId);

        if (actor == null) {
            view.displayErrorMessage("Actor not found: " + actorId);
            return;
        }

        Map<String, Action> actions = SpeciesActionRegistry.getActionsForSpecies(actor.getSpecies());
        Action action = actions.get(actionName);

        if (action == null) {
            view.displayErrorMessage("Unknown action: " + actionName);
            return;
        }

        if (!action.validate(actor, target)) {
            view.displayErrorMessage("Invalid action: " + actionName);
            return;
        }

        action.execute(actor, target); // Execute the action
        view.actualise(); // Refresh the view
    }
}