package src.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import src.controller.IndividualController;
import src.model.simulation.DinosaurSimulationModel;
import src.model.simulation.DogSimulationModel;
import src.model.simulation.IndividualSimulationModel;
import src.model.simulation.SimulationModel;
import src.view.ConsoleSimulationView;

public class Simulation {
    public static void main(String[] args) {
        // Create the view and controller

        ArrayList<SimulationModel> models= new ArrayList<>();
        models.add(new DinosaurSimulationModel());
        models.add(new DogSimulationModel());
        IndividualController individualController = new IndividualController(models);
        ConsoleSimulationView consoleSimulationView = new ConsoleSimulationView(individualController, models);
        individualController.setView(consoleSimulationView);

        System.out.println("+-------------------------------------------------------+");
        System.out.println("|              WELCOME TO THE SIMULATION                |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("ajouter individu ou faire une action");
        System.out.println("Exemple d'ajout d'individu: ajouter espece nom");
        System.out.println("Exemple d'action unaire: action nom action");
        System.out.println("Exemple d'action binaire: action nom action nom2");

        while (true){
            consoleSimulationView.getInput();
        }

    }
}