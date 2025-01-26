package src.view;

import src.controller.IndividualController;
import src.model.individual.Individual;
import src.model.simulation.SimulationModel;
import src.observer.Observer;
import src.observer.Subject;

import java.util.List;
import java.util.Scanner;

public class ConsoleSimulationView implements SimulationView, Observer {

    private Scanner scanner = new Scanner(System.in);

    private IndividualController controller;

    private SimulationModel model;

    public static final String EXIT_COMMAND = "exit";

    public ConsoleSimulationView(IndividualController controller, SimulationModel model) {
        this.controller = controller;
        this.model = model;

        if (model instanceof Subject) {
            ((Subject) model).addObserver(this);
        }
    }

    public void displayIndividuals() {
        System.out.println("+--------------------- INDIVIDUALS ---------------------+");
        System.out.println("| ID            | Name     | Species   | Vitality | Food | Water |");
        System.out.println("+-------------------------------------------------------+");

        List<Individual> individuals = model.getIndividuals();

        for (Individual individual : individuals) {
            System.out.println(String.format("| %-13s | %-8s | %-9s | %8.1f | %4.1f | %5.1f |",
                    individual.getId(),
                    individual.getName(),
                    individual.getSpecies(),
                    individual.getStats().getVitality(),
                    individual.getStats().getFood(),
                    individual.getStats().getWater()));
        }
        System.out.println("+-------------------------------------------------------+");
    }

    public void displayIndividualById(String id) {
        Individual individual = model.getIndividualByName(id);
        if (individual == null) {
            sendErrorOutput("Individual with ID " + id + " not found");
            return;
        }
        System.out.println("+--------------------- INDIVIDUAL ----------------------+");
        System.out.println("| ID            | Name     | Species   | Vitality | Food | Water |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println(String.format("| %-13s | %-8s | %-9s | %8.1f | %4.1f | %5.1f |",
                individual.getId(),
                individual.getName(),
                individual.getSpecies(),
                individual.getStats().getVitality(),
                individual.getStats().getFood(),
                individual.getStats().getWater()));
        System.out.println("+-------------------------------------------------------+");
    }


    public String getInput() {
        System.out.print("ajouter,faire une action ou afficher info:\n");
        return scanner.nextLine();
    }

    @Override
    public void activateView() {

        String request = getInput();

        while (!EXIT_COMMAND.equals(request)) {
            controller.manageRequest(request);
            request = getInput();
        }
    }

    public void sendOutput(String output) {
        System.out.println(output);
    }

    public void sendErrorOutput(String output) {
        sendOutput("[ERROR] : " + output);
    }

    @Override
    public void update() {
        sendOutput(model.getOutputToDisplay());
    }
}