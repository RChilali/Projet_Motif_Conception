package src.simulation;

import src.controller.IndividualController;
import src.model.simulation.IndividualSimulationModel;
import src.view.ConsoleSimulationView;

import static src.controller.IndividualController.ACTION_COMMAND;
import static src.controller.IndividualController.ADD_COMMAND;
import static src.view.ConsoleSimulationView.EXIT_COMMAND;

public class Simulation {
    public static void main(String[] args) {
        // Create the view and controller

        IndividualSimulationModel model = new IndividualSimulationModel();
        IndividualController individualController = new IndividualController(model);
        ConsoleSimulationView consoleSimulationView = new ConsoleSimulationView(individualController, model);
        individualController.setView(consoleSimulationView);

        System.out.println("+-------------------------------------------------------+");
        System.out.println("|              WELCOME TO THE SIMULATION                |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("Exemple d'ajout d'individu:\t" + ADD_COMMAND + " espèce nom");
        System.out.println("Exemple d'action unaire:\t" + ACTION_COMMAND + " nom actionNom");
        System.out.println("Exemple d'action binaire:\t" + ACTION_COMMAND + " nom actionNom nom2");
        System.out.println("Pour quitter la simulation:\t" + EXIT_COMMAND + "\n");

        consoleSimulationView.activateView();
    }
}