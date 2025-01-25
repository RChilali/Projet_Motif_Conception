package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

public interface SimulationModel {

    void addIndividual(String name);

    List<Individual> getIndividuals();

    Individual getIndividualById(String id);

    void updateIndividual(String id, Individual individual);

    // Méthode abstraite pour vérifier si l'implémentation correspond à l'entrée
    boolean supports(String species);
}
