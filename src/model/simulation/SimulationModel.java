package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

public interface SimulationModel {

    void addIndividual(String species, String name) throws ReflectiveOperationException;

    boolean simulateAction(String firstIndividualName, String action);

    boolean simulateAction(String firstIndividualName, String action, String secondIndividualName);

    List<Individual> getIndividuals();

    Individual getIndividualByName(String name);

    void updateIndividual(String id, Individual individual);
}
