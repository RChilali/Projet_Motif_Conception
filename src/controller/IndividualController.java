package src.controller;


import java.util.ArrayList;
import src.model.individual.Individual;
import src.model.simulation.SimulationModel;
import src.view.ConsoleSimulationView;

public class IndividualController implements SimulationController<ConsoleSimulationView> {

    private static final String ADD_COMMAND = "ajouter";

    private static final String ACTION_COMMAND = "action";

    private ConsoleSimulationView view;

    private ArrayList<SimulationModel> models;

    public IndividualController(ArrayList<SimulationModel> models) {
        this.models = models;
    }

    public void setView(ConsoleSimulationView view) {
        this.view = view;
    }

    public void manageRequest(String request) {

        if (request == null || request.isEmpty() || models.isEmpty()) {
            return;
        }

        String[] requestArray = request.split(" ");

        SimulationModel simulationModel = models.stream()
            .filter(model -> model.supports(requestArray[1])).findFirst().orElseThrow();

        String requestCommand = requestArray[0];

        if (ADD_COMMAND.equals(requestCommand)) {
            simulationModel.addIndividual(requestArray[2]);
            return;
        }

        if (ACTION_COMMAND.equals(requestCommand)) {
            performAction(requestArray);
            return;
        }

        view.sendOutput("saisie incorrect\n");
    }

    private void performAction(String[] requestArray) {

        models.forEach(model -> {
            Individual individual = model.getIndividualById(requestArray[1]);

            if (individual == null) {
                return;
            }

            if (requestArray.length == 3) {
                models.performAction(requestArray[2]);
                return;
            }

            if (requestArray.length == 4) {
                Individual secondIndividual = model.getIndividualById(requestArray[3]);
                individual.performAction(requestArray[2], secondIndividual);
                return;
            }

            view.sendOutput("action incorrect\n");
        });
    }

}