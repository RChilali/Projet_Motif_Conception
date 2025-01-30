package src.view;

/**
 * Définition de la vue responsable de l'affichage des informations fournies par le contrôleur.
 * Cette vue interagit avec l'utilisateur et présente les résultats ou les sorties du modèle.
 */
public interface SimulationView {

    /**
     * Affiche le message à l'utilisateur.
     *
     * @param output La chaîne de caractères représentant les données ou le message à afficher.
     */
    void displayOutput(String output);

    /**
     * Active la vue et gère l'interaction avec l'utilisateur.<p>
     * Cette méthode affiche un message de bienvenue, puis attend les requêtes de l'utilisateur.
     * Les requêtes sont traitées par le contrôleur jusqu'à ce que l'utilisateur saisie la commande de sortie.
     */
    void activateView();

}