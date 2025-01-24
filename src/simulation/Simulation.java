package src.simulation;

import src.controller.ActionController;
import src.controller.IndividualController;
import src.model.simulation.ActionModel;
import src.model.simulation.DinosaurSimulationModel;
import src.model.simulation.DogSimulationModel;
import src.model.simulation.SimulationModel;
import src.view.ActionView;
import src.view.IndividualView;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        // Create the view and controller

        List<SimulationModel> models = new ArrayList<>();
        models.add(new DinosaurSimulationModel());
        models.add(new DogSimulationModel());
        IndividualController individualController = new IndividualController(models);
        IndividualView individualView = new IndividualView(individualController, models);
        individualController.setView(individualView);

        System.out.println("+-------------------------------------------------------+");
        System.out.println("|              WELCOME TO THE SIMULATION                |");
        System.out.println("+-------------------------------------------------------+");

        // Add some individuals
        individualController.manageInsertIndividual();
        individualController.manageInsertIndividual();

        // Get the IDs of the added individuals
//        String dogId = models.get(1).getIndividuals().get(0).getId(); // Get the ID of the first dog
//        String dinoId = models.get(0).getIndividuals().get(1).getId(); // Get the ID of the first dinosaur

        System.out.println(models.get(1).getIndividuals());
        System.out.println(models.get(0).getIndividuals());

        ActionModel actionModel = new ActionModel();
        ActionController actionController = new ActionController(actionModel);
        ActionView actionView = new ActionView(actionController, actionModel);
        actionController.setView(actionView);

        // Perform some actions
        actionController.manageExecuteAction(); // Rex barks (unary action)
        actionController.manageExecuteAction(); // Rex bites T-Rex (binary action)
    }
}