package src.view;

import src.model.individual.Individual;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements SimulationView {
    private List<Individual> individuals = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayIndividuals() {
        System.out.println("+--------------------- INDIVIDUALS ---------------------+");
        System.out.println("| ID            | Name     | Species   | Vitality | Food | Water |");
        System.out.println("+-------------------------------------------------------+");
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

    @Override
    public void displayErrorMessage(String message) {
        System.out.println("! ERROR: " + message);
    }

    @Override
    public void addIndividual(Individual individual) {
        individuals.add(individual);
        displayIndividuals(); // Refresh the display
    }

    @Override
    public void actualise() {
        displayIndividuals(); // Refresh the display
    }

    @Override
    public String saisirName() {
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    @Override
    public String saisirSpecies() {
        System.out.print("Enter species (Dog/Dinosaur): ");
        return scanner.nextLine();
    }

    @Override
    public String saisirAction() {
        System.out.print("Enter action (e.g., 'Bite'): ");
        return scanner.nextLine();
    }

    @Override
    public Individual getIndividualById(String id) {
        for (Individual individual : individuals) {
            if (individual.getId().equals(id)) {
                return individual;
            }
        }
        return null;
    }

    @Override
    public List<Individual> getIndividuals() {
        return individuals;
    }
}