package src.simulation;

import src.controller.SimulationController;
import src.view.ConsoleView;

public class Simulation {
    public static void main(String[] args) {
        // Create the view and controller
        ConsoleView view = new ConsoleView();
        SimulationController controller = new SimulationController(view);

        System.out.println("+-------------------------------------------------------+");
        System.out.println("|              WELCOME TO THE SIMULATION                |");
        System.out.println("+-------------------------------------------------------+");

        // Add some individuals
        controller.addIndividual("Dog", "Rex");
        controller.addIndividual("Dinosaur", "T-Rex");

        // Get the IDs of the added individuals
        String dogId = view.getIndividuals().get(0).getId(); // Get the ID of the first dog
        String dinoId = view.getIndividuals().get(1).getId(); // Get the ID of the first dinosaur

        // Perform some actions
        controller.performAction(dogId, "Bark", null); // Rex barks (unary action)
        controller.performAction(dogId, "Bite", dinoId); // Rex bites T-Rex (binary action)
        controller.performAction(dinoId, "Stomp", dogId); // T-Rex stomps Rex (binary action)
    }
}