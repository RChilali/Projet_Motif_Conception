package src.controller;


import src.model.simulation.SimulationModel;
import src.view.ConsoleSimulationView;

public class IndividualController implements SimulationController<ConsoleSimulationView> {

    public static final String ADD_COMMAND = "ajouter";

    public static final String ACTION_COMMAND = "action";

    private ConsoleSimulationView view;

    private final SimulationModel model;

    public IndividualController(SimulationModel model) {
        this.model = model;
    }

    public void setView(ConsoleSimulationView view) {
        this.view = view;
    }

    public void manageRequest(String request) {

        if (request == null || model == null) {
            return;
        }

        String[] requestArray = request.split(" ");

        String requestCommand = requestArray[0];

        if (ADD_COMMAND.equals(requestCommand)) {
            try {
                model.addIndividual(requestArray[1], requestArray[2]);
            } catch (ReflectiveOperationException e) {
                view.sendErrorOutput("Saisie incorrect. No such species : " + requestArray[1] + "\n");
            }
            return;
        }

        if (ACTION_COMMAND.equals(requestCommand)) {
            performAction(requestArray);
            return;
        }

        view.sendErrorOutput("Saisie incorrecte\n");
    }

    private void performAction(String[] requestArray) {

        boolean isActionSuccessful;

        if (requestArray.length == 3) {
            isActionSuccessful = model.simulateAction(requestArray[1], requestArray[2]);
        } else if (requestArray.length == 4) {
            isActionSuccessful = model.simulateAction(requestArray[1], requestArray[2], requestArray[3]);
        } else {
            view.sendErrorOutput("Action incorrecte\n");
            return;
        }

        if (!isActionSuccessful) {
            view.sendErrorOutput("Error occurred while executing action\n");
        }

    }

}