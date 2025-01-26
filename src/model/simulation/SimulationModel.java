package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

public interface SimulationModel {

    void addIndividual(String species, String name) throws ReflectiveOperationException;

    void simulateAction(String firstIndividualName, String action);

    void simulateAction(String firstIndividualName, String action, String secondIndividualName);

    List<Individual> getIndividuals();

    Individual getIndividualByName(String name);

    void updateIndividual(String id, String life, String food, String water);

    void deleteIndividual(String id);

    void setOutputToDisplay(String OutputToDisplay);

    String getOutputToDisplay();

    boolean verifyNameAvailability(String name);
}