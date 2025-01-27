package src.controller;

import src.view.SimulationView;

/**
 * TODO
 */
public interface SimulationController {

    /**
     * Setter : modifie la vue liée à ce contrôleur.
     *
     * @param view la vue
     */
    void setView(SimulationView view);

    /**
     * Interprète la commande saisie par utilisateur. Si la commande n'est pas trouvée, cette méthode
     * appelle la vue pour afficher le message d'erreur.
     *
     * @param request la requête que l'utilisateur a saisie
     */
    void manageRequest(String request);

}