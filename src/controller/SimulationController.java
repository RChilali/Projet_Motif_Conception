package src.controller;

import src.model.individual.Dinosaur;
import src.model.individual.Dog;
import src.model.individual.Individual;
import src.model.registry.SpeciesActionRegistry;
import src.model.stats.Stats;
import src.view.SimulationView;

public class SimulationController {
    private SimulationView view;

    public SimulationController(SimulationView view) {
        this.view = view;
    }

    // Handle adding a new individual
    public void addIndividual(String species, String name) {
        Individual individual = null;
        Stats stats = new Stats(100, 50, 50); // Default stats

        switch (species.toLowerCase()) {
            case "dog":
                individual = new Dog("d" + System.currentTimeMillis(), name, stats, 'M'); // Default gender
                break;
            case "dinosaur":
                individual = new Dinosaur("dn" + System.currentTimeMillis(), name, stats, false); // Default canFly
                break;
            default:
                view.displayErrorMessage("Unknown species: " + species);
                return;
        }

        view.addIndividual(individual); // Notify the view
    }

    // Handle performing an action
    public void performAction(String actorId, String actionName, String targetId) {
        Individual actor = view.getIndividualById(actorId);
        Individual target = view.getIndividualById(targetId);

        if (actor == null) {
            view.displayErrorMessage("Actor not found: " + actorId);
            return;
        }

        if (target == null && !SpeciesActionRegistry.getActionsForSpecies(actor.getSpecies()).get(actionName).validate(actor, null)) {
            view.displayErrorMessage("Invalid target for action: " + actionName);
            return;
        }

        actor.performAction(actionName, target); // Perform the action
        view.actualise(); // Refresh the view
    }
}