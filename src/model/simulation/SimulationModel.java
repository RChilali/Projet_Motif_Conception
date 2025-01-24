package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

public interface SimulationModel {

    void addIndividual(String name);

    List<? extends Individual> getIndividuals();

    Individual getIndividualById(String id);

    // Méthode abstraite pour vérifier si l'implémentation correspond à l'entrée
    boolean supports(String species);
}
