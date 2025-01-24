package src.model.simulation;

import src.model.individual.Dog;
import src.model.individual.Individual;
import src.model.stats.Stats;
import src.observer.Observer;
import src.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class DogSimulationModel implements Subject, SimulationModel {
    private List<Dog> dogs = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Add a new dog to the simulation
    @Override
    public void addIndividual(String name) {
        Stats stats = new Stats(100, 50, 50); // Default stats
        Dog dog = new Dog("d" + System.currentTimeMillis(), name, stats, 'M'); // Default gender
        dogs.add(dog);
        notifyObservers(); // Notify observers after adding
    }

    @Override
    public List<? extends Individual> getIndividuals() {
        return dogs;
    }

    @Override
    public boolean supports(String species) {
        return "Dog".equals(species);
    }

    @Override
    public Individual getIndividualById(String id) {
        for (Individual individual : dogs) {
            if (individual.getId().equals(id)) {
                return individual;
            }
        }
        return null;
    }

}