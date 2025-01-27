package src.model.individual;

import src.model.stats.Stats;

/**
 * Individu dinosaur.
 */
public class Dinosaur extends Individual {

    /**
     * Constructor champ à champ.
     *
     * @param id      idéntifiant du dinosaur
     * @param name    nom du dinosaur
     * @param species espèce du dinosaur
     * @param stats   statistics du dinosaur
     */
    public Dinosaur(String id, String name, String species, Stats stats) {
        super(id, name, species, stats);
    }

    /**
     * @return la chaîne de caractères formatée décrivant le dinosaur.
     */
    @Override
    public String toString() {
        return String.format("%s [ID: %s, Name: %s]", getSpecies(), getId(), getName());
    }
}