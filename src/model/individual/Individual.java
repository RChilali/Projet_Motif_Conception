package src.model.individual;

import src.model.actions.Action;
import src.model.stats.Stats;

/**
 * Définition de l'individu.
 */
public abstract class Individual {

    private final String id;
    private final String name;
    private final String species;
    private Stats stats;

    /**
     * Constructeur champ à champ.
     *
     * @param id      identifiant de l'individu
     * @param name    nom de l'individu
     * @param species espèce de l'individu
     * @param stats   état de l'individu
     */
    public Individual(String id, String name, String species, Stats stats) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.stats = stats;
    }

    /**
     * Renvoie l'identifiant de l'individu.
     */
    public String getId() {
        return id;
    }

    /**
     * Renvoie le nom de l'individu.
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie l'espèce de l'individu.
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Renvoie l'état de l'individu.
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Setter : définit l'état de l'individu.
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * Lance l'exécution de l'action binaire de l'individu si cette action est valide.
     *
     * @param action l'action de l'individu
     * @param target la cible de l'action
     * @return true si l'action a été executé, false sinon
     */
    public boolean performAction(Action action, Individual target) {
        if (action != null && action.validate(this, target)) {
            action.execute(this, target);
            return true;
        }

        return false;
    }

    /**
     * Lance l'exécution de l'action unaire de l'individu si cette action est valide.
     *
     * @param action l'action de l'individu
     * @return true si l'action a été executé, false sinon
     */
    public boolean performAction(Action action) {
        if (action != null && action.validate(this)) {
            action.execute(this);
            return true;
        }

        return false;
    }

}