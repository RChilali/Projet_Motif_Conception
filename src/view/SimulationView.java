package src.view;

import src.model.individual.Individual;
import java.util.List;

public interface SimulationView {
    // Display methods
    void displayIndividuals();
    void displayErrorMessage(String message);

    // Add/update individuals
    void addIndividual(Individual individual);
    void actualise(); // Refresh the view

    // Input methods
    String saisirName(); // Capture name input
    String saisirSpecies(); // Capture species input
    String saisirAction(); // Capture action input

    // Utility methods
    Individual getIndividualById(String id); // Find individual by ID
    List<Individual> getIndividuals(); // Get the list of individuals
}