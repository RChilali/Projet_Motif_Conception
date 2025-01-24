package src.controller;

import src.model.actions.Action;
import src.model.actions.registry.SpeciesActionRegistry;
import src.model.individual.Individual;
import src.model.simulation.ActionModel;
import src.view.ActionView;

import java.util.Map;


public class ActionController {

    private ActionView view;

    private ActionModel model;

    public ActionController(ActionModel model) {
        this.model = model;
    }

    public void setView(ActionView view) {
        this.view = view;
    }

    public void manageInsertAction() {
        String id = view.insertIndividualId();
        String species = view.insertSpecies();
        String actionName = view.insertActionName();

        Map<String, Action> actionsForSpecies = SpeciesActionRegistry.getActionsForSpecies(species);
        if (!actionsForSpecies.containsKey(actionName)) {
            model.addAction(id, actionName);
        }
    }

    // Validate and execute an action
    public void manageExecuteAction() {

        String actorId = view.insertIndividualId();
        String actionName = view.insertActionName();
        String targetId = view.insertIndividualId();

        Individual actor = model.getIndividualById(actorId);
        Individual target = model.getIndividualById(targetId);

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

        if (target == null && !SpeciesActionRegistry.getActionsForSpecies(actor.getSpecies()).get(actionName).validate(actor, null)) {
            view.displayErrorMessage("Invalid target for action: " + actionName);
            return;
        }

        actor.performAction(actionName, target);
        model.notifyObservers();
    }
}