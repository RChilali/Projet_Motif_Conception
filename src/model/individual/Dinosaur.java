package src.model.individual;

import src.model.stats.Stats;

public class Dinosaur extends Individual {

    public Dinosaur(String id, String name, String species, Stats stats) {
        super(id, name, species, stats);
    }

    @Override
    public String toString() {
        return String.format("%s [ID: %s, Name: %s]", getSpecies(), getId(), getName());
    }
}