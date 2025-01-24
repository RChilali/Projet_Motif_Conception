package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

public class UnknownSimulationModel implements SimulationModel {

    @Override
    public void addIndividual(String name) {
        //
    }

    @Override
    public List<? extends Individual> getIndividuals() {
        return null;
    }

    @Override
    public Individual getIndividualById(String id) {
        return null;
    }

    @Override
    public boolean supports(String input) {
        return true;
    }

}
