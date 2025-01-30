package src.simulation;

import src.controller.IndividualController;
import src.model.simulation.IndividualSimulationModel;
import src.view.ConsoleSimulationView;

public class Simulation {
    public static void main(String[] args) {
        IndividualSimulationModel model = new IndividualSimulationModel();
        IndividualController individualController = new IndividualController(model);
        ConsoleSimulationView consoleSimulationView = new ConsoleSimulationView(individualController, model);
        individualController.setView(consoleSimulationView);
        consoleSimulationView.activateView();
    }
}