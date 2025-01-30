package src.view;

import src.controller.IndividualController;
import src.controller.SimulationController;
import src.model.individual.Individual;
import src.model.simulation.SimulationModel;
import src.observer.Observer;
import src.observer.Subject;

import java.util.List;
import java.util.Scanner;

import static src.controller.IndividualController.*;

/**
 * Définition de la vue qui s'occupe des affichages dans la console.
 */
public class ConsoleSimulationView implements SimulationView, Observer {

    private Scanner scanner = new Scanner(System.in);
    private IndividualController controller;
    private SimulationModel model;
    public static final String EXIT_COMMAND = "quitter";

    /**
     * Constructeur permettant d'associer un contrôleur et un modèle à la vue.
     *
     * @param controller contrôleur associé
     * @param model      model associé
     */
    public ConsoleSimulationView(SimulationController controller, SimulationModel model) {
        this.controller = (IndividualController) controller;
        this.model = model;

        if (model instanceof Subject) {
            ((Subject) model).addObserver(this);
        }
    }

    /**
     * Affiche la liste des individus et leurs caractéristiques.
     */
    public void displayIndividuals() {
        System.out.println("+---------------------- INDIVIDUS ----------------------+");
        System.out.println("| ID            | Nom       | Espèce        | Vitalité | Nourriture | Eau |");
        System.out.println("+-------------------------------------------------------+");

        List<Individual> individuals = model.getIndividuals();

        for (Individual individual : individuals) {
            System.out.println(String.format("| %-13s | %-8s | %-9s | %8.1f | %4.1f | %5.1f |",
                    individual.getId(),
                    individual.getName(),
                    individual.getSpecies(),
                    individual.getStats().getVitality(),
                    individual.getStats().getFood(),
                    individual.getStats().getWater()));
        }
        System.out.println("+-------------------------------------------------------+");
    }

    /**
     * Affiche un individu et ses caractéristiques.
     *
     * @param id identifiant d'un individu
     */
    public void displayIndividualById(String id) {
        Individual individual = model.getIndividualByName(id);
        if (individual == null) {
            model.setOutputToDisplay("Individu " + id + " n'est pas trouvé");
            return;
        }
        System.out.println("+---------------------- INDIVIDU -----------------------+");
        System.out.println("| ID            | Nom       | Espèce        | Vitalité | Nourriture | Eau |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println(String.format("| %-13s | %-8s | %-9s | %8.1f | %4.1f | %5.1f |",
                individual.getId(),
                individual.getName(),
                individual.getSpecies(),
                individual.getStats().getVitality(),
                individual.getStats().getFood(),
                individual.getStats().getWater()));
        System.out.println("+-------------------------------------------------------+");
    }

    /**
     * Affiche le message de bienvenu.
     */
    private void displayWelcomeMessage() {
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|              BIENVENUE A LA SIMULATION                |");
        System.out.println("+-------------------------------------------------------+\n");
        displayCommandExemple("Exemple d'ajout d'individu :", ACTION_COMMAND + " (espèce) (nom)");
        displayCommandExemple("Exemple d'action unaire :", ACTION_COMMAND + " (actionNom) (nom)");
        displayCommandExemple("Exemple de mise à jour d'informations :", UPDATE_COMMAND + "  (nom) (vie) (nourriture) (eau)");
        displayCommandExemple("Exemple de la suppression d'informations :", DELETE_COMMAND + "  (nom)");
        displayCommandExemple("Exemple d'action binaire :", ACTION_COMMAND + " (actionNom) (nomSource) (nomCible)");
        displayCommandExemple("Exemple d'affichage d'informations :", INFO_COMMAND + " (nom) ou " + INFO_COMMAND);
        displayCommandExemple("Pour quitter la simulation :", EXIT_COMMAND);
        System.out.println();
    }

    /**
     * Affiche un exemple de la commande.
     */
    private void displayCommandExemple(String explication, String commandPrototype) {
        System.out.printf("%40s %-35s\n", explication, commandPrototype);
    }


    /**
     * Affiche les commandes disponibles dans l'application.
     */
    private String getInput() {
        displayOutput(ADD_COMMAND + " | " + DELETE_COMMAND + " | " + UPDATE_COMMAND + " | " +
                ACTION_COMMAND + " | " + INFO_COMMAND + " | " + EXIT_COMMAND + " :\n");
        return scanner.nextLine();
    }

    @Override
    public void activateView() {

        displayWelcomeMessage();
        String request = getInput();

        while (!EXIT_COMMAND.equals(request)) {
            controller.manageRequest(request);
            request = getInput();
        }
    }

    @Override
    public void displayOutput(String output) {
        System.out.println(output);
    }

    /**
     * Envoie l'erreur d'une des vérifications de saisie.
     */
    public void displayControllerErrorOutput(String output) {
        displayOutput("\u001B[31m[SAISIE INCORRECTE] : " + output + "\u001B[0m");
    }

    @Override
    public void update() {
        displayOutput(model.getOutputToDisplay());
    }
}