package src.model.simulation;

import src.model.individual.Individual;

import java.util.List;

/**
 * Modèle qui est responsable de la gestion des données et de la logique métier
 */
public interface SimulationModel {

    /**
     * Ajoute un individu à la simulation.
     *
     * @param species L'espèce de l'individu.
     * @param name    Le nom de l'individu.
     * @throws ReflectiveOperationException Si une erreur d'instanciation survient.
     */
    void addIndividual(String species, String name) throws ReflectiveOperationException;

    /**
     * Simule une action effectuée par un individu.
     *
     * @param firstIndividualName Le nom de l'individu effectuant l'action.
     * @param actionName          Le nom de l'action à exécuter.
     */
    void simulateAction(String firstIndividualName, String actionName);

    /**
     * Simule une action impliquant deux individus.
     *
     * @param firstIndividualName  Le nom du premier individu.
     * @param action               Le nom de l'action à exécuter.
     * @param secondIndividualName Le nom du second individu.
     */
    void simulateAction(String firstIndividualName, String action, String secondIndividualName);

    /**
     * Récupère la liste de tous les individus de la simulation.
     *
     * @return Une liste d'individus.
     */
    List<Individual> getIndividuals();

    /**
     * Récupère un individu par son nom.
     *
     * @param name Le nom de l'individu.
     * @return L'individu correspondant, ou null s'il n'existe pas.
     */
    Individual getIndividualByName(String name);

    /**
     * Met à jour l'état d'un individu.
     *
     * @param id    L'identifiant de l'individu.
     * @param life  La valeur de niveau de vie à mettre à jour.
     * @param food  La valeur de niveau de nourriture à mettre à jour.
     * @param water La valeur de niveau d'eau à mettre à jour.
     */
    void updateIndividual(String id, String life, String food, String water);

    /**
     * Supprime un individu de la simulation.
     *
     * @param id L'identifiant de l'individu à supprimer.
     */
    void deleteIndividual(String id);

    /**
     * Définit le texte de sortie à afficher.
     *
     * @param OutputToDisplay La sortie à afficher.
     */
    void setOutputToDisplay(String OutputToDisplay);

    /**
     * Récupère le texte de sortie actuel.
     *
     * @return La sortie actuelle sous forme de chaîne de caractères.
     */
    String getOutputToDisplay();
}
