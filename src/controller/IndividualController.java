package src.controller;


import src.model.simulation.SimulationModel;
import src.view.ConsoleSimulationView;
import src.view.SimulationView;

/**
 * Contrôleur qui gère les commandes liées aux individus (l'ajout, la suppression, la modification, etc.)
 *
 * @see SimulationController
 */
public class IndividualController implements SimulationController {

    public static final String ADD_COMMAND = "ajouter";
    public static final String ACTION_COMMAND = "action";
    public static final String INFO_COMMAND = "info";
    public static final String DELETE_COMMAND = "supprimer";
    public static final String UPDATE_COMMAND = "modifier";
    private ConsoleSimulationView view;
    private final SimulationModel model;

    /**
     * Constructeur qui définie le modèle lié à ce contrôleur.
     *
     * @param model le modèle responsable de la gestion et logique métier
     */
    public IndividualController(SimulationModel model) {
        this.model = model;
    }

    @Override
    public void setView(SimulationView view) {
        this.view = (ConsoleSimulationView) view;
    }

    @Override
    public void manageRequest(String request) {

        if (request == null || model == null) {
            return;
        }

        String[] requestArray = request.split(" ");

        String requestCommand = requestArray[0];

        if (ADD_COMMAND.equals(requestCommand)) {
            if (requestArray.length < 3) {
                view.displayControllerErrorOutput("Le nombre de paramètres pour l'action n'est pas correct\n");
                return;
            }
            try {
                model.addIndividual(requestArray[1], requestArray[2]);
            } catch (ReflectiveOperationException e) {
                view.displayControllerErrorOutput(
                        "Création de l'espèce " + requestArray[1] + " est impossible");
            }
            return;
        }

        if (ACTION_COMMAND.equals(requestCommand)) {
            performAction(requestArray);
            return;
        }

        if (INFO_COMMAND.equals(requestCommand)) {
            if (requestArray.length == 2) {
                view.displayIndividualById(requestArray[1]);
                return;
            }
            view.displayIndividuals();
            return;
        }

        if (DELETE_COMMAND.equals(requestCommand)) {
            if (requestArray.length == 2) {
                model.deleteIndividual(requestArray[1]);
            } else {
                view.displayControllerErrorOutput("Le nombre de paramètres pour l'action n'est pas correct\n");
            }
            return;
        }

        if (UPDATE_COMMAND.equals(requestCommand)) {
            if (requestArray.length == 5) {
                model.updateIndividual(requestArray[1], requestArray[2], requestArray[3], requestArray[4]);
            } else {
                view.displayControllerErrorOutput("Le nombre de paramètres pour l'action n'est pas correct\n");
            }
            return;
        }

        view.displayControllerErrorOutput("Aucune commande de ce type : " + requestCommand);
    }

    /**
     * Exécute une action d'un individu en fonction du nombre de paramètres. Nombre de paramètres possible :
     * <li>3 (commande "action", nom d'un individu, action) : action unaire s'exécute</li>
     * <li>4 (commande "action", nom d'un individu, action, nom du deuxième individu) : action binaire s'exécute</li>
     * <li>Moins que 3 ou plus que 4 : message d'erreur est envoyé à la vue</li>
     *
     * @param requestArray requête de l'utilisateur séparé par espaces et mis dans un tableau
     */
    private void performAction(String[] requestArray) {
        if (requestArray.length == 3) {
            model.simulateAction(requestArray[2], requestArray[1]);
        } else if (requestArray.length == 4) {
            model.simulateAction(requestArray[2], requestArray[1], requestArray[3]);
        } else {
            view.displayControllerErrorOutput("Le nombre de paramètres pour l'action n'est pas correct\n");
        }
    }

}
