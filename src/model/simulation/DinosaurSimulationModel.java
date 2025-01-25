package src.model.simulation;

import src.model.individual.Dinosaur;
import src.model.individual.Individual;
import src.model.stats.Stats;
import src.observer.Subject;

public class DinosaurSimulationModel extends IndividualSimulationModel  {

    @Override
    public void addIndividual(String name) {
        Stats stats = new Stats(100, 50, 50); // Default stats
        Dinosaur dino = new Dinosaur(name, name, stats, false); // Default canFly
        getIndividuals().add(dino);
        notifyObservers(); // Notify observers after adding
    }

    @Override
    public void updateIndividual(String id, Individual individual) {

    }

    @Override
    public boolean supports(String species) {
        return "dinosaur".equals(species);
    }
}