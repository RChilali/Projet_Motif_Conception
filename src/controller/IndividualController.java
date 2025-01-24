package src.controller;

import src.model.simulation.SimulationModel;
import src.model.simulation.UnknownSimulationModel;
import src.model.simulation.registry.SimulationModelRegistry;
import src.view.IndividualView;

import java.util.List;

public class IndividualController implements SimulationController<IndividualView> {

    private IndividualView view;

    private List<SimulationModel> models;

    public IndividualController(List<SimulationModel> models) {
        this.models = models;
    }

    @Override
    public void setView(IndividualView view) {
        this.view = view;
    }

    public void manageInsertIndividual() {
        String name = view.insertName();
        String species = view.insertSpecies();

        SimulationModel simulationModel = SimulationModelRegistry.getInstance(models, species);

        if (simulationModel instanceof UnknownSimulationModel) {
            view.displayErrorMessage("No such species : " + species);
            return;
        }

        simulationModel.addIndividual(name);
    }
}
