package src.view;

import src.controller.IndividualController;
import src.model.individual.Individual;
import src.model.simulation.SimulationModel;
import src.observer.Observer;
import src.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleSimulationView implements SimulationView, Observer {

    private Scanner scanner = new Scanner(System.in);

    private IndividualController controller;

    private List<SimulationModel> models;

    public ConsoleSimulationView(IndividualController controller, List<SimulationModel> models) {
        this.controller = controller;
        this.models = models;

        for (SimulationModel model : models) {
            if (model instanceof Subject) {
                ((Subject) model).addObserver(this);
            }
        }
    }

    public void displayIndividuals() {
        System.out.println("+--------------------- INDIVIDUALS ---------------------+");
        System.out.println("| ID            | Name     | Species   | Vitality | Food | Water |");
        System.out.println("+-------------------------------------------------------+");

        List<Individual> individuals = new ArrayList<>();
        for (SimulationModel model : models) {
            List<? extends Individual> modelIndividuals = model.getIndividuals();
            individuals.addAll(modelIndividuals);
        }

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


    public void getInput() {
        System.out.print("ajouter ou faire une action:\n");
        controller.manageRequest(scanner.nextLine());
    }

    public void sendOutput(String output) {
        System.out.print(output);
    }

    @Override
    public void update() {
        displayIndividuals();
//        System.out.println("Individual added");
    }
}