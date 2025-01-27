package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

/**
 * Modèle qui est responsable de la gestion des données et de la logique métier
 */
public interface SimulationModel {

    void addIndividual(String species, String name) throws ReflectiveOperationException;

    void simulateAction(String firstIndividualName, String actionName);

    void simulateAction(String firstIndividualName, String action, String secondIndividualName);

    List<Individual> getIndividuals();

    Individual getIndividualByName(String name);

    void updateIndividual(String id, String life, String food, String water);

    void deleteIndividual(String id);

    void setOutputToDisplay(String OutputToDisplay);

    String getOutputToDisplay();
}