package src.model.individual;

import src.model.stats.Stats;

/**
 * Individu dinosaure.
 */
public class Dinosaur extends Individual {

    /**
     * Constructeur champ à champ.
     *
     * @param id      identifiant du dinosaure
     * @param name    nom du dinosaure
     * @param species espèce du dinosaure
     * @param stats   état du dinosaure
     */
    public Dinosaur(String id, String name, String species, Stats stats) {
        super(id, name, species, stats);
    }

    /**
     * @return la chaîne de caractères formatée décrivant le dinosaure.
     */
    @Override
    public String toString() {
        return String.format("%s [ID: %s, Name: %s]", getSpecies(), getId(), getName());
    }
}